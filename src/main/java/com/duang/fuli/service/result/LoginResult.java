package com.duang.fuli.service.result;

import com.duang.fuli.domain.User;
import com.duang.fuli.domain.form.LoginForm;
import com.duang.fuli.service.result.Protocols.Login;
import com.duang.fuli.utils.ValidatorUtils;

public class LoginResult extends ServiceResult
{

	public static final LoginResult USERNAME_OR_PASSWORD_ERROR;
	private static final LoginResult USERNAME_FORMAT_ERROR;
	private static final LoginResult PASSWORD_FORMAT_ERROR;
	private static final LoginResult CAPTCHA_ERROR;
    static{

    	
    	USERNAME_OR_PASSWORD_ERROR = new LoginResult();
    	USERNAME_OR_PASSWORD_ERROR.setCode(Login.USERNAME_OR_PASSWORD_ERROR);
    	USERNAME_OR_PASSWORD_ERROR.setMsg("密码或用户名错误！");
    	
    	USERNAME_FORMAT_ERROR=new LoginResult();
    	USERNAME_FORMAT_ERROR.setCode(Login.USERNAME_FORMAT_ERROR);
    	USERNAME_FORMAT_ERROR.setMsg("登录用户名只能是邮箱哦!");
    	
    	PASSWORD_FORMAT_ERROR=new LoginResult();
    	PASSWORD_FORMAT_ERROR.setCode(Login.PASSWORD_FORMAT_ERROR);
    	PASSWORD_FORMAT_ERROR.setMsg("登录用户名只能是邮箱哦!");
    	
    	CAPTCHA_ERROR=new LoginResult();
    	CAPTCHA_ERROR.setCode(Login.CAPTCHA_ERROR);
    	CAPTCHA_ERROR.setMsg("登录验证码错误！");	
    }
    
    private User user;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
    
    public static LoginResult validate(LoginForm loginForm){
    	
		String username = loginForm.getUsername();
		String password = loginForm.getPassword();
		String captcha = loginForm.getCaptcha();
	    String rightCaptcha  = loginForm.getRightCaptcha();
		
		if(!ValidatorUtils.isEmail(username)){
			return USERNAME_FORMAT_ERROR;
		}
		
		if(password==null || password.length()<6 && password.length()>16){
			return PASSWORD_FORMAT_ERROR;
		}
		
		if(rightCaptcha ==null || !rightCaptcha.equals(captcha)){
		    return CAPTCHA_ERROR;
		}
		
		return null;
    }

	public static LoginResult succ(User user) {
    	LoginResult LOGIN_SUCC = new LoginResult();
    	LOGIN_SUCC.setCode(Login.SUCC);
    	LOGIN_SUCC.setMsg("登陆成功");
    	LOGIN_SUCC.setUser(user);
    	return LOGIN_SUCC;
	}

	public boolean isLoginSuccessful() {
		return getCode() == Login.SUCC;
	}
	

}
