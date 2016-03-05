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
	$("#e_username").show();
}

function hideUsernameError() {
	$("#e_username").hide();
}

function checkSubmit() {
	return checkUsername() && checkPassword() && checkConfirmPassword()
			&& checkPasswordEqual() && checkCaptcha();
}

function checkPassword() {
	var len = $.trim($("#password").val()).length;
	if (len < 6 || len > 16) {
		showPasswordError("密码不能少于6位或大于16位")
		return false;
	} else {
		// $("#t").text("");
		hidePasswordError();
		return true;
	}
}

function hidePasswordError() {
	$("#e_password").hide();
}

function showPasswordError(message) {
	$("#e_password").text(message);
	$("#e_password").show();
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
	$("#e_confirm_password").show();
}

function hideConfirmPasswordError() {
	$("#e_confirm_password").hide();

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
	$("#e_confirm_password").hide();
	$("#e_password").hide();
}

function showPasswordEqualError(message) {
	$("#e_password").text(message);
	$("#e_password").show();
	$("#e_confirm_password").text(message);
	$("#e_confirm_password").show();
}

function checkCaptcha() {
	var len = $.trim($("#captcha").val()).length;
	if (len != 6) {
		showCaptchaError("验证码只能是6位数");
		return false;
	} else {
		// $("#t").text("");
		hideCaptchaError();
		return true;
	}
}

function showCaptchaError(message) {
	$("#e_captcha").text(message);
	$("#e_captcha").show();

}

function hideCaptchaError() {
	$("#e_captcha").hide();

}

function changeImg() {
	var imgSrc = $("#captcha_img");
	var timestamp = (new Date()).valueOf();
	imgSrc.attr("src", registerAddress + "showCaptcha?x=" + timestamp);
}

function active() {
	$.ajax({
		type : "POST",
		url : registerAddress + "sendEmailForRegister",
		dataType : "json",
		complete : function(data) {
			// 在这里做些事情，假设返回的json数据里有name这个属性
			// 有时候可以直接data.name或者data['name']去访问
			// 但有时候，却要通过var jsonData =
			// eval("("+data.responseText+")");才可以通过jsonData.name访问，而且这种情况下，需要是complete而不是success
			var jsonData = eval("(" + data.responseText + ")");
			
			if (jsonData.error_no== 0) {
				$('#a_login').show();
				$("#sendEmailForRegister").hide();
				$("#message").text(jsonData.msg);
			}else{
				$("#message").text(jsonData.error);	
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
		url : registerAddress + "confirm", // 跳转到 action
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
			var no =result.error_no; 
			if (no== 0) {
				window.location.href = registerAddress + "activeAccount";
			} else {
				currentState = -1;
				if (no==10001 || no==10002) {
					showUsernameError(result.error);
				}
				if (no==20001) {
					showPasswordError(result.error);
				}

				if (no==20002) {
					showPasswordEqualError(result.error);
				}

				if (no==30001) {
					showCaptchaError(result.error);
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
