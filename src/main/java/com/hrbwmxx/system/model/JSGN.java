package com.hrbwmxx.system.model;
/**
 * 
* @title: JSGN 
* @description：角色菜单中间表
* @author： 李静雨
* @date： 2018年1月17日 下午4:54:29
 */
public class JSGN {
	private String jsId;//角色ID；主键(关联T_JS)
	private String tId;//三级菜单ID；主键(关联T_SJCD)
	public String getJsId() {
		return jsId;
	}
	public void setJsId(String jsId) {
		this.jsId = jsId;
	}
	public String gettId() {
		return tId;
	}
	public void settId(String tId) {
		this.tId = tId;
	}
	
}
