package com.duang.fuli.service.impl;

import java.sql.Timestamp;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.duang.fuli.dao.InactiveAccountDao;
import com.duang.fuli.dao.UserDao;
import com.duang.fuli.domain.InactiveAccount;
import com.duang.fuli.domain.RegisterForm;
import com.duang.fuli.domain.User;
import com.duang.fuli.domain.json.RegisterJson;
import com.duang.fuli.service.RegisterService;
import com.duang.fuli.service.result.RegisterResult;
import com.duang.fuli.service.result.RegisterResult.REGISTER_RESULT;
import com.duang.fuli.utils.EmailUtil;
import com.duang.fuli.utils.MD5Utils;
import com.duang.fuli.utils.ValidatorUtils;

@Service("registerService")
public class RegisterServiceImpl implements RegisterService {

	private static final Logger LOG = Logger.getLogger(RegisterServiceImpl.class);

	@Resource(name="userDao")
	private UserDao userDao;
	@Resource(name="inactiveAccountDao")
	private InactiveAccountDao inactiveAccountDao;

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
		
		registerMessage.setError_no(20001);
		registerMessage.setError("密码只能是6到16位之间");
		PASSWORD_FORMAT_ERROR_JSON = JSON.toJSONString(registerMessage);
		
		registerMessage.setError_no(20002);
		registerMessage.setError("两次密码不一致!");
		TWO_PASSWORD_INEQUAL_ERROR_JSON= JSON.toJSONString(registerMessage);
		
		registerMessage.setError_no(30001);
		registerMessage.setError("验证码错误!");
	    CAPTCHA_ERROR_JSON= JSON.toJSONString(registerMessage);
			
		registerMessage.setError_no(-1);
		registerMessage.setError("暂时无法注册，请稍后重试！");
		OTHER_ERROR_JSON = JSON.toJSONString(registerMessage);

	}

	
	@Override
	public RegisterResult register(RegisterForm user) {
		if (LOG.isDebugEnabled())
			LOG.debug("register user-->" + user);
		
		String username = user.getUsername();
		String password = user.getPassword();
		String confirmPassword = user.getConfirmPassword();
		String captcha =  user.getCaptcha();
		String rightCaptcha = user.getRightCaptcha();
		
		RegisterResult result = new RegisterResult();

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
	
		User u = userDao.getUserByUsername(user.getUsername());
		if (u != null) {
			result.setJson(EXIST_USER_ERROR_JSON);
			result.setResult(REGISTER_RESULT.EXIST_USER);
			return result;
		}

		inactiveAccountDao.deleteByUsername(user.getUsername());
		long registerTime =System.currentTimeMillis(); 
		InactiveAccount inactiveAccount = new InactiveAccount();
		inactiveAccount.setUsername(user.getUsername());
		inactiveAccount.setPassword(user.getPassword());
		inactiveAccount.setRegisterTime(System.currentTimeMillis());
		inactiveAccount.setToken(MD5Utils.md5(user.getUsername()
				+registerTime));
		inactiveAccountDao.saveInactiveAccount(inactiveAccount);
		
		result.setResult(REGISTER_RESULT.SUCCESS);
		result.setInactiveAccount(inactiveAccount);
        result.setJson(SUCC_REG_JSON);
		
		return result;
	}

	@Override
	public boolean sendRegisterMail(String actionUrl, InactiveAccount inactiveAccount)
			throws Exception {
		return sendMessageToClient(actionUrl, inactiveAccount);
	}

	private boolean sendMessageToClient(String actionUrl,
			InactiveAccount inactiveAccount) throws Exception {
		StringBuilder content = new StringBuilder();
		StringBuilder tempContextUrl = new StringBuilder(actionUrl);
		tempContextUrl.append("?token=");
		tempContextUrl.append(inactiveAccount.getToken());
		String urlStr = tempContextUrl.toString();
		content.append("<a href=\"");
		content.append(urlStr);
		content.append("\">");
		content.append(urlStr);
		content.append("</a>");
		try {
			EmailUtil.sendEmail(inactiveAccount.getUsername(), "点击链接验证您的账号",
					content.toString());
		} catch (Exception e) {
			LOG.warn("can't send register mail!", e);
			return false;
		}

		return true;
	}

	@Override
	public boolean authenticateEmail(String token) {
		InactiveAccount account = inactiveAccountDao.getByToken(token);
		if (account == null) {
			return false;
		}

		User user = new User();
		user.setUsername(account.getUsername());
		Timestamp registerTime=new Timestamp(account.getRegisterTime());
		user.setRegisterTime(registerTime);
		user.setNickname("新用户" + token.substring(0, 5));
		user.setPassword(MD5Utils.md5(account.getPassword()));
		inactiveAccountDao.deleteByUsername(account.getUsername());
		userDao.saveUser(user);
		return true;
	}

}
