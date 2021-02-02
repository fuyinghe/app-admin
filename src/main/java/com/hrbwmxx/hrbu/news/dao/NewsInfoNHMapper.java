package com.hrbwmxx.hrbu.news.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hrbwmxx.framework.model.Page;
import com.hrbwmxx.hrbu.news.entity.NewsInfoNH;
import com.hrbwmxx.hrbu.news.vo.NewsInfoNHCustom;


public interface NewsInfoNHMapper {

	public List<NewsInfoNHCustom> queryNewsInfoListPage(@Param("page")Page page, @Param("obj")NewsInfoNHCustom obj);
	
	public int queryNewsInfoForCount(@Param("page")Page page, @Param("obj")  NewsInfoNHCustom obj);
	
	public NewsInfoNHCustom queryNewsInfoById(@Param("obj") NewsInfoNHCustom obj);
	
	public List<NewsInfoNHCustom> queryNewsInfoList(@Param("obj") NewsInfoNHCustom obj);
	
	void updateNewsInfoForTimes(NewsInfoNHCustom news);

	public void saveNewsInfo(NewsInfoNH obj);

	public void updateNewsInfo(NewsInfoNH obj);

	public void cancelOrPublishNewsInfo(NewsInfoNH obj);

	public void deleteNewsInfoById(NewsInfoNH obj);

	
}
