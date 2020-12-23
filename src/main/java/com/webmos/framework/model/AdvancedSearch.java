package com.webmos.framework.model;

/**
 * 高级查询实体类
 * @author grma
 * 2018-01-23
 */
public class AdvancedSearch {
	
	
	//数据列名称
	private String column_en="";
	//字段中文名称
	private String column_cn="";
	//数据字段类型
	private String column_type="";
	
	//查询类型
	private String query_type="";
	//查询值
	private String query_Value="";
	
	public String getQuery_Value() {
		return query_Value;
	}
	public void setQuery_Value(String query_Value) {
		this.query_Value = query_Value;
	}
	public String getQuery_type() {
		return query_type;
	}
	public void setQuery_type(String query_type) {
		this.query_type = query_type;
	}
	public String getColumn_en() {
		return column_en;
	}
	public void setColumn_en(String column_en) {
		this.column_en = column_en;
	}
	public String getColumn_cn() {
		return column_cn;
	}
	public void setColumn_cn(String column_cn) {
		this.column_cn = column_cn;
	}
	public String getColumn_type() {
		return column_type;
	}
	public void setColumn_type(String column_type) {
		this.column_type = column_type;
	}
	
	
	

}
