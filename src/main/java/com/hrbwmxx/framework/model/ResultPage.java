package com.hrbwmxx.framework.model;

public class ResultPage extends Page implements Result{
	private String errcode = "0";
	private String errmsg ="操作成功";	
	
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
	
}
