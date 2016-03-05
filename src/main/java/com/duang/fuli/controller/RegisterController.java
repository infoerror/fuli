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

	private static String EXPIRED_MAIL_JSON;
	private static String SUCC_MAIL_JSON;
	private static String FAIL_MAIL_JSON;
	static {
		Result result = new Result();
		result.setError_no(10001);
		result.setError("激活时间已经过期，请重新注册");
		EXPIRED_MAIL_JSON = JSON.toJSONString(result);

		result.setError_no(0);
		result.setMsg("成功发送激活邮件");
		SUCC_MAIL_JSON = JSON.toJSONString(result);

		result.setError_no(10002);
		result.setError("发送激活邮件失败，请检查您的邮箱是否正确，或者联系管理员");
		FAIL_MAIL_JSON = JSON.toJSONString(result);

	}

	@RequestMapping("sendEmailForRegister")
	public void sendRegisterMail(HttpServletResponse response,HttpSession session,
			HttpServletRequest request) throws Exception {
		Object tmpUser;
		if ((tmpUser = session.getAttribute("inactiveAccount")) == null) {
			writeJson(EXPIRED_MAIL_JSON,response);
		} else {
			String targetUrl = request.getRequestURL().toString();
			String targetUri = request.getRequestURI().toString();
			String contextPath = request.getServletContext().getContextPath();

			StringBuilder url = new StringBuilder(targetUrl);
			StringBuilder tempContextUrl = url
					.delete(url.length() - targetUri.length(), url.length())
					.append(contextPath).append("");
			tempContextUrl.append("/register/authenticateEmail");

			boolean succ = registerService.sendRegisterMail(
					tempContextUrl.toString(), (InactiveAccount) tmpUser);
			if (succ) {
			   writeJson(SUCC_MAIL_JSON, response);
				return;
			} else {
				writeJson(FAIL_MAIL_JSON, response);
				return;
			}
		}
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
