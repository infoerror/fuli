package com.duang.fuli.service.result;

import com.duang.fuli.domain.User;

public class LoginResult {
	
	public static enum LOGIN_RESULT{
		
		SUCCESS
	}
	
	private User user;
	private String json;
	private LOGIN_RESULT result;

	public String getJson() {
		return json;
	}

	public void setJson(String json) {
		this.json = json;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public LOGIN_RESULT getResult() {
		return result;
	}

	public void setResult(LOGIN_RESULT result) {
		this.result = result;
	}
	

}
