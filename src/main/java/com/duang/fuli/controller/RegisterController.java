package com.duang.fuli.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.alibaba.fastjson.JSON;
import com.duang.fuli.controller.base.BaseController;
import com.duang.fuli.domain.InactiveAccount;
import com.duang.fuli.domain.RegisterForm;
import com.duang.fuli.domain.json.Result;
import com.duang.fuli.service.RegisterService;
import com.duang.fuli.service.result.RegisterResult;
import com.duang.fuli.service.result.SendRegisterEmailResult;
import com.duang.fuli.service.result.RegisterResult.REGISTER_RESULT;
import com.duang.fuli.utils.CaptchaUtils;

@Controller
@Scope("prototype")
@RequestMapping(value = "/register")
public class RegisterController extends BaseController {
	private static final Logger LOG = Logger
			.getLogger(RegisterController.class);
	@Resource(name="registerService")
	private RegisterService registerService;
	
	@RequestMapping("/")
	public String index(){
		return "register/index";
	}

	@RequestMapping("authenticateEmail")
	public String authenticateEmail(Model model, @RequestParam String token)
			throws Exception {
		if (LOG.isDebugEnabled())
			LOG.debug("receive token:" + token);
		if (!(token != null && token.trim().length() == 32)) {
			return "register/index";
		}
		if (registerService.authenticateEmail(token)) {
			model.addAttribute("authenticate", true);
			return "register/succ";
		}
		return "register/index";
	}


	@RequestMapping("sendEmailForRegister")
	public void sendRegisterMail(HttpServletResponse response,HttpSession session,
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
		writeJson(result.getJson(),response);
		
	}

	
	@RequestMapping("/activeAccount")
	public Object activeAccountUI(HttpSession session) throws Exception {
		if (session.getAttribute("inactiveAccount") == null) {
			return "register/index";
		}
		return "register/activeAccountUI";
	}

	
	@RequestMapping(value="/confirm",method=RequestMethod.POST)
	public void register(HttpServletResponse response,HttpSession session,@RequestBody RegisterForm registerForm)
			throws Exception {
		String rightCaptcha = (String) session.getAttribute(CaptchaUtils.REGISTER_SESSION_FLAG);
		registerForm.setRightCaptcha(rightCaptcha);
		RegisterResult registerResult = registerService.register(registerForm);
		if(registerResult.getResult()==REGISTER_RESULT.SUCCESS){
			session.setAttribute("inactiveAccount", registerResult.getInactiveAccount());
		}
		writeJson(registerResult.getJson(), response);
	}

	@RequestMapping("/showCaptcha")
	public void showCaptcha(HttpServletResponse response,HttpSession session) throws Exception {
		CaptchaUtils.writeImg2Resp(response,session,CaptchaUtils.REGISTER_SESSION_FLAG);
	}

}
