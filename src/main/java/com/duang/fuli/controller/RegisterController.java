package com.duang.fuli.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.duang.fuli.domain.InactiveAccount;
import com.duang.fuli.domain.User;
import com.duang.fuli.domain.json.RegisterJson;
import com.duang.fuli.domain.json.Result;
import com.duang.fuli.service.RegisterService;
import com.duang.fuli.service.result.RegisterResult;
import com.duang.fuli.utils.CaptchaUtils;
import com.duang.fuli.utils.ValidatorUtils;

@Controller
@Scope("prototype")
@RequestMapping(value = "/register")
public class RegisterController {
	private static final Logger LOG = Logger
			.getLogger(RegisterController.class);
	@Resource(name="registerService")
	private RegisterService registerService;
	
	@RequestMapping("/")
	public String index(){
		return "register/index";
	}

	@RequestMapping("authenticateEmail")
	public String authenticate(Model model, @RequestParam String token)
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

	@RequestMapping("sendRegisterMail")
	public String sendRegisterMail(HttpSession session,
			HttpServletRequest request) throws Exception {
		Object tmpUser;
		if ((tmpUser = session.getAttribute("inactiveAccount")) == null) {
			return (EXPIRED_MAIL_JSON);
		} else {
			String targetUrl = request.getRequestURL().toString();
			String targetUri = request.getRequestURI().toString();
			String contextPath = request.getServletContext().getContextPath();

			StringBuilder url = new StringBuilder(targetUrl);
			StringBuilder tempContextUrl = url
					.delete(url.length() - targetUri.length(), url.length())
					.append(contextPath).append("");
			tempContextUrl.append("/register/authenticate");

			boolean succ = registerService.sendRegisterMail(
					tempContextUrl.toString(), (InactiveAccount) tmpUser);
			if (succ) {
				return SUCC_MAIL_JSON;
			} else {
				return FAIL_MAIL_JSON;
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

	private final static String SUCC_REG_JSON;
	private final static String EXIST_USER_ERROR_JSON;
	private final static String USERNAME_ERROR_JSON;
	private final static String PASSWORD_FORMAT_ERROR_JSON;
	private final static String TWO_PASSWORD_INEQUAL_ERROR_JSON;	
	private final static String CAPTCHA_ERROR_JSON;
	private final static String OTHER_ERROR_JSON;

	static {
		RegisterJson registerMessage = new RegisterJson();
		registerMessage.setError_no(0);
		registerMessage.setMsg("注册成功!");
		SUCC_REG_JSON = JSON.toJSONString(registerMessage);

		registerMessage.setError_no(10001);
		registerMessage.setError("用户已经存在");
		EXIST_USER_ERROR_JSON = JSON.toJSONString(registerMessage);
		
		registerMessage.setError_no(10002);
		registerMessage.setError("用户名只能是邮箱");
		USERNAME_ERROR_JSON = JSON.toJSONString(registerMessage);
		
		registerMessage.setError_no(10003);
		registerMessage.setError("密码只能是6到16位之间");
		PASSWORD_FORMAT_ERROR_JSON = JSON.toJSONString(registerMessage);
		
		registerMessage.setError_no(10004);
		registerMessage.setError("两次密码不一致!");
		TWO_PASSWORD_INEQUAL_ERROR_JSON= JSON.toJSONString(registerMessage);
		
		registerMessage.setError_no(10005);
		registerMessage.setError("验证码错误!");
	    CAPTCHA_ERROR_JSON= JSON.toJSONString(registerMessage);
			
		registerMessage.setError_no(9999);
		registerMessage.setError("暂时无法注册，请稍后重试！");
		OTHER_ERROR_JSON = JSON.toJSONString(registerMessage);

	}

	@RequestMapping("/register")
	@ResponseBody
	public String register(HttpSession session, @RequestParam String username,@RequestParam String password,
			@RequestParam String confirmPassword	,@RequestParam String captcha	
			)
			throws Exception {

		if(!ValidatorUtils.isEmail(username)){
			return USERNAME_ERROR_JSON;
		}
		if(password==null || password.length()<6 && password.length()>16){
			return PASSWORD_FORMAT_ERROR_JSON;
		}
		if(confirmPassword==null || !password.equals(confirmPassword)){
			return TWO_PASSWORD_INEQUAL_ERROR_JSON;
		}
		String rightCaptcha=(String) session.getAttribute(CaptchaUtils.REGISTER_SESSION_FLAG);
		if(!rightCaptcha.equals(captcha)){
			return  CAPTCHA_ERROR_JSON;
		}
		
		User user = new User();
		user.setUsername(username);
		user.setPassword(password);
		RegisterResult registerResult = registerService.register(user);
		switch (registerResult.getResult()) {
		case SUCCESS:
			session.setAttribute("inactiveAccount", registerResult.getInactiveAccount());
			return SUCC_REG_JSON;
		case EXIST_USER:
			return EXIST_USER_ERROR_JSON;
		default:
			break;
		}
		return OTHER_ERROR_JSON;
	}

	@RequestMapping("/showCaptcha")
	public void showCaptcha(HttpServletResponse response,HttpSession session) throws Exception {
		CaptchaUtils.writeImg2Resp(response,session,CaptchaUtils.REGISTER_SESSION_FLAG);
	}

}
