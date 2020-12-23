package com.hrbwmxx.system.vo;

import com.hrbwmxx.system.model.Code;

public class CodeCustom extends Code{
	
	private String dmlx;//代码类型
	private String lxmc; //类型名称
	private String lxfhdm; //类型返回代码
	private String smc; //省名称
	private String xmc; //市名称
	
	public String getSmc() {
		return smc;
	}
	public void setSmc(String smc) {
		this.smc = smc;
	}
	public String getXmc() {
		return xmc;
	}
	public void setXmc(String xmc) {
		this.xmc = xmc;
	}
	public String getLxmc() {
		return lxmc;
	}
	public void setLxmc(String lxmc) {
		this.lxmc = lxmc;
	}
	public String getLxfhdm() {
		return lxfhdm;
	}
	public void setLxfhdm(String lxfhdm) {
		this.lxfhdm = lxfhdm;
	}
	public String getDmlx() {
		return dmlx;
	}
	public void setDmlx(String dmlx) {
		this.dmlx = dmlx;
	}
	
	
}
