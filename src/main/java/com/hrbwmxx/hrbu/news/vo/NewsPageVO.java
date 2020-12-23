package com.hrbwmxx.hrbu.news.vo;

import java.util.List;


import com.hrbwmxx.framework.model.ResultPage;
import com.hrbwmxx.hrbu.news.entity.News;

public class NewsPageVO extends ResultPage{
	private News news;
	private NewsColumnCustom newsColumn;
	private List<NewsColumnCustom>  newsColumnList ;
	public News getNews() {
		return news;
	}
	public void setNews(News news) {
		this.news = news;
	}
	public NewsColumnCustom getNewsColumn() {
		return newsColumn;
	}
	public void setNewsColumn(NewsColumnCustom newsColumn) {
		this.newsColumn = newsColumn;
	}
	public List<NewsColumnCustom> getNewsColumnList() {
		return newsColumnList;
	}
	public void setNewsColumnList(List<NewsColumnCustom> newsColumnList) {
		this.newsColumnList = newsColumnList;
	}
	
}
