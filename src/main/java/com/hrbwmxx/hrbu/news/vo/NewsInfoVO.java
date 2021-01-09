package com.hrbwmxx.hrbu.news.vo;

import java.util.List;
import java.util.Map;

import com.hrbwmxx.framework.model.ResultEntity;
import com.hrbwmxx.hrbu.news.entity.NewsInfo;

public class NewsInfoVO  extends ResultEntity{

	private NewsInfoCustom newsInfoCustom;
	private List<NewsInfo>  newsInfoList ;
	private List<Map<String, String>> list_file_pic;
	

	public NewsInfoCustom getNewsInfoCustom() {
		return newsInfoCustom;
	}
	public void setNewsInfoCustom(NewsInfoCustom newsInfoCustom) {
		this.newsInfoCustom = newsInfoCustom;
	}
	public List<NewsInfo> getNewsInfoList() {
		return newsInfoList;
	}
	public void setNewsInfoList(List<NewsInfo> newsInfoList) {
		this.newsInfoList = newsInfoList;
	}
	public List<Map<String, String>> getList_file_pic() {
		return list_file_pic;
	}
	public void setList_file_pic(List<Map<String, String>> list_file_pic) {
		this.list_file_pic = list_file_pic;
	}
	
}
