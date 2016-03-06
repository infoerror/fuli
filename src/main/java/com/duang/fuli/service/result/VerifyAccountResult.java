package com.duang.fuli.service.result;

public class VerifyAccountResult {
	
	private boolean passVerification=false;
	
	private String json;

	public boolean isPassVerification() {
		return passVerification;
	}

	public void setPassVerification(boolean passVerification) {
		this.passVerification = passVerification;
	}

	public String getJson() {
		return json;
	}

	public void setJson(String json) {
		this.json = json;
	}
	

}
