package com.duang.fuli.controller.api;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.duang.fuli.controller.base.JSONController;
import com.duang.fuli.domain.User;
import com.duang.fuli.domain.form.LoginForm;
import com.duang.fuli.service.LoginService;
import com.duang.fuli.service.result.LoginResult;
import com.duang.fuli.web.utils.CookieFlags;
import com.duang.fuli.web.utils.SessionFlags;
/**
 * 
 * @author zgq
 * @date 2016年3月19日 下午1:49:37
 */
@Controller
@Scope("prototype")
@RequestMapping(value = "/api/login")
public class ApiLoginController extends JSONController{
	
	@Resource(name="loginService")
	private LoginService loginService;
	
	@RequestMapping(value="/confirm",method=RequestMethod.POST)
	public void login(HttpServletResponse response,HttpSession session,@RequestBody LoginForm loginForm)
			throws Exception {
		String rightCaptcha = (String) session.getAttribute(SessionFlags.LOGIN_CAPTCHA_SESSION_FLAG);
		loginForm.setRightCaptcha(rightCaptcha);
		LoginResult loginResult = loginService.login(loginForm);
		User loginedUser=loginResult.getUser();
		if(loginResult.isLoginSuccessful()){
			Cookie cookie1 = new Cookie(CookieFlags.LOGINED_USERNAME,
					loginedUser.getUsername());
			Cookie cookie2 = new Cookie(CookieFlags.LOGINED_PASSWORD,
					loginedUser.getPassword());
			addCookies(response,cookie1, cookie2);
			loginedUser.setPassword(null); //密码在这里session中，用不到
			session.setAttribute(SessionFlags.LOGINED_USER_FLAG, loginedUser);
		}
		writeJson(loginResult);
	}


}
