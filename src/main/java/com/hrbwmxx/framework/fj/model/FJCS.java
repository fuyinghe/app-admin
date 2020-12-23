package com.hrbwmxx.framework.fj.model;

import java.util.Date;
/**
 * 
* @title: FJCS 
* @description：附件参数表，文件上传时需要在数据库中插入数据
* @author： 李静雨
* @date： 2018年1月12日 下午4:45:58
 */
public class FJCS {
	private String fjcsId;//主键；YYYYMMDDHHMMSS
	private String fjdm;//代码（即文件夹拼音名字）
	private String fjmc;//功能名称
	private Date rksj;//添加时间
	private String ms;//功能描述
	private String zt;//状态；N：停用；Y：使用
	public String getFjcsId() {
		return fjcsId;
	}
	public void setFjcsId(String fjcsId) {
		this.fjcsId = fjcsId;
	}
	public String getFjdm() {
		return fjdm;
	}
	public void setFjdm(String fjdm) {
		this.fjdm = fjdm;
	}
	public String getFjmc() {
		return fjmc;
	}
	public void setFjmc(String fjmc) {
		this.fjmc = fjmc;
	}
	 
	public String getMs() {
		return ms;
	}
	public void setMs(String ms) {
		this.ms = ms;
	}
	public String getZt() {
		return zt;
	}
	public void setZt(String zt) {
		this.zt = zt;
	}
	public Date getRksj() {
		return rksj;
	}
	public void setRksj(Date rksj) {
		this.rksj = rksj;
	}
}
