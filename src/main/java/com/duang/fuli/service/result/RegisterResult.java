package com.duang.fuli.service.result;

import com.duang.fuli.domain.InactiveAccount;

public class RegisterResult {
	
	public static enum REGISTER_RESULT{
		SUCCESS,EXIST_USER
	}
	
	private  REGISTER_RESULT result;
	
	private InactiveAccount inactiveAccount;

	public REGISTER_RESULT getResult() {
		return result;
	}

	public void setResult(REGISTER_RESULT result) {
		this.result = result;
	}

	public InactiveAccount getInactiveAccount() {
		return inactiveAccount;
	}

	public void setInactiveAccount(InactiveAccount inactiveAccount) {
		this.inactiveAccount = inactiveAccount;
	}


}