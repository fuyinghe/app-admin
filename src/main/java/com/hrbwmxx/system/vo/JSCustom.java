package com.hrbwmxx.system.vo;

import com.hrbwmxx.system.model.JS;

public class JSCustom extends JS {
	
	private static final long serialVersionUID = -4973600945364425590L;

	private String yhMc ; // 用户名称
	
	private String gnMc ; // 菜单名称

	private String pId ; // 菜单父级ID 	
	public String getpId() {
		return pId;
	}

	public void setpId(String pId) {
		this.pId = pId;
	}

	public String getGnMc() {
		return gnMc;
	}

	public void setGnMc(String gnMc) {
		this.gnMc = gnMc;
	}

	public String getYhMc() {
		return yhMc;
	}

	public void setYhMc(String yhMc) {
		this.yhMc = yhMc;
	}
	
}
