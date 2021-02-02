package com.hrbwmxx.hrbu.news.vo;

import java.util.List;
import java.util.Map;

import com.hrbwmxx.framework.model.ResultEntity;
import com.hrbwmxx.hrbu.news.entity.NewsInfoNH;

public class NewsInfoNHVO  extends ResultEntity{

	private NewsInfoNHCustom newsInfoNHCustom;
	private List<NewsInfoNH>  newsInfoNHList ;
	private List<Map<String, String>> list_file_pic;
	
	public NewsInfoNHCustom getNewsInfoNHCustom() {
		return newsInfoNHCustom;
	}
	public void setNewsInfoNHCustom(NewsInfoNHCustom newsInfoNHCustom) {
		this.newsInfoNHCustom = newsInfoNHCustom;
	}
	public List<NewsInfoNH> getNewsInfoNHList() {
		return newsInfoNHList;
	}
	public void setNewsInfoNHList(List<NewsInfoNH> newsInfoNHList) {
		this.newsInfoNHList = newsInfoNHList;
	}
	public List<Map<String, String>> getList_file_pic() {
		return list_file_pic;
	}
	public void setList_file_pic(List<Map<String, String>> list_file_pic) {
		this.list_file_pic = list_file_pic;
	}
	


	
}
