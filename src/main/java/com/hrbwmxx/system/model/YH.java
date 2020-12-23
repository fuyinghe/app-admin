package com.hrbwmxx.system.model;

import java.io.Serializable;
/**
 * 
* @title: YH 
* @description：TODO
* @author： 刘威巍
* @date： 2018年1月12日 下午4:37:49
 */
public class YH implements Serializable {
	
	 private static final long serialVersionUID = 1L;
	 
	 private String yhId;//用户id,uuid，关联学生，为学生 jlid，关联政工人员，为职工号；如果是管理员，则为管理员id
	 private String userName;//如果是学生，登录为学号，如果是政工人员，为oa返回账号；如果是第三方例如管理员，则为管理员账号
	 private String passWord;//md5加密
	 private String xm;//姓名
	 private String sf;//1政工人员 2第三方 3学生
	 private String xh;//序号
	 private String zt;//状态；0：停用；1：使用
	 private String token;//
	public String getYhId() {
		return yhId;
	}
	public void setYhId(String yhId) {
		this.yhId = yhId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassWord() {
		return passWord;
	}
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	public String getXm() {
		return xm;
	}
	public void setXm(String xm) {
		this.xm = xm;
	}
	public String getSf() {
		return sf;
	}
	public void setSf(String sf) {
		this.sf = sf;
	}
	public String getXh() {
		return xh;
	}
	public void setXh(String xh) {
		this.xh = xh;
	}
	public String getZt() {
		return zt;
	}
	public void setZt(String zt) {
		this.zt = zt;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	 
	 
	 
}
