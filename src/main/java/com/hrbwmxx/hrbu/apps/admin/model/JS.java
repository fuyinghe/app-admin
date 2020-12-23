package com.hrbwmxx.hrbu.apps.admin.model;

/**
 * 角色实体
 * 
 * @author hechunyang
 * @date 2018年5月8日
 * @remark TODO
 */
public class JS {

	private String id;

	private String name;

	private String des;

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

	public String getDes() {
		return des;
	}

	public void setDes(String des) {
		this.des = des;
	}

	public JS(String id, String name, String des) {
		super();
		this.id = id;
		this.name = name;
		this.des = des;
	}

	public JS() {
		super();
	}

}
