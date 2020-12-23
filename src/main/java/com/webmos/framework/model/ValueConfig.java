package com.webmos.framework.model;

import java.util.ArrayList;
import java.util.List;

public class ValueConfig {
	
	//验证唯一性的时候需要的url / 高级select请求的接口 / 联动select第一个select请求数据的接口
	private String url="";
	//高级select或联动select请求接口数据中list中每条作为option的value的key
	private String valueKey="";
	//高级select或联动select请求接口数据中list中每条作为option的text的key
	private String textKey="";
	////高级select中，单选或者多选
	private int enable=0;
	//所有select返回的option优先检查list，没有则请求url， /radio/checkbox的list
	private List<ModelDMB> list=new ArrayList<ModelDMB>();
	//联动select下一个联动select的field
	private String nextSelectField="";
	//联动select下一个联动select请求数据的接口
	private String nextSelectUrl="";
	//当前联动select选中的option的value值作为此字段传递给nextSelectUrl
	private String nextSelectQueryKey="";

	public String getUrl() {
		//modelDMB/XG_XBDM/query.do
		//return "modelDMB/"+url+"/query.do";
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getValueKey() {
		return valueKey;
	}
	public void setValueKey(String valueKey) {
		this.valueKey = valueKey;
	}
	public String getTextKey() {
		return textKey;
	}
	public void setTextKey(String textKey) {
		this.textKey = textKey;
	}

	public int getEnable() {
		return enable;
	}
	public void setEnable(int enable) {
		this.enable = enable;
	}
	public List<ModelDMB> getList() {
		return list;
	}
	public void setList(List<ModelDMB> list) {
		this.list = list;
	}
	public String getNextSelectField() {
		return nextSelectField;
	}
	public void setNextSelectField(String nextSelectField) {
		this.nextSelectField = nextSelectField;
	}
	public String getNextSelectUrl() {
		return nextSelectUrl;
	}
	public void setNextSelectUrl(String nextSelectUrl) {
		this.nextSelectUrl = nextSelectUrl;
	}
	public String getNextSelectQueryKey() {
		return nextSelectQueryKey;
	}
	public void setNextSelectQueryKey(String nextSelectQueryKey) {
		this.nextSelectQueryKey = nextSelectQueryKey;
	}
	
	
	
}
