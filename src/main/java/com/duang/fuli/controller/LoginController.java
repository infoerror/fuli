package com.duang.fuli.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.duang.fuli.controller.base.BaseController;
import com.duang.fuli.service.LoginService;
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
	
	
	@RequestMapping("/loginSuccess")
	public String loginSuccess() throws Exception {
		return "redirect:/user/center";
	}
	
	@RequestMapping("/showCaptcha")
	public void showCaptcha(HttpServletResponse response,HttpSession session) throws Exception {
		CaptchaUtils.writeImg2Resp(response,session,SessionFlagUtils.LOGIN_SESSION_FLAG);
	}

}
