package com.duang.fuli.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.duang.fuli.controller.base.BaseController;
import com.duang.fuli.domain.form.LoginForm;
import com.duang.fuli.service.LoginService;
import com.duang.fuli.service.result.LoginResult;
import com.duang.fuli.service.result.LoginResult.LOGIN_RESULT;
import com.duang.fuli.utils.CaptchaUtils;
import com.duang.fuli.utils.SessionFlagUtils;

@Controller
@Scope("prototype")
@RequestMapping(value = "/login")
public class LoginController extends BaseController{
	
	@Resource(name="loginService")
	private LoginService loginService;
	
	@RequestMapping("/")
	public String index(){
		return "login/index";
	}
	
	@RequestMapping(value="/confirm",method=RequestMethod.POST)
	public void login(HttpServletResponse response,HttpSession session,@RequestBody LoginForm loginForm)
			throws Exception {
		String rightCaptcha = (String) session.getAttribute(SessionFlagUtils.LOGIN_SESSION_FLAG);
		loginForm.setRightCaptcha(rightCaptcha);
		LoginResult loginResult = loginService.login(loginForm);
		if(loginResult.getResult()==LOGIN_RESULT.SUCCESS){
			session.setAttribute(SessionFlagUtils.LOGINED_USER_FLAG, loginResult.getUser());
		}
		writeJson(loginResult.getJson(), response);
	}
	
	@RequestMapping("/loginSuccess")
	public String loginSuccess() throws Exception {
		return "redirect:/account/center";
	}
	
	@RequestMapping("/showCaptcha")
	public void showCaptcha(HttpServletResponse response,HttpSession session) throws Exception {
		CaptchaUtils.writeImg2Resp(response,session,SessionFlagUtils.LOGIN_SESSION_FLAG);
	}

}
