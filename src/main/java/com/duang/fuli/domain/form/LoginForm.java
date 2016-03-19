package com.duang.fuli.domain.form;

/**
 * 
 * @author zgq
 * @date 2016年3月19日 下午1:56:31
 */
public class LoginForm {
	
	private String username;
	private String password;
	private String captcha;
	private String rightCaptcha;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getCaptcha() {
		return captcha;
	}
	public void setCaptcha(String captcha) {
		this.captcha = captcha;
	}
	public String getRightCaptcha() {
		return rightCaptcha;
	}
	public void setRightCaptcha(String rightCaptcha) {
		this.rightCaptcha = rightCaptcha;
	}
	

}
