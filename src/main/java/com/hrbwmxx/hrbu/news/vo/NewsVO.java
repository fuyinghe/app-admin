package com.hrbwmxx.hrbu.news.vo;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.hrbwmxx.framework.model.ResultEntity;

public class NewsVO extends ResultEntity{
	private NewsCustom newsCustom;
	private NewsColumnCustom newColumn;
	private List<NewsColumnCustom>  newColumnList ;
	//private List<Map<String, String>> list_file_pic;
	//private List<Map<String, String>> list_file_attachment;
	private String id;
	
	
	public NewsCustom getNewsCustom() {
		return newsCustom;
	}
	public void setNewsCustom(NewsCustom newsCustom) {
		this.newsCustom = newsCustom;
	}
	public NewsColumnCustom getNewColumn() {
		return newColumn;
	}
	public void setNewColumn(NewsColumnCustom newColumn) {
		this.newColumn = newColumn;
	}
	public List<NewsColumnCustom> getNewColumnList() {
		return newColumnList;
	}
	public void setNewColumnList(List<NewsColumnCustom> newColumnList) {
		this.newColumnList = newColumnList;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	 
 
}
