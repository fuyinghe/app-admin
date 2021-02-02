package com.hrbwmxx.hrbu.news.vo;

import java.util.List;

import com.hrbwmxx.framework.model.ResultEntity;
import com.hrbwmxx.hrbu.news.entity.NewsTypeNH;

public class NewsTypeNHVO extends ResultEntity{
	
	private NewsTypeNH newsTypeNH;
	private NewsTypeNHCustom newsTypeNHCustom;
	private List<NewsTypeNHCustom> newsTypeNHList;
	public NewsTypeNH getNewsTypeNH() {
		return newsTypeNH;
	}
	public void setNewsTypeNH(NewsTypeNH newsTypeNH) {
		this.newsTypeNH = newsTypeNH;
	}
	public NewsTypeNHCustom getNewsTypeNHCustom() {
		return newsTypeNHCustom;
	}
	public void setNewsTypeNHCustom(NewsTypeNHCustom newsTypeNHCustom) {
		this.newsTypeNHCustom = newsTypeNHCustom;
	}
	public List<NewsTypeNHCustom> getNewsTypeNHList() {
		return newsTypeNHList;
	}
	public void setNewsTypeNHList(List<NewsTypeNHCustom> newsTypeNHList) {
		this.newsTypeNHList = newsTypeNHList;
	}
	

	
	
}
