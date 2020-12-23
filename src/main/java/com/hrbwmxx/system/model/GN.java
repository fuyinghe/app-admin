package com.hrbwmxx.system.model;

import java.io.Serializable;

public class GN implements Serializable{
	
	private static final long serialVersionUID = 4903568154935804811L;
	
	private String gnId; //主键
	private String mc; 	//名称
	private String pId; //上级主键
	private String url; //页面路径
	private String tbMc; //图标名称
	private String ms; //描述
	private String px; //排序
	private String zt; //状态
	private String dj; //等级
	private String dm; //代码
	private String lb; //类别
	private String sjMc; //上级名称
	
	public String getGnId() {
		return gnId;
	}
	public void setGnId(String gnId) {
		this.gnId = gnId;
	}
	public String getMc() {
		return mc;
	}
	public void setMc(String mc) {
		this.mc = mc;
	}
	public String getpId() {
		return pId;
	}
	public void setpId(String pId) {
		this.pId = pId;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getTbMc() {
		return tbMc;
	}
	public void setTbMc(String tbMc) {
		this.tbMc = tbMc;
	}
	public String getMs() {
		return ms;
	}
	public void setMs(String ms) {
		this.ms = ms;
	}
	public String getPx() {
		return px;
	}
	public void setPx(String px) {
		this.px = px;
	}
	public String getZt() {
		return zt;
	}
	public void setZt(String zt) {
		this.zt = zt;
	}
	public String getDj() {
		return dj;
	}
	public void setDj(String dj) {
		this.dj = dj;
	}
	public String getDm() {
		return dm;
	}
	public void setDm(String dm) {
		this.dm = dm;
	}
	public String getLb() {
		return lb;
	}
	public void setLb(String lb) {
		this.lb = lb;
	}
	public String getSjMc() {
		return sjMc;
	}
	public void setSjMc(String sjMc) {
		this.sjMc = sjMc;
	}
	
	
}
