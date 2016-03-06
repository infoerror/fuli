package com.duang.fuli.service;

import com.duang.fuli.domain.form.LoginForm;
import com.duang.fuli.service.result.LoginResult;

public interface LoginService {
	
	LoginResult login(LoginForm loginForm);

}
