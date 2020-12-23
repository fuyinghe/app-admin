package com.webmos.framework.model;

public class ColumnProperty {
	
	private String columnId;
	
	private String columnCn;
	
	private String columnEn;
	
	private int columnType=0;
	
	private int columnLength=0;
	
	private String columnDefault;
	
	private String tableName;

	public String getColumnId() {
		return columnId;
	}

	public void setColumnId(String columnId) {
		this.columnId = columnId;
	}

	public String getColumnCn() {
		return columnCn;
	}

	public void setColumnCn(String columnCn) {
		this.columnCn = columnCn;
	}

	public String getColumnEn() {
		return columnEn;
	}

	public void setColumnEn(String columnEn) {
		this.columnEn = columnEn;
	}

	public int getColumnType() {
		return columnType;
	}

	public void setColumnType(int columnType) {
		this.columnType = columnType;
	}

	public int getColumnLength() {
		return columnLength;
	}

	public void setColumnLength(int columnLength) {
		this.columnLength = columnLength;
	}

	public String getColumnDefault() {
		return columnDefault;
	}

	public void setColumnDefault(String columnDefault) {
		this.columnDefault = columnDefault;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	

}
