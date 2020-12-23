package com.webmos.framework.model;

public class ResultEntity implements Result {
	 
	private String code = "200";
	private String message ="操作成功";
	
	private String errcode = "0";
	private String errmsg ="操作成功";
	
	 
	 
	//private List<?> list;

	//public List<?> getList() {
	//	return list;
	//}
	//public void setList(List<?> list) {
	//	this.list = list;
	//}
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
