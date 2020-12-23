package com.hrbwmxx.system.model;

import java.io.Serializable;

/**
 * 
* @title: JS 
* @description：角色实体类
* @author： 李静雨
* @date： 2018年1月12日 上午10:32:03
 */
public class JS implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1286790320856471313L;
	private String jsId;//角色ID；主键；YYYYMMDDHHMMSS
	private String mc;//名称
	private String xh;//序号
	private String jsDm;//角色代码
	private String ymId;//页面ID
	private String zt;//状态；N：停用；Y：使用
	public String getJsDm() {
		return jsDm;
	}
	public void setJsDm(String jsDm) {
		this.jsDm = jsDm;
	}
	public String getJsId() {
		return jsId;
	}
	public void setJsId(String jsId) {
		this.jsId = jsId;
	}
	public String getMc() {
		return mc;
	}
	public void setMc(String mc) {
		this.mc = mc;
	}
	public String getXh() {
		return xh;
	}
	public void setXh(String xh) {
		this.xh = xh;
	}
	public String getYmId() {
		return ymId;
	}
	public void setYmId(String ymId) {
		this.ymId = ymId;
	}
	public String getZt() {
		return zt;
	}
	public void setZt(String zt) {
		this.zt = zt;
	}
	
}
