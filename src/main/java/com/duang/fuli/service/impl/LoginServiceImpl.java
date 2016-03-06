package com.duang.fuli.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.duang.fuli.dao.UserDao;
import com.duang.fuli.domain.User;
import com.duang.fuli.domain.form.LoginForm;
import com.duang.fuli.domain.json.LoginJson;
import com.duang.fuli.service.LoginService;
import com.duang.fuli.service.VerifyAccountService;
import com.duang.fuli.service.result.LoginResult;
import com.duang.fuli.service.result.VerifyAccountResult;
import com.duang.fuli.service.result.LoginResult.LOGIN_RESULT;
import com.duang.fuli.utils.JsonUtils;
import com.duang.fuli.utils.MD5Utils;

@Service("loginService")
public class LoginServiceImpl implements LoginService{

	@Resource(name="userDao")
	private UserDao userDao;
	
	@Resource(name="verifyAccountService")
	private VerifyAccountService verifyAccountService;
	 
	private static final String LOGIN_SUCC_JSON;
	private static final String LOGIN_FAIL_JSON;
	
    static{
    	 LoginJson loginJson = new LoginJson();
    	 loginJson.setError_no(0);
    	 loginJson.setMsg("登陆成功");
    	 LOGIN_SUCC_JSON = JsonUtils.toString(loginJson);
    	 
    	 loginJson.setError_no(100001);
    	 loginJson.setError("密码或用户名错误！");
    	 LOGIN_FAIL_JSON = JsonUtils.toString(loginJson);
    }

	@Override
	public LoginResult login(LoginForm loginForm) {
		
		LoginResult  loginResult = new LoginResult();
		VerifyAccountResult verifyAccountResult = verifyAccountService.verifyLogin(loginForm);
		
		if(!verifyAccountResult.isPassVerification()){
			loginResult.setJson(verifyAccountResult.getJson());
			return loginResult;
		}
		 
		User loginUser= new User();
		loginUser.setUsername(loginForm.getUsername());
		String encryptedPassword=MD5Utils.md5(loginForm.getPassword());
		loginUser.setPassword(encryptedPassword);
		//start login
		User user=userDao.login(loginUser);
		if(user==null){
			loginResult.setJson(LOGIN_FAIL_JSON);
			return loginResult;
		}
		
		//login successfully
		loginResult.setUser(user);
		loginResult.setResult(LOGIN_RESULT.SUCCESS);
		loginResult.setJson(LOGIN_SUCC_JSON);
		
		return loginResult;
	}

}
