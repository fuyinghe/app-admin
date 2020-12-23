package com.webmos.framework.model;

public class ResultPage extends Page implements Result{
	private String errcode = "200";
	private String errmsg ="操作成功";
	
	private String code = "200";
	
	private String message ="操作成功";
	
	
	public String getErrcode() {
		return errcode;
	}

	 
	public void setErrcode(String errcode) {
		this.errcode = errcode;
	}


	public String getErrmsg() {
		return errmsg;
	}


	public void setErrmsg(String errmsg) {
		this.errmsg = errmsg;
	}

 

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	
}
