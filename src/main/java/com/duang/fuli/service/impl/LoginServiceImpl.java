package com.duang.fuli.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.duang.fuli.dao.UserDao;
import com.duang.fuli.domain.User;
import com.duang.fuli.domain.form.LoginForm;
import com.duang.fuli.service.LoginService;
import com.duang.fuli.service.result.LoginResult;
import com.duang.fuli.utils.MD5Utils;

@Service("loginService")
public class LoginServiceImpl implements LoginService{

	@Resource(name="userDao")
	private UserDao userDao;
	

	@Override
	public LoginResult login(LoginForm loginForm) {
		/**
		 * 这里可以用aop自动验证  
		 */
		LoginResult loginResult = LoginResult.validate(loginForm);
		if(loginResult!=null)
			return  loginResult;
		
		User loginUser= new User();
		loginUser.setUsername(loginForm.getUsername());
		String encryptedPassword=MD5Utils.md5(loginForm.getPassword());
		loginUser.setPassword(encryptedPassword);
		
		//start login
		User user=userDao.login(loginUser);
		if(user==null){
			return LoginResult.USERNAME_OR_PASSWORD_ERROR;
		}
		
		//login successfully
		return LoginResult.succ(user);
	}

}
