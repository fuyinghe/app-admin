package com.webmos.framework.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DataModel implements Serializable{
	
	private static final long serialVersionUID = 1L;
	//模块ID
	private String moid="";
	//数据表或视图名称
	private String tableName="";
	//查询信息主键
	private String table_key="WID";
	private String table_key_value="";
	
	//模块中未被隐藏的字段
	private String notHiddenColumns="";
	//模块中全部字段
	private String allColumns="";
	//或模块form表单显示的列字段
	private String fromColumns="";
	
	//模块设置的排序片段代码
	private String customOrderBy="";
	
	//前端用户输入的排序值
	//该值放入前，前端一定进行处理加工，否则会出现sql注入漏洞
	private String orderBy_column;
	private String orderBy_ascdesc;
	
	//高级查询数据对象模型
	private List<AdvancedSearch> advancedSearchList;
	private List<FormModel> formModel;
	//高级查询前端用户输入的数据值
	private HashMap<String,String> queryFiledMap;
	
	
	/**
	 * 检查高级查询模型与用户查询条件；
	 * 检查目的:不在高级查询模型中的用户查询字段，视为非法字段，将抛弃掉，不可进行查询；
	 * 检查通过的用户查询字段，会将用户提交的查询值，写入高级查询模型中；
	 * 对高级查询模型中已有的字段，但用户未输入查询值，同样不参加查询。
	 */
	public void CheckContrast(){
		List<AdvancedSearch> temp=new ArrayList<AdvancedSearch>();
		if(advancedSearchList != null && queryFiledMap != null){
			for(int i=0;i<this.advancedSearchList.size();i++){
				AdvancedSearch advancedSearch = advancedSearchList.get(i);
				if(queryFiledMap.get(advancedSearch.getColumn_en())!=null){
					advancedSearch.setQuery_Value(queryFiledMap.get(advancedSearch.getColumn_en()));
					temp.add(advancedSearch);
				}
			}
			advancedSearchList = temp;
		}
	}

	
	public String getCustomOrderBy() {
		return customOrderBy;
	}


	public void setCustomOrderBy(String customOrderBy) {
		this.customOrderBy = customOrderBy;
	}


	public String getOrderBy_column() {
		return orderBy_column;
	}
	
	public void setOrderBy_column(String orderBy_column) {
		this.orderBy_column = orderBy_column;
	}
	
	public String getOrderBy_ascdesc() {
		return orderBy_ascdesc;
	}

	public void setOrderBy_ascdesc(String orderBy_ascdesc) {
		this.orderBy_ascdesc = orderBy_ascdesc;
	}

	public List<FormModel> getFormModel() {
		return formModel;
	}

	public void setFormModel(List<FormModel> formModel) {
		this.formModel = formModel;
	}

	public String getFromColumns() {
		return fromColumns;
	}

	public void setFromColumns(String fromColumns) {
		this.fromColumns = fromColumns;
	}



	public String getTable_key_value() {
		return table_key_value;
	}
	
	public void setTable_key_value(String table_key_value) {
		this.table_key_value = table_key_value;
	}



	public String getTable_key() {
		return table_key;
	}



	public void setTable_key(String table_key) {
		this.table_key = table_key;
	}



	public HashMap<String, String> getQueryFiledMap() {
		return queryFiledMap;
	}
	public void setQueryFiledMap(HashMap<String, String> queryFiledMap) {
		this.queryFiledMap = queryFiledMap;
	}
	public String getNotHiddenColumns() {
		return notHiddenColumns;
	}
	public void setNotHiddenColumns(String notHiddenColumns) {
		this.notHiddenColumns = notHiddenColumns;
	}
	public String getAllColumns() {
		return allColumns;
	}
	public void setAllColumns(String allColumns) {
		this.allColumns = allColumns;
	}
	public String getMoid() {
		return moid;
	}
	public void setMoid(String moid) {
		this.moid = moid;
	}
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public List<AdvancedSearch> getAdvancedSearchList() {
		return advancedSearchList;
	}
	public void setAdvancedSearchList(List<AdvancedSearch> advancedSearchList) {
		this.advancedSearchList = advancedSearchList;
	}

	
	
	
}
