package com.hrbwmxx.framework.fj.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
/**
 * 
* @title: FJ 
* @description：附件表，文件上传时需要先在参数数据库中插入数据
* @author： 李静雨
* @date： 2018年1月12日 下午4:46:46
 */
public class FJ {
	 private String  	  fjId  ;//主键
	 private String   xzlj;// 下载路径
	 private String   xmc ;//时间戳名字
	 private String    fkId;//外键id（用来多附件上传的时候储存业务主键）
	 private String   ymc  ;//原始文件名字
	 private String    wjqlj;//附件全路径
	 private String    dm  ;//附件参数code便于以后统计
	 private String    tplj;//图片路径展示
	 private String    xmlj;//http根路径路径，用于拓展分布式环境
	 private Date   rksj ;//保存时间
	 private String zt;//是否启用1/0
	public String getFjId() {
		return fjId;
	}
	public void setFjId(String fjId) {
		this.fjId = fjId;
	}
	public String getXzlj() {
		return xzlj;
	}
	public void setXzlj(String xzlj) {
		this.xzlj = xzlj;
	}
	public String getXmc() {
		return xmc;
	}
	public void setXmc(String xmc) {
		this.xmc = xmc;
	}
	public String getFkId() {
		return fkId;
	}
	public void setFkId(String fkId) {
		this.fkId = fkId;
	}
	public String getYmc() {
		return ymc;
	}
	public void setYmc(String ymc) {
		this.ymc = ymc;
	}
	public String getWjqlj() {
		return wjqlj;
	}
	public void setWjqlj(String wjqlj) {
		this.wjqlj = wjqlj;
	}
	public String getDm() {
		return dm;
	}
	public void setDm(String dm) {
		this.dm = dm;
	}
	public String getTplj() {
		return tplj;
	}
	public void setTplj(String tplj) {
		this.tplj = tplj;
	}
	public String getXmlj() {
		return xmlj;
	}
	public void setXmlj(String xmlj) {
		this.xmlj = xmlj;
	}
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	public Date getRksj() {
		return rksj;
	}
	public void setRksj(Date rksj) {
		this.rksj = rksj;
	}
	public String getZt() {
		return zt;
	}
	public void setZt(String zt) {
		this.zt = zt;
	}
	 
}
