package com.hrbwmxx.hrbu.news.vo;

import java.util.List;

import com.hrbwmxx.framework.model.ResultEntity;
import com.hrbwmxx.hrbu.news.entity.NewsType;

public class NewsTypeVO extends ResultEntity{
	
	private NewsType newsType;
	private NewsTypeCustom newsTypeCustom;
	private List<NewsTypeCustom> newsTypeList;
	
	public NewsType getNewsType() {
		return newsType;
	}
	public void setNewsType(NewsType newsType) {
		this.newsType = newsType;
	}
	public NewsTypeCustom getNewsTypeCustom() {
		return newsTypeCustom;
	}
	public void setNewsTypeCustom(NewsTypeCustom newsTypeCustom) {
		this.newsTypeCustom = newsTypeCustom;
	}
	public List<NewsTypeCustom> getNewsTypeList() {
		return newsTypeList;
	}
	public void setNewsTypeList(List<NewsTypeCustom> newsTypeList) {
		this.newsTypeList = newsTypeList;
	}
	
	
}
