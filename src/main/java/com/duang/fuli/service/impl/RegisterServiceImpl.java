package com.duang.fuli.service.impl;

import java.sql.Timestamp;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.duang.fuli.dao.InactiveAccountDao;
import com.duang.fuli.dao.UserDao;
import com.duang.fuli.domain.InactiveAccount;
import com.duang.fuli.domain.User;
import com.duang.fuli.service.RegisterService;
import com.duang.fuli.service.result.RegisterResult;
import com.duang.fuli.service.result.RegisterResult.REGISTER_RESULT;
import com.duang.fuli.utils.EmailUtil;
import com.duang.fuli.utils.MD5Utils;

@Service("registerService")
public class RegisterServiceImpl implements RegisterService {

	private static final Logger LOG = Logger.getLogger(RegisterServiceImpl.class);

	@Resource(name="userDao")
	private UserDao userDao;
	@Resource(name="inactiveAccountDao")
	private InactiveAccountDao inactiveAccountDao;

	@Override
	public RegisterResult register(User user) {
		if (LOG.isDebugEnabled())
			LOG.debug("register user-->" + user);

		RegisterResult result = new RegisterResult();
		User u = userDao.getUserByUsername(user.getUsername());
		if (u != null) {
			result.setResult(REGISTER_RESULT.EXIST_USER);
			return result;
		}

		// must delete registered ago account of this username to avoid verify wrong account info  
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
