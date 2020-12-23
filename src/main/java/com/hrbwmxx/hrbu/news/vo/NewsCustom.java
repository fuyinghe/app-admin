package com.hrbwmxx.hrbu.news.vo;
import java.util.List;
import java.util.Map;

import com.hrbwmxx.hrbu.news.entity.News;


public class NewsCustom extends News{
	private String columnName;
	private String editUser;
	private String publishUser;
	private List<Map<String, String>> list_file_pic;
	private List<Map<String, String>> list_file_attachment;
	public List<Map<String, String>> getList_file_pic() {
		return list_file_pic;
	}
	public void setList_file_pic(List<Map<String, String>> list_file_pic) {
		this.list_file_pic = list_file_pic;
	}
	public List<Map<String, String>> getList_file_attachment() {
		return list_file_attachment;
	}
	public void setList_file_attachment(List<Map<String, String>> list_file_attachment) {
		this.list_file_attachment = list_file_attachment;
	}
	
	public String getColumnName() {
		return columnName;
	}
	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}
	public String getEditUser() {
		return editUser;
	}
	public void setEditUser(String editUser) {
		this.editUser = editUser;
	}
	public String getPublishUser() {
		return publishUser;
	}
	public void setPublishUser(String publishUser) {
		this.publishUser = publishUser;
	}
	
	
	
}
