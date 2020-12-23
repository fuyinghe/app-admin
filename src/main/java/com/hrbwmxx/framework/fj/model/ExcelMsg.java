package com.hrbwmxx.framework.fj.model;

public class ExcelMsg {
	private boolean success;//返回信息成功失败
	private String msg;//返回信息通知
	 
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
}
