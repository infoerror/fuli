package com.duang.fuli.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.duang.fuli.controller.base.BaseController;
import com.duang.fuli.service.RegisterService;
import com.duang.fuli.utils.CaptchaUtils;
import com.duang.fuli.web.utils.SessionFlags;

/**
 * 
 * @author zgq
 * @date 2016年3月19日 下午1:49:19
 */
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

	
	@RequestMapping("/activeAccount")
	public Object activeAccountUI(HttpSession session) throws Exception {
		if (session.getAttribute("inactiveAccount") == null) {
			return "register/index";
		}
		return "register/activeAccountUI";
	}

	
	@RequestMapping("/showCaptcha")
	public void showCaptcha(HttpServletResponse response,HttpSession session) throws Exception {
		CaptchaUtils.writeImg2Resp(response,session,SessionFlags.REGISTER_SESSION_FLAG);
	}

}
