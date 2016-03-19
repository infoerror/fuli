package com.duang.fuli.controller.api;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.duang.fuli.controller.base.JSONController;
import com.duang.fuli.domain.InactiveAccount;
import com.duang.fuli.domain.form.RegisterForm;
import com.duang.fuli.service.RegisterService;
import com.duang.fuli.service.result.RegisterResult;
import com.duang.fuli.service.result.SendRegisterEmailResult;
import com.duang.fuli.utils.SessionFlagUtils;

@Controller
@Scope("prototype")
@RequestMapping(value = "/api/register")
public class ApiRegisterController extends JSONController{
	@Resource(name="registerService")
	private RegisterService registerService;
	
	@RequestMapping(value="/confirm",method=RequestMethod.POST)
	public void register(HttpSession session,@RequestBody RegisterForm registerForm)
			throws Exception {
		String rightCaptcha = (String) session.getAttribute(SessionFlagUtils.REGISTER_SESSION_FLAG);
		registerForm.setRightCaptcha(rightCaptcha);
		RegisterResult registerResult = registerService.register(registerForm);
		if(registerResult.isRegisterSuccessful()){
			session.setAttribute("inactiveAccount", registerResult.getInactiveAccount());
		}
		writeJson(registerResult);
	}

	@RequestMapping("sendEmailForRegister")
	public void sendRegisterMail(HttpSession session,
			HttpServletRequest request) throws Exception {
		String targetUrl = request.getRequestURL().toString();
		String targetUri = request.getRequestURI().toString();
		String contextPath = request.getServletContext().getContextPath();

		StringBuilder url = new StringBuilder(targetUrl);
		StringBuilder tempContextUrl = url
				.delete(url.length() - targetUri.length(), url.length())
				.append(contextPath).append("");
		tempContextUrl.append("/register/authenticateEmail");

		InactiveAccount tmpUser=(InactiveAccount) session.getAttribute("inactiveAccount");
		SendRegisterEmailResult result = registerService.sendRegisterMail(
				tempContextUrl.toString(), (InactiveAccount) tmpUser);
		writeJson(result);
		
	}
}
