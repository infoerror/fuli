package com.duang.fuli.service.impl;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.duang.fuli.dao.UserDao;
import com.duang.fuli.domain.User;
import com.duang.fuli.domain.form.LoginForm;
import com.duang.fuli.service.LoginService;
import com.duang.fuli.service.result.LoginResult;
import com.duang.fuli.utils.MD5Utils;

/**
 * 
 * @author zgq
 * @date 2016年3月19日 下午1:52:56
 */
@Service("loginService")
public class LoginServiceImpl implements LoginService {

	@Resource(name = "userDao")
	private UserDao userDao;

	@Override
	public LoginResult login(LoginForm loginForm) {
		/**
		 * 这里可以用aop自动验证
		 */
		LoginResult loginResult = LoginResult.validate(loginForm);
		if (loginResult != null)
			return loginResult;

		loginForm.setUsername(loginForm.getUsername());
		String encryptedPassword = MD5Utils.md5(loginForm.getPassword());
		loginForm.setPassword(encryptedPassword);
        return confirmLogin(loginForm);
	}

	private LoginResult confirmLogin(LoginForm loginForm) {
		// 开始登陆
		User user = userDao.login(loginForm);
		if (user == null) {
			return LoginResult.USERNAME_OR_PASSWORD_ERROR;
		}
		// 登录成功
		return LoginResult.succ(user);
	}

	@Override
	public LoginResult loginByCookie(LoginForm loginForm) {
		return confirmLogin(loginForm);
	}

}
