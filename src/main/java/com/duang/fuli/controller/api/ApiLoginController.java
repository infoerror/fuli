package com.duang.fuli.controller.api;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.duang.fuli.controller.base.JSONController;
import com.duang.fuli.domain.form.LoginForm;
import com.duang.fuli.service.LoginService;
import com.duang.fuli.service.result.LoginResult;
import com.duang.fuli.utils.SessionFlagUtils;

@Controller
@Scope("prototype")
@RequestMapping(value = "/api/login")
public class ApiLoginController extends JSONController{
	
	@Resource(name="loginService")
	private LoginService loginService;
	
	@RequestMapping(value="/confirm",method=RequestMethod.POST)
	public void login(HttpSession session,@RequestBody LoginForm loginForm)
			throws Exception {
		String rightCaptcha = (String) session.getAttribute(SessionFlagUtils.LOGIN_SESSION_FLAG);
		loginForm.setRightCaptcha(rightCaptcha);
		LoginResult loginResult = loginService.login(loginForm);
		if(loginResult.isLoginSuccessful()){
			session.setAttribute(SessionFlagUtils.LOGINED_USER_FLAG, loginResult.getUser());
		}
		writeJson(loginResult);
	}


}
