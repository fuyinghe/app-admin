package com.webmos.framework.model;

public class ImpModel extends Model {
	
	private static final long serialVersionUID = 1L;
	//是否主键。0否1是。
	private String isKey="0";
	//字段类型
	private String checkType;
	//用户提交值
	private String column_value="";
	//当前批次
	private String uuid="";
	public String getColumn_value() {
		return column_value;
	}
	public void setColumn_value(String column_value) {
		this.column_value = column_value;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public String getIsKey() {
		return isKey;
	}
	public void setIsKey(String isKey) {
		this.isKey = isKey;
	}
	public String getCheckType() {
		return checkType;
	}
	public void setCheckType(String checkType) {
		this.checkType = checkType;
	}
	
}
