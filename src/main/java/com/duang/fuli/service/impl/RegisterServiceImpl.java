package com.duang.fuli.service.impl;

import java.sql.Timestamp;
import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.duang.fuli.dao.InactiveAccountDao;
import com.duang.fuli.dao.UserDao;
import com.duang.fuli.domain.InactiveAccount;
import com.duang.fuli.domain.User;
import com.duang.fuli.domain.UserInfo;
import com.duang.fuli.domain.form.RegisterForm;
import com.duang.fuli.service.RegisterService;
import com.duang.fuli.service.result.RegisterResult;
import com.duang.fuli.service.result.SendRegisterEmailResult;
import com.duang.fuli.utils.EmailUtil;
import com.duang.fuli.utils.MD5Utils;
/**
 * 
 * @author zgq
 * @date 2016年3月19日 下午1:53:00
 */
@Service("registerService")
public class RegisterServiceImpl implements RegisterService {

	private static final Logger LOG = Logger
			.getLogger(RegisterServiceImpl.class);

	@Resource(name = "userDao")
	private UserDao userDao;
	@Resource(name = "inactiveAccountDao")
	private InactiveAccountDao inactiveAccountDao;
	
	/**
	 * 也可以用aop验证
	 */
	@Override
	public RegisterResult register(RegisterForm registerForm) {
	
		RegisterResult registerResult = RegisterResult.validate(registerForm);
		if(registerResult!=null){
			return registerResult;
		}
		if (LOG.isDebugEnabled())
			LOG.debug("register user-->" + registerForm);
		User u = userDao.getUserByUsername(registerForm.getUsername());
		if (u != null) {
			return RegisterResult.EXIST_USER_ERROR;
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
		return RegisterResult.succ(inactiveAccount);
	}

	@Override
	public SendRegisterEmailResult sendRegisterMail(String actionUrl,
			InactiveAccount inactiveAccount) throws Exception {

		return sendMessageToClient(actionUrl, inactiveAccount);
	}


	private SendRegisterEmailResult sendMessageToClient(String actionUrl,
			InactiveAccount inactiveAccount) throws Exception {
		if (inactiveAccount == null) {
			return SendRegisterEmailResult.EXPIRED_MAIL;
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
           return SendRegisterEmailResult.SUCC_MAIL;
		} catch (Exception e) {
			LOG.warn("can't send register mail!", e);
			return SendRegisterEmailResult.FAIL_MAIL;
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
		
		user.setPassword(MD5Utils.md5(account.getPassword()));
		inactiveAccountDao.deleteByUsername(account.getUsername());
		user.setNickname("新用户" + token.substring(0, 5));
		userDao.saveUser(user);
		
		UserInfo userInfo  =new UserInfo();
		userInfo.setRegisterTime(registerTime);
		userInfo.setUser(user);
		userDao.saveUserInfo(userInfo);
		
		return true;
	}

}
