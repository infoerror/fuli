package com.duang.fuli.service.impl;

import java.sql.Timestamp;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.hibernate.annotations.ResultCheckStyle;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.duang.fuli.dao.InactiveAccountDao;
import com.duang.fuli.dao.UserDao;
import com.duang.fuli.domain.InactiveAccount;
import com.duang.fuli.domain.User;
import com.duang.fuli.domain.form.RegisterForm;
import com.duang.fuli.domain.json.Result;
import com.duang.fuli.service.RegisterService;
import com.duang.fuli.service.VerifyAccountService;
import com.duang.fuli.service.result.RegisterResult;
import com.duang.fuli.service.result.RegisterResult.REGISTER_RESULT;
import com.duang.fuli.service.result.SendRegisterEmailResult;
import com.duang.fuli.service.result.VerifyAccountResult;
import com.duang.fuli.utils.EmailUtil;
import com.duang.fuli.utils.MD5Utils;

@Service("registerService")
public class RegisterServiceImpl implements RegisterService {

	private static final Logger LOG = Logger
			.getLogger(RegisterServiceImpl.class);

	@Resource(name = "userDao")
	private UserDao userDao;
	@Resource(name = "inactiveAccountDao")
	private InactiveAccountDao inactiveAccountDao;
	@Resource(name = "verifyAccountService")
	private VerifyAccountService verifyAccountService;

	private final static String SUCC_REG_JSON;

	static {
		Result result = new Result();
		result.setError_no(0);
		result.setMsg("注册成功!");
		SUCC_REG_JSON = JSON.toJSONString(result);
		RegisterResult registerResult = new RegisterResult();
		registerResult.setResult(REGISTER_RESULT.SUCCESS);
		registerResult.setJson(SUCC_REG_JSON);;
		
		RegisterResult error= new RegisterResult();
		error.setResult(REGISTER_RESULT.SUCCESS);
		error.setJson(SUCC_REG_JSON);;	
	}

	@Override
	public RegisterResult register(RegisterForm registerForm) {
		if (LOG.isDebugEnabled())
			LOG.debug("register user-->" + registerForm);
		RegisterResult registerResult = new RegisterResult();
		
		VerifyAccountResult verifyAccountResult=verifyAccountService.verifyRegister(registerForm);
		if(!verifyAccountResult.isPassVerification()){
                registerResult.setJson(verifyAccountResult.getJson());
                return registerResult;
		}

		inactiveAccountDao.deleteByUsername(registerForm.getUsername());
		long registerTime = System.currentTimeMillis();
		InactiveAccount inactiveAccount = new InactiveAccount();
		inactiveAccount.setUsername(registerForm.getUsername());
		inactiveAccount.setPassword(registerForm.getPassword());
		inactiveAccount.setRegisterTime(System.currentTimeMillis());
		inactiveAccount
				.setToken(MD5Utils.md5(registerForm.getUsername() + registerTime));
		inactiveAccountDao.saveInactiveAccount(inactiveAccount);
		registerResult.setResult(REGISTER_RESULT.SUCCESS);
		registerResult.setInactiveAccount(inactiveAccount);
		registerResult.setJson(SUCC_REG_JSON);

		return registerResult;
	}

	@Override
	public SendRegisterEmailResult sendRegisterMail(String actionUrl,
			InactiveAccount inactiveAccount) throws Exception {

		return sendMessageToClient(actionUrl, inactiveAccount);
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

	private SendRegisterEmailResult sendMessageToClient(String actionUrl,
			InactiveAccount inactiveAccount) throws Exception {
		SendRegisterEmailResult result = new SendRegisterEmailResult();
		if (inactiveAccount == null) {
			result.setJson(EXPIRED_MAIL_JSON);
			return result;
		}

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
			result.setJson(SUCC_MAIL_JSON);
			return result;
		} catch (Exception e) {
			LOG.warn("can't send register mail!", e);
			result.setJson(FAIL_MAIL_JSON);
			return result;
		}

	}

	@Override
	public boolean authenticateEmail(String token) {
		InactiveAccount account = inactiveAccountDao.getByToken(token);
		if (account == null) {
			return false;
		}

		User user = new User();
		user.setUsername(account.getUsername());
		Timestamp registerTime = new Timestamp(account.getRegisterTime());
		user.setRegisterTime(registerTime);
		user.setNickname("新用户" + token.substring(0, 5));
		user.setPassword(MD5Utils.md5(account.getPassword()));
		inactiveAccountDao.deleteByUsername(account.getUsername());
		userDao.saveUser(user);
		return true;
	}

}
