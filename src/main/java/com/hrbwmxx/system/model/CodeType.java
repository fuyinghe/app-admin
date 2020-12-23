package com.hrbwmxx.system.model;


public class CodeType {
	private String id;//代码表ID；主键
	private String dm;//代码表名称
	private String mc;//名称
	private String state;//状态：0：停用；1：使用
	private int px;//序号
	private String fhdm ;//类型简称
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDm() {
		return dm;
	}
	public void setDm(String dm) {
		this.dm = dm;
	}
	public String getMc() {
		return mc;
	}
	public void setMc(String mc) {
		this.mc = mc;
	}
	public int getPx() {
		return px;
	}
	public void setPx(int px) {
		this.px = px;
	}
	public String getFhdm() {
		return fhdm;
	}
	public void setFhdm(String fhdm) {
		this.fhdm = fhdm;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	
}
