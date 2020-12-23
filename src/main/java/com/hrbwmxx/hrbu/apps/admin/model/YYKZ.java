package com.hrbwmxx.hrbu.apps.admin.model;

import java.util.ArrayList;
import java.util.List;

/**
 * app
 */
public class YYKZ {

	/**
	 * id
	 */
	private String id;

	/**
	 * 名称
	 */
	private String name;

	/**
	 * 图标
	 */
	private String iconCn;
	private String iconEn;

	private String icon2Cn;
	private String icon2En;
	/**
	 * 链接
	 */
	private String link;

	/**
	 * 状态
	 */
	private int state;

	/**
	 * 是否需要登陆 1--需要登陆 0--不需要登陆
	 */
	private int needLogin;

	/**
	 * 打开类型
	 */
	private int openType;

	/**
	 * appType 分类
	 */
	private String appType;

	/**
	 * 1--系统应用 2--非系统应用
	 */
	private int sysType;

	private int weight;

	private List<JS> roles = new ArrayList();

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIconCn() {
		return iconCn;
	}

	public void setIconCn(String iconCn) {
		this.iconCn = iconCn;
	}

	public String getIconEn() {
		return iconEn;
	}

	public void setIconEn(String iconEn) {
		this.iconEn = iconEn;
	}

	public String getIcon2Cn() {
		return icon2Cn;
	}

	public void setIcon2Cn(String icon2Cn) {
		this.icon2Cn = icon2Cn;
	}

	public String getIcon2En() {
		return icon2En;
	}

	public void setIcon2En(String icon2En) {
		this.icon2En = icon2En;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public int getNeedLogin() {
		return needLogin;
	}

	public void setNeedLogin(int needLogin) {
		this.needLogin = needLogin;
	}

	public int getOpenType() {
		return openType;
	}

	public void setOpenType(int openType) {
		this.openType = openType;
	}

	public String getAppType() {
		return appType;
	}

	public void setAppType(String appType) {
		this.appType = appType;
	}

	public int getSysType() {
		return sysType;
	}

	public void setSysType(int sysType) {
		this.sysType = sysType;
	}

	public List<JS> getRoles() {
		return roles;
	}

	public void setRoles(List<JS> roles) {
		this.roles = roles;
	}

}