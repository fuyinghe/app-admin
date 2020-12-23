package com.hrbwmxx.framework.security.user;

import java.math.BigDecimal;

import com.hrbwmxx.framework.model.TimestampSupportBean;

/**
 * User entity.
 * 
 */

public class UserEntity extends TimestampSupportBean {

	private static final long serialVersionUID = -3078433341804725057L;
	// Fields
	//sys_user
	private BigDecimal id; //201701707
	private String username; //admin
	private String password; //ec2d3e3104d4eedc08e13a318f578fe8
	
	private String activeFlag = "Y";
	private String isLockOut = "N";
	private String quickLoginFlag = "N";
	private String picture;//用户照片
	
	
	private BigDecimal personId;//
	//t_person
	private BigDecimal jobNumber;//工号
	private String name;//真实的姓名
	private BigDecimal deptId;//部门ID
	//sys_dept
	private String deptName;//部门名称
	//
	private String userIP;

	
	
	public BigDecimal getId() {
		return id;
	}
	public void setId(BigDecimal id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getActiveFlag() {
		return activeFlag;
	}
	public void setActiveFlag(String activeFlag) {
		this.activeFlag = activeFlag;
	}
	
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public String getIsLockOut() {
		return isLockOut;
	}
	
	public void setIsLockOut(String isLockOut) {
		this.isLockOut = isLockOut;
	}
	
	public String getQuickLoginFlag() {
		return quickLoginFlag;
	}
	
	public void setQuickLoginFlag(String quickLoginFlag) {
		this.quickLoginFlag = quickLoginFlag;
	}
	
	public BigDecimal getJobNumber() {
		return jobNumber;
	}
	
	public void setJobNumber(BigDecimal jobNumber) {
		this.jobNumber = jobNumber;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public BigDecimal getDeptId() {
		return deptId;
	}
	
	public void setDeptId(BigDecimal deptId) {
		this.deptId = deptId;
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getPicture() {
		return picture;
	}
	
	public void setPicture(String picture) {
		this.picture = picture;
	}
	public BigDecimal getPersonId() {
		return personId;
	}
	
	public void setPersonId(BigDecimal personId) {
		this.personId = personId;
	}
	public String getUserIP() {
		return userIP;
	}
	
	public void setUserIP(String userIP) {
		this.userIP = userIP;
	}
	
	
	
	
	
}