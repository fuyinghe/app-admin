package com.webmos.framework.model;

import java.io.Serializable;
import java.util.List;

public class Model implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	//属性ID
	private String column_id="";
	//所属模块
	private String moid="";
	//英文名
	private String field="";
	//映射字段
	private String mappingField="";
	//中文名
	private String title="";
	//默认值
	private String defaultValue="";
	//是否必填。0否1是。
	private String isRequired="0";
	//检查类型
	private String valueType="";
	//最大值
	private String maxValue="0";
	//最小值
	private String minValue="0";
	//最大长度
	private int maxSize=0;
	//最小长度
	private int minSize=0;
	//布局结构
	//text/radio/checkbox/textarea/editor/hidden/datetime/date/time/select:静态数据select/advSelect:高级select/linSelect:联动select
	private String formType="";
	//是否隐藏，增改查是否隐藏
	private int isHidden=0;
	//是否已读,如果只读，增改表单中不显示，但查看显示。
	private int isOnlyread=0;
	//布局比例，12列占几列，最小3，最大12
	private int cols=3;
	//是否可浏览，表格中是否显示，0不允许，1允许，2允许但默认隐藏
	private int visible=0;
	//列表对齐方式 left,center,right
	private String align="center";
	//导入导出
	private int isImport=0;
	private int isExport=0;
	//排序  是否可以按此列排序，请求list接口时附加orderBy=该列field desc/asc
	private int sortable=0;
	//高级查询   高查启用，0禁止按此字段搜索，1只可在高级搜索中搜索，2简单搜索中也显示
	private int search=0;
	//查询类型   text/radio/select:静态数据select/advSelect:高级select/linSelect:联动select/datetime/date/time/dateslot:时间段，日期~日期。
	private String searchType="";
	//输入提示
	private String placeholder="";
	//分组名称
	private String fieldgroupName="";
	//当前列顺序
	private String fieldorder="";
	//其他配置
	private ValueConfig valueConfig =new ValueConfig();
	
	
	public void setDmbList(List<ModelDMB> dmblist){
		valueConfig.setList(dmblist);
	}

	public ValueConfig getValueConfig() {
		return valueConfig;
	}

	public void setValueConfig(ValueConfig valueConfig) {
		this.valueConfig = valueConfig;
	}

	public String getMappingField() {
		//return mappingField;
		if(this.formType.equals("select")||this.formType.equals("radio")||this.formType.equals("checkbox")){
			return field+"Name";
		}else{
			return field;
		}
		
		
	}

	public int getIsHidden() {
		return isHidden;
	}

	public void setIsHidden(int isHidden) {
		this.isHidden = isHidden;
	}

	public int getCols() {
		return cols;
	}

	public void setCols(int cols) {
		this.cols = cols;
	}

	public int getVisible() {
		return visible;
	}

	public void setVisible(int visible) {
		this.visible = visible;
	}

	public int getIsImport() {
		return isImport;
	}

	public void setIsImport(int isImport) {
		this.isImport = isImport;
	}

	public int getIsExport() {
		return isExport;
	}

	public void setIsExport(int isExport) {
		this.isExport = isExport;
	}

	public int getSortable() {
		return sortable;
	}

	public void setSortable(int sortable) {
		this.sortable = sortable;
	}

	public int getSearch() {
		return search;
	}

	public void setSearch(int search) {
		this.search = search;
	}

	public String getFieldorder() {
		return fieldorder;
	}

	public void setFieldorder(String fieldorder) {
		this.fieldorder = fieldorder;
	}

	public String getFieldgroupName() {
		return fieldgroupName;
	}

	public void setFieldgroupName(String fieldgroupName) {
		this.fieldgroupName = fieldgroupName;
	}

	public String getPlaceholder() {
		return placeholder;
	}

	public void setPlaceholder(String placeholder) {
		this.placeholder = placeholder;
	}

	public String getField() {
		return field;
	}

	public String getColumn_id() {
		return column_id;
	}

	public void setColumn_id(String column_id) {
		this.column_id = column_id;
	}

	public String getMoid() {
		return moid;
	}

	public void setMoid(String moid) {
		this.moid = moid;
	}

	public void setField(String field) {
		this.field = field;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDefaultValue() {
		return defaultValue;
	}

	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}

	public String getIsRequired() {
		return isRequired;
	}

	public void setIsRequired(String isRequired) {
		if(isRequired.equals("true")){
			this.isRequired = "1";
		}else{
			this.isRequired = "0";
		}
	}

	public String getValueType() {
		if(valueType==null){
			return "";
		}else{
			return valueType;
		}
		
		
	}

	public void setValueType(String valueType) {
		this.valueType = valueType;
	}

	public String getMaxValue() {
		return maxValue;
	}

	public void setMaxValue(String maxValue) {
		this.maxValue = maxValue;
	}

	public String getMinValue() {
		return minValue;
	}

	public void setMinValue(String minValue) {
		this.minValue = minValue;
	}

	

	public int getMaxSize() {
		return maxSize;
	}

	public void setMaxSize(int maxSize) {
		this.maxSize = maxSize;
	}

	public int getMinSize() {
		return minSize;
	}

	public void setMinSize(int minSize) {
		this.minSize = minSize;
	}

	public String getFormType() {
		return formType;
	}

	public void setFormType(String formType) {
		this.formType = formType;
	}




	public int getIsOnlyread() {
		return isOnlyread;
	}

	public void setIsOnlyread(String isOnlyread) {
		if(isOnlyread.equals("true")){
			this.isOnlyread =1;
		}else{
			this.isOnlyread =0;
		}
	}


	public String getSearchType() {
		return searchType;
	}

	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}

	public String getAlign() {
		return align;
	}

	public void setAlign(String align) {
		this.align = align;
	}
	

	@Override
	public String toString() {
		return "Model [column_id=" + column_id + ", moid=" + moid + ", field=" + field + ", mappingField="
				+ mappingField + ", title=" + title + ", defaultValue=" + defaultValue + ", isRequired=" + isRequired
				+ ", valueType=" + valueType + ", maxValue=" + maxValue + ", minValue=" + minValue + ", maxSize="
				+ maxSize + ", minSize=" + minSize + ", formType=" + formType + ", isHidden=" + isHidden
				+ ", isOnlyread=" + isOnlyread + ", cols=" + cols + ", visible=" + visible + ", align=" + align
				+ ", isImport=" + isImport + ", isExport=" + isExport + ", sortable=" + sortable + ", search=" + search
				+ ", searchType=" + searchType + ", placeholder=" + placeholder + ", fieldgroupName=" + fieldgroupName
				+ ", fieldorder=" + fieldorder + ", valueConfig=" + valueConfig + "]";
	}
}
