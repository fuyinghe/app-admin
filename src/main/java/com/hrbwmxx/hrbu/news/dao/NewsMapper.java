package com.hrbwmxx.hrbu.news.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;


import com.hrbwmxx.framework.model.Page;
import com.hrbwmxx.hrbu.news.entity.News;
import com.hrbwmxx.hrbu.news.vo.NewsCustom;

public interface NewsMapper {
	
  public NewsCustom queryNewsById(@Param("obj") NewsCustom obj);
   void  saveNewsData(News news);
   void updateNewsData(News news);
   void deleteNewsDataById(News news);
   public List<NewsCustom> queryNewsListPage(@Param("page")Page page, @Param("obj") NewsCustom obj);
   public int queryNewsForCount(@Param("page")Page page, @Param("obj")  NewsCustom obj);
   public List<NewsCustom> queryNewsList(@Param("obj") NewsCustom obj);
	
	public List<News> queryNewsByNewsColumnid(@Param("newsColumnid")String newsColumnid);
	
	
}
