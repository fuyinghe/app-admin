package com.webmos.framework.model;

public interface Result {
	
	//以下是业务返回数据响应吗和响应信息
	public String getCode();
	
	public void setCode(String code);
	
	public String getMessage();
	
	public void setMessage(String message);
	
	//以下是系统错误返回数据和响应信息
	
	public String getErrcode();
	
	public void setErrcode(String errcode);
	
	public String getErrmsg();
	
	public void setErrmsg(String errmsg);
	
}
