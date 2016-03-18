package com.duang.fuli.domain.form;

import com.duang.fuli.domain.User;

public class BasicInfoForm {
	
	private User user;
	private String nickname;
	private String signature;
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getSignature() {
		return signature;
	}
	public void setSignature(String signature) {
		this.signature = signature;
	}

}
