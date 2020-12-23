package com.webmos.framework.model;

public class FormModel extends Model {
	
	private static final long serialVersionUID = 1L;
	
	//数据列名称
	//private String column_en="";
	//字段中文名称
	//private String column_cn="";
	//数据字段类型
	//private String column_type="";
	//用户提交值
	private String column_value="";
	//检查类型
	//private String column_checkType="";

	public String getColumn_value() {
		return column_value;
	}

	public void setColumn_value(String column_value) {
		this.column_value = column_value;
	}

	@Override
	public String toString() {
		return super.toString();
	}
	
	
}
