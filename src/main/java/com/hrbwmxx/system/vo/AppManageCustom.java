package com.hrbwmxx.system.vo;

import java.util.List;
import java.util.Map;

import com.hrbwmxx.system.model.AppManage;

public class AppManageCustom extends AppManage{
	
	private String cityname;//城市名称
	private String openname; //打开类型名称
	private String sysname;//位置名称
	private String appId; //appID
	private String cityId;//城市ID
	private String app_type;//类型ID
	private String urltypename;//图片类型
	private String paytypemc;//缴费名称
	private String attachId;//网络图片
	private String apple;
	private String adr;
	private String wx;
	
	public String getApple() {
		return apple;
	}
	public void setApple(String apple) {
		this.apple = apple;
	}
	public String getAdr() {
		return adr;
	}
	public void setAdr(String adr) {
		this.adr = adr;
	}
	public String getWx() {
		return wx;
	}
	public void setWx(String wx) {
		this.wx = wx;
	}
	private List<Map<String, String>> list_file_pic;
	
	
	public List<Map<String, String>> getList_file_pic() {
		return list_file_pic;
	}
	public void setList_file_pic(List<Map<String, String>> list_file_pic) {
		this.list_file_pic = list_file_pic;
	}
	public String getAttachId() {
		return attachId;
	}
	public void setAttachId(String attachId) {
		this.attachId = attachId;
	}
	public String getCityname() {
		return cityname;
	}
	public void setCityname(String cityname) {
		this.cityname = cityname;
	}
	public String getOpenname() {
		return openname;
	}
	public void setOpenname(String openname) {
		this.openname = openname;
	}
	public String getSysname() {
		return sysname;
	}
	public void setSysname(String sysname) {
		this.sysname = sysname;
	}
	public String getAppId() {
		return appId;
	}
	public void setAppId(String appId) {
		this.appId = appId;
	}
	public String getCityId() {
		return cityId;
	}
	public void setCityId(String cityId) {
		this.cityId = cityId;
	}
	public String getApp_type() {
		return app_type;
	}
	public void setApp_type(String app_type) {
		this.app_type = app_type;
	}
	public String getUrltypename() {
		return urltypename;
	}
	public void setUrltypename(String urltypename) {
		this.urltypename = urltypename;
	}
	public String getPaytypemc() {
		return paytypemc;
	}
	public void setPaytypemc(String paytypemc) {
		this.paytypemc = paytypemc;
	}

}
