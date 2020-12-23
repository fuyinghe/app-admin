package com.hrbwmxx.system.model;


public class Code {
	
	private String  dm;    	//ID
	private String  dmlx;  //代码类型编码
	private String	mc ;    //名称
	private int  px ;	//排序
	private String  state ; 	//状态 0：停用；1：使用
	private String  fhdm ;	//返回代码
	private String  sjdm ;	//上级代码
	private String  sjz  ; 	//上级值
	private String  bzdm ;	//标准代码dmlx
	
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
	public String getSjdm() {
		return sjdm;
	}
	public void setSjdm(String sjdm) {
		this.sjdm = sjdm;
	}
	public String getSjz() {
		return sjz;
	}
	public void setSjz(String sjz) {
		this.sjz = sjz;
	}
	public String getBzdm() {
		return bzdm;
	}
	public void setBzdm(String bzdm) {
		this.bzdm = bzdm;
	}
	public String getDmlx() {
		return dmlx;
	}
	public void setDmlx(String dmlx) {
		this.dmlx = dmlx;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
}
