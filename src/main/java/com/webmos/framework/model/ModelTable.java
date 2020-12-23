package com.webmos.framework.model;

public class ModelTable {
	
	//模块名称中文名称
	private String model_Name="";
	//模块英文名称（表名）
	private String model_Table="";
	
	//模块配置的分页数，默认为10
	private int model_pageSize=10;
	
	//模块字段主键 （查询一条信息，或修改一条数据时使用）
	//当前为固定值，后续后改为模块中设置
	private String model_Key="WID";
	
	//模块中配置的自定义排序
	private String model_order ="";
	
	//模块外键
	private String model_foreign = "";
	
	
	public String getModel_foreign() {
		return model_foreign;
	}
	public void setModel_foreign(String model_foreign) {
		this.model_foreign = model_foreign;
	}
	public String getModel_order() {
		return model_order;
	}
	public void setModel_order(String model_order) {
		this.model_order = model_order;
	}
	public String getModel_Key() {
		return model_Key;
	}
	public void setModel_Key(String model_Key) {
		this.model_Key = model_Key;
	}
	public String getModel_Name() {
		return model_Name;
	}
	public void setModel_Name(String model_Name) {
		this.model_Name = model_Name;
	}
	public String getModel_Table() {
		return model_Table;
	}
	public void setModel_Table(String model_Table) {
		this.model_Table = model_Table;
	}
	public int getModel_pageSize() {
		return model_pageSize;
	}
	public void setModel_pageSize(int model_pageSize) {
		this.model_pageSize = model_pageSize;
	}
	@Override
	public String toString() {
		return "ModelTable [model_Name=" + model_Name + ", model_Table="
				+ model_Table + ", model_pageSize=" + model_pageSize + "]";
	}
	
	

}
