package com.duang.fuli.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.duang.fuli.dao.UserDao;
import com.duang.fuli.domain.User;
import com.duang.fuli.domain.form.LoginForm;
import com.duang.fuli.domain.form.RegisterForm;
import com.duang.fuli.domain.json.Result;
import com.duang.fuli.service.VerifyAccountService;
import com.duang.fuli.service.result.VerifyAccountResult;
import com.duang.fuli.utils.ValidatorUtils;

@Service("verifyAccountService")
public class VerifyAccountServiceImpl implements VerifyAccountService{
	
	@Resource(name="userDao")
	private UserDao userDao;
	
	private final static String EXIST_USER_ERROR_JSON;
	private final static String USERNAME_ERROR_JSON;
	private final static String PASSWORD_FORMAT_ERROR_JSON;
	private final static String TWO_PASSWORD_INEQUAL_ERROR_JSON;	
	private final static String CAPTCHA_ERROR_JSON;
	
	static {
		Result result = new Result();

		result.setError_no(10001);
		result.setError("用户已经存在");
		EXIST_USER_ERROR_JSON = JSON.toJSONString(result);
		
		result.setError_no(10002);
		result.setError("用户名只能是邮箱");
		USERNAME_ERROR_JSON = JSON.toJSONString(result);
		
		result.setError_no(20001);
		result.setError("密码只能是6到16位之间");
		PASSWORD_FORMAT_ERROR_JSON = JSON.toJSONString(result);
		
		result.setError_no(20002);
		result.setError("两次密码不一致!");
		TWO_PASSWORD_INEQUAL_ERROR_JSON= JSON.toJSONString(result);
		
		result.setError_no(30001);
		result.setError("验证码错误!");
	    CAPTCHA_ERROR_JSON= JSON.toJSONString(result);

	}
	@Override
	public VerifyAccountResult verifyLogin(LoginForm loginForm) {
		
		String username = loginForm.getUsername();
		String password = loginForm.getPassword();
		String captcha = loginForm.getCaptcha();
	    String rightCaptcha  = loginForm.getRightCaptcha();
		
		VerifyAccountResult result = new VerifyAccountResult();
		if(!ValidatorUtils.isEmail(username)){
			result.setJson(USERNAME_ERROR_JSON);
			return result;
		}
		
		if(password==null || password.length()<6 && password.length()>16){
			result.setJson(PASSWORD_FORMAT_ERROR_JSON);
			return result;
		}
		
		if(rightCaptcha ==null || !rightCaptcha.equals(captcha)){
		    result.setJson(CAPTCHA_ERROR_JSON);
		    return result;
		}
		
		result.setPassVerification(true);
		
		return result;
	}
	
	
	@Override
	public VerifyAccountResult verifyRegister(RegisterForm registerForm) {
		
		VerifyAccountResult result= new VerifyAccountResult();		
		String username = registerForm.getUsername();
		String password = registerForm.getPassword();
		String confirmPassword = registerForm.getConfirmPassword();
		String captcha =  registerForm.getCaptcha();
		String rightCaptcha = registerForm.getRightCaptcha();

		if(!ValidatorUtils.isEmail(username)){
			result.setJson(USERNAME_ERROR_JSON);
			return result;
		}
		
		if(password==null || password.length()<6 && password.length()>16){
			result.setJson(PASSWORD_FORMAT_ERROR_JSON);
			return result;
		}
		if(confirmPassword==null || !password.equals(confirmPassword)){
			result.setJson(TWO_PASSWORD_INEQUAL_ERROR_JSON);
			return result;
		}
		
		if(rightCaptcha ==null || !rightCaptcha.equals(captcha)){
		    result.setJson(CAPTCHA_ERROR_JSON);
		    return result;
		}
	
		User u = userDao.getUserByUsername(registerForm.getUsername());
		if (u != null) {
			result.setJson(EXIST_USER_ERROR_JSON);
			return result;
		}
		result.setPassVerification(true);
		return result;
	}


}
