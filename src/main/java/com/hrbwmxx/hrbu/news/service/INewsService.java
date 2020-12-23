package com.hrbwmxx.hrbu.news.service;

import javax.servlet.http.HttpServletRequest;

import com.hrbwmxx.framework.model.Page;
import com.hrbwmxx.framework.model.Result;
import com.hrbwmxx.hrbu.news.entity.News;
import com.hrbwmxx.hrbu.news.vo.NewsCustom;

public interface INewsService {
	
	Result queryNewsListPage(Page page, NewsCustom obj);
	
	Result queryNewsById(NewsCustom obj);
	
	Result insertNewsData(News obj,HttpServletRequest request);
	
	Result updateNewsData(News obj,HttpServletRequest request);
	Result publishNewsData(News obj,HttpServletRequest request);
	Result cancelPublishNewsData(News obj);
	
	Result deleteNewsDataById(News obj);
	
	Result queryNewsList(NewsCustom obj);
	
	Result queryNewsByNewsColumnid(String newsColumnid);//查找的为已经发布的
}
