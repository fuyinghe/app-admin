package com.webmos.framework.model;

/**
 * 代码表配置与模型属性表的映射关系
 * @author grma
 */
public class CodeSql {
	
	//模型配置的英文名称
	private String model_field="";
	//模型配置的中文名称
	private String model_title="";
	//模型配置的formType类型
	private String model_formType="";
	//代码表配置的ID
	private String dmb_id="";
	//代码表配置的标题名称
	private String dmb_title="";
	//代码表配置的sql片段
	private String dmb_sqlcode="";
	
	public String getModel_field() {
		return model_field;
	}
	public void setModel_field(String model_field) {
		this.model_field = model_field;
	}
	public String getModel_title() {
		return model_title;
	}
	public void setModel_title(String model_title) {
		this.model_title = model_title;
	}
	public String getDmb_id() {
		return dmb_id;
	}
	public void setDmb_id(String dmb_id) {
		this.dmb_id = dmb_id;
	}
	public String getDmb_title() {
		return dmb_title;
	}
	public void setDmb_title(String dmb_title) {
		this.dmb_title = dmb_title;
	}
	public String getDmb_sqlcode() {
		return dmb_sqlcode;
	}
	public void setDmb_sqlcode(String dmb_sqlcode) {
		this.dmb_sqlcode = dmb_sqlcode;
	}
	
	public String getModel_formType() {
		return model_formType;
	}
	public void setModel_formType(String model_formType) {
		this.model_formType = model_formType;
	}
	@Override
	public String toString() {
		return "CodeSql [model_field=" + model_field + ", model_title="
				+ model_title + ", model_formType=" + model_formType
				+ ", dmb_id=" + dmb_id + ", dmb_title=" + dmb_title
				+ ", dmb_sqlcode=" + dmb_sqlcode + "]";
	}

	
	
}
