package com.hrbwmxx.hrbu.news.vo;

import java.util.List;
import java.util.Map;

import com.hrbwmxx.hrbu.news.entity.NewsInfoNH;

public class NewsInfoNHCustom  extends NewsInfoNH{

	private String publishUser;//出版作者
	private String publishUserName;//出版作者
	private String typeName;//出版作者
	private List<Map<String, String>> list_file_pic;//图片
	public String getPublishUser() {
		return publishUser;
	}
	public void setPublishUser(String publishUser) {
		this.publishUser = publishUser;
	}
	public String getPublishUserName() {
		return publishUserName;
	}
	public void setPublishUserName(String publishUserName) {
		this.publishUserName = publishUserName;
	}
	public List<Map<String, String>> getList_file_pic() {
		return list_file_pic;
	}
	public void setList_file_pic(List<Map<String, String>> list_file_pic) {
		this.list_file_pic = list_file_pic;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	
	
}
