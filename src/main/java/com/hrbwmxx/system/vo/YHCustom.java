package com.hrbwmxx.system.vo;

import com.hrbwmxx.system.model.JS;
import com.hrbwmxx.system.model.YH;

public class YHCustom extends YH{
	
	private static final long serialVersionUID = -873857426079442364L;
	
	private String jsMc; //角色名称
	
	private String gnMc; //菜单名称
	
	private String jsmcList; //
	
	private String jsidList ; //
	
	/**
	 * 角色对象
	 */
	private JS js;
	
	public String getJsmcList() {
		return jsmcList;
	}
	public void setJsmcList(String jsmcList) {
		this.jsmcList = jsmcList;
	}
	public String getJsidList() {
		return jsidList;
	}
	public void setJsidList(String jsidList) {
		this.jsidList = jsidList;
	}
	public JS getJs() {
		return js;
	}
	public void setJs(JS js) {
		this.js = js;
	}
	public String getGnMc() {
		return gnMc;
	}
	public void setGnMc(String gnMc) {
		this.gnMc = gnMc;
	}
	public String getJsMc() {
		return jsMc;
	}
	public void setJsMc(String jsMc) {
		this.jsMc = jsMc;
	}
}
