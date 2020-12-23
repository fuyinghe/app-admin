package com.hrbwmxx.system.model;

/**
 * app类型管理
 * @ClassName:  AppType   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @date:   2018年10月18日 
 * @author: CHIUCLOUD(云)    
 * @Copyright: Mr丶ZHAO. All rights reserved. 
 *
 */
public class AppType {
	
	private String id;//id
	private String app_type; //类型
	private String	des; //描述
	private String	px; //排序
	private String state; //状态
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getApp_type() {
		return app_type;
	}
	public void setApp_type(String app_type) {
		this.app_type = app_type;
	}
	public String getDes() {
		return des;
	}
	public void setDes(String des) {
		this.des = des;
	}
	public String getPx() {
		return px;
	}
	public void setPx(String px) {
		this.px = px;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
}
