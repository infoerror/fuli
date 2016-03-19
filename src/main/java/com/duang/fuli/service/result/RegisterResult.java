package com.duang.fuli.service.result;

import com.duang.fuli.domain.InactiveAccount;
import com.duang.fuli.domain.form.RegisterForm;
import com.duang.fuli.service.result.Protocols.Register;
import com.duang.fuli.utils.ValidatorUtils;

public class RegisterResult extends ServiceResult{
	
	public final static  RegisterResult EXIST_USER_ERROR;
	private final static RegisterResult USERNAME_FORMAT_ERROR;
	private final static RegisterResult PASSWORD_FORMAT_ERROR;
	private final static RegisterResult TWO_PASSWORD_INEQUAL_ERROR;	
	private final static RegisterResult CAPTCHA_ERROR;
	
	static {
		EXIST_USER_ERROR= new RegisterResult();
		EXIST_USER_ERROR.setCode(Register.EXIST_USER);
		EXIST_USER_ERROR.setMsg("用户已经存在");
		
		USERNAME_FORMAT_ERROR= new RegisterResult();
		USERNAME_FORMAT_ERROR.setCode(Register.USERNAME_FORMAT_ERROR);
		USERNAME_FORMAT_ERROR.setMsg("用户名只能是邮箱");
		
		PASSWORD_FORMAT_ERROR= new RegisterResult();
		PASSWORD_FORMAT_ERROR.setCode(Register.PASSWORD_FORMAT_ERROR);
		PASSWORD_FORMAT_ERROR.setMsg("密码只能是6到16位之间");
		
		TWO_PASSWORD_INEQUAL_ERROR = new RegisterResult();
		TWO_PASSWORD_INEQUAL_ERROR.setCode(Register.TWO_PASSWORD_INEQUAL_ERROR);
		TWO_PASSWORD_INEQUAL_ERROR.setMsg("两次密码不一致!");
		
		CAPTCHA_ERROR= new RegisterResult();
		CAPTCHA_ERROR.setCode(Register.CAPTCHA_ERROR);
		CAPTCHA_ERROR.setMsg("验证码错误!");

	}
	
	private InactiveAccount inactiveAccount;
	
	public InactiveAccount getInactiveAccount() {
		return inactiveAccount;
	}

	public void setInactiveAccount(InactiveAccount inactiveAccount) {
		this.inactiveAccount = inactiveAccount;
	}
	
	public static RegisterResult validate(RegisterForm registerForm){
		String username = registerForm.getUsername();
		String password = registerForm.getPassword();
		String confirmPassword = registerForm.getConfirmPassword();
		String captcha =  registerForm.getCaptcha();
		String rightCaptcha = registerForm.getRightCaptcha();

		if(!ValidatorUtils.isEmail(username)){
			return USERNAME_FORMAT_ERROR;
		}
		
		if(password==null || password.length()<6 && password.length()>16){
			return PASSWORD_FORMAT_ERROR;
		}
		if(confirmPassword==null || !password.equals(confirmPassword)){
			return TWO_PASSWORD_INEQUAL_ERROR;
		}
		
		if(rightCaptcha ==null || !rightCaptcha.equals(captcha)){
		    return CAPTCHA_ERROR;
		}
	
		return null;
	}

	public static RegisterResult succ(InactiveAccount inactiveAccount) {
		RegisterResult succ= new RegisterResult();
		succ.setCode(Register.SUCC);
		succ.setInactiveAccount(inactiveAccount);
		succ.setMsg("注册成功!");
		return null;
	}

	public boolean isRegisterSuccessful() {
	     return getCode() == Register.SUCC;
	}


}
