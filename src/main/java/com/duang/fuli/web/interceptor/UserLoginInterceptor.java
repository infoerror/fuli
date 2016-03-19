package com.duang.fuli.web.interceptor;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.duang.fuli.domain.User;
import com.duang.fuli.domain.form.LoginForm;
import com.duang.fuli.service.LoginService;
import com.duang.fuli.service.result.LoginResult;
import com.duang.fuli.web.utils.CookieFlags;
import com.duang.fuli.web.utils.SessionFlags;

/**
 * 
 * @author zgq
 * @date 2016年3月19日 下午1:57:21
 */
public class UserLoginInterceptor extends LoginInterceptor<User> {

	@Resource
	private LoginService loginService;

	public UserLoginInterceptor() {
		addCookie(CookieFlags.LOGINED_USERNAME);
		addCookie(CookieFlags.LOGINED_PASSWORD);
		addIgnoreURL("/login/");
		addIgnoreURL("/register/");
	}

	@Override
	protected boolean doLogin(HttpServletRequest request,
			HttpServletResponse response, Object handler, List<String> values)
			throws Exception {
		LoginForm loginForm = new LoginForm();
		loginForm.setUsername(values.get(0));
		loginForm.setPassword(values.get(1));
		LoginResult loginResult = loginService.loginByCookie(loginForm);
		if (loginResult.isLoginSuccessful()) {
			request.getSession().setAttribute(
					SessionFlags.LOGINED_USER_FLAG, loginResult.getUser());
			return true;
		}
		return false;
	}

	@Override
	protected String loginSessionFlag() {
		return SessionFlags.LOGINED_USER_FLAG;
	}
	
	protected void forwardLogin(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
	    request.getRequestDispatcher("/login/").forward(request, response);
	}

	@Override
	protected boolean lackCookie(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws ServletException, IOException {
		forwardLogin(request, response);
		return false;
	}

}
