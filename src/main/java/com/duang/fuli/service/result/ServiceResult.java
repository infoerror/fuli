package com.duang.fuli.service.result;

/**
 * 
 * @author zgq
 * @date 2016年3月19日 下午1:54:24
 */
public class ServiceResult {
	private int code;
	private String msg;
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	protected static final <T> T USER_NO_LOGIN(Class<?> resClass){
		return CommonResults.USER_NO_LOGIN(resClass);
	}
}
