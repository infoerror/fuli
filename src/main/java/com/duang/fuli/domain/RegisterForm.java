package com.duang.fuli.domain;

import java.io.Serializable;

public class RegisterForm implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String username;
	private String password;
	private String confirmPassword;
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
	public String getConfirmPassword() {
		return confirmPassword;
	}
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
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
