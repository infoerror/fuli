var currentState = 0;

function checkUsername() {
	var myreg = /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;
	if (!myreg.test($("#username").val())) {
		showUsernameError("用户名只能是邮箱");
		return false;
	}
	return true;
}

function showUsernameError(message) {
	$("#e_username").text(message);
	$("#le_username").show();
}

function hideUsernameError() {
	$("#le_username").hide();
}

function checkSubmit() {
	return checkUsername() && checkPassword() && checkConfirmPassword()
			&& checkPasswordEqual() && checkCaptcha();
}

function checkPassword() {
	var len = $.trim($("#password").val()).length;
	if (len < 6 || len > 15) {
		showPasswordError("密码不能少于6位或大于15位")
		return false;
	} else {
		// $("#t").text("");
		hidePasswordError();
		return true;
	}
}

function hidePasswordError() {
	$("#le_password").hide();
}

function showPasswordError(message) {
	$("#e_password").text(message);
	$("#le_password").show();
}

function checkConfirmPassword() {
	var len = $.trim($("#confirmPassword").val()).length;
	if (len < 6 || len > 15) {
		showConfirmPasswordError("密码不能少于6位或大于15位");
		return false;
	} else {
		// $("#t").text("");
		hideConfirmPasswordError();
		return true;
	}
}

function showConfirmPasswordError(message) {
	$("#e_confirm_password").text(message);
	$("#le_confirm_password").show();
}

function hideConfirmPasswordError() {
	$("#le_confirm_password").hide();

}

function checkPasswordEqual() {
	if ($("#password").val() == $("#confirmPassword").val()) {
		hidePasswordEqualError();
		return true;
	} else {
		showPasswordEqualError("二次密码必须一致");
		return false;
	}
}

function hidePasswordEqualError() {

	$("#le_confirm_password").hide();
	$("#le_password").hide();
}

function showPasswordEqualError(message) {
	$("#e_password").text(message);
	$("#e_password").show();
	$("#e_confirm_password").text(message);
	$("#le_confirm_password").show();
}

function checkCaptcha() {
	var len = $.trim($("#captcha").val()).length;
	if (len != 4) {
		showCaptchaError("验证码只能是4位数");
		return false;
	} else {
		// $("#t").text("");
		hideCaptchaError();
		return true;
	}
}

function showCaptchaError(message) {
	$("#e_captcha").text(message);
	$("#le_captcha").show();

}

function hideCaptchaError() {
	$("#le_captcha").hide();

}

function changeImg() {
	var imgSrc = $("#captcha_img");
	var timestamp = (new Date()).valueOf();
	imgSrc.attr("src", registerAddress + "showCaptcha?x=" + timestamp);
}

function active() {
	$.ajax({
		type : "POST",
		url : registerAddress + "sendRegisterMail",
		dataType : "json",
		complete : function(data) {
			$("#li_active").hide();
			// 在这里做些事情，假设返回的json数据里有name这个属性
			// 有时候可以直接data.name或者data['name']去访问
			// 但有时候，却要通过var jsonData =
			// eval("("+data.responseText+")");才可以通过jsonData.name访问，而且这种情况下，需要是complete而不是success
			var jsonData = eval("(" + data.responseText + ")");
			$("#t_message").text(jsonData.tips);
			if (jsonData.code > 0) {
				$('#a_login').show();
			}
		}
	});

}
var registerAddress = "";
// if (!registerAddress.endsWith('/')) {
// registerAddress = registerAddress + '/';
// }

function register() {

	$.ajax({
		url : registerAddress + "register", // 跳转到 action
		data : {
			username : $("#username").val(),
			password : $("#password").val(),
			confirmPassword : $("#confirmPassword").val(),
			captcha : $("#captcha").val()
		},
		type : 'post',
		cache : false,
		dataType : 'json',
		success : function(result) {
			if (result.code == '0') {
				// http://localhost:8080/Jzdy1.0/style/image/error.png
				window.location.href = registerAddress + "activeAccountUI";
			} else {
				currentState = -1;
				if (result.username) {
					showUsernameError(result.username);
				}
				if (result.password) {
					showPasswordError(result.password);
				}
				if (result.confirmPassword) {
					showPasswordError(result.confirmPassword);
				}

				if (result.inequal) {
					showPasswordEqualError(result.inequal);
				}

				if (result.captcha) {
					showCaptchaError(result.captcha);
				}

				// $("#tips_content").text(result.tips);
				changeImg();

			}
		},
		error : function() {
			alert("暂时无法链接服务器，请联系管理员")
		}
	});

}
