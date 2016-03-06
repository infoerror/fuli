package com.duang.fuli.service;

import com.duang.fuli.domain.form.LoginForm;
import com.duang.fuli.domain.form.RegisterForm;
import com.duang.fuli.service.result.VerifyAccountResult;

public interface VerifyAccountService {

	public VerifyAccountResult verifyRegister(RegisterForm registerForm);
	
	public VerifyAccountResult verifyLogin(LoginForm loginForm);

}
