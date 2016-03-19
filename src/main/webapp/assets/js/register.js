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
var registerAddress = "";
// if (!registerAddress.endsWith('/')) {
// registerAddress = registerAddress + '/';
// }


function checkLoginSubmit() {
	return checkUsername() && checkPassword() && checkCaptcha();
}
