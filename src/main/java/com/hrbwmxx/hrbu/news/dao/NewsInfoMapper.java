package com.hrbwmxx.hrbu.news.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hrbwmxx.framework.model.Page;
import com.hrbwmxx.hrbu.news.entity.NewsInfo;
import com.hrbwmxx.hrbu.news.vo.NewsInfoCustom;


public interface NewsInfoMapper {

	public List<NewsInfoCustom> queryNewsInfoListPage(@Param("page")Page page, @Param("obj")NewsInfoCustom obj);
	
	public int queryNewsInfoForCount(@Param("page")Page page, @Param("obj")  NewsInfoCustom obj);
	
	public NewsInfoCustom queryNewsInfoById(@Param("obj") NewsInfoCustom obj);
	
	public List<NewsInfoCustom> queryNewsInfoList(@Param("obj") NewsInfoCustom obj);
	
	void updateNewsInfoForTimes(NewsInfoCustom news);

	public void saveNewsInfo(NewsInfo obj);

	public void updateNewsInfo(NewsInfo obj);

	public void cancelOrPublishNewsInfo(NewsInfo obj);

	public void deleteNewsInfoById(NewsInfo obj);

	
}
