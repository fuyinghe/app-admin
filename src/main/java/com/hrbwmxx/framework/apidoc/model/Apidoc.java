package com.hrbwmxx.framework.apidoc.model;

import java.math.BigDecimal;

public class Apidoc {
	/**
	 * 主键
	 */
	private BigDecimal id;
	/**
	 * 父级Id
	 */
	private BigDecimal pid;
	/**
	 * 名称
	 */
	private String name;
	/**
	 * 配置信息
	 */
	private String config;
	public BigDecimal getId() {
		return id;
	}
	public void setId(BigDecimal id) {
		this.id = id;
	}
	public BigDecimal getPid() {
		return pid;
	}
	public void setPid(BigDecimal pid) {
		this.pid = pid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getConfig() {
		return config;
	}
	public void setConfig(String config) {
		this.config = config;
	}
}
