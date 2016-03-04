package com.duang.fuli.domain.json;

public class Result {

	private int error_no;
	private String error;
	private String msg;

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}


	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	public int getError_no() {
		return error_no;
	}
	public void setError_no(int error_no) {
		this.error_no = error_no;
	}

}
