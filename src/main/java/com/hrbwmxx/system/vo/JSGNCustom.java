package com.hrbwmxx.system.vo;

import com.hrbwmxx.system.model.JSGN;

public class JSGNCustom extends JSGN{
	//角色名称
	private String jsmc;
	//菜单名称
	private String  cdmc;
	public String getJsmc() {
		return jsmc;
	}
	public void setJsmc(String jsmc) {
		this.jsmc = jsmc;
	}
	public String getCdmc() {
		return cdmc;
	}
	public void setCdmc(String cdmc) {
		this.cdmc = cdmc;
	}
	
}
