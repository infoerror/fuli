package com.duang.fuli.service;

import com.duang.fuli.domain.form.LoginForm;
import com.duang.fuli.service.result.LoginResult;

/**
 * 
 * @author zgq
 * @date 2016年3月19日 下午1:51:37
 */
public interface LoginService {
	
	LoginResult login(LoginForm loginForm);
	
	/**
	 * 不用验证
	 * @param loginForm
	 * @return
	 */
	LoginResult loginByCookie(LoginForm loginForm);

}
