package com.hrbwmxx.framework.model;

/**
 * token转userid实体
 * @author lenovo
 *
 */
public class TokenUser {
	
	private String errormsg;
	private String errorcode;
	private String data;
	
	public String getErrormsg() {
		return errormsg;
	}
	public void setErrormsg(String errormsg) {
		this.errormsg = errormsg;
	}
	public String getErrorcode() {
		return errorcode;
	}
	public void setErrorcode(String errorcode) {
		this.errorcode = errorcode;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	
	

}
