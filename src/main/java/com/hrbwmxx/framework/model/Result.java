package com.hrbwmxx.framework.model;

public interface Result {

	//以下是系统错误返回数据和响应信息
	
	public String getErrcode();
	
	public void setErrcode(String errcode);
	
	public String getErrmsg();
	
	public void setErrmsg(String errmsg);
	
}
