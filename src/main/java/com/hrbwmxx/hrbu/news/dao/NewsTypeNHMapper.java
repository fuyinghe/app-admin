package com.hrbwmxx.hrbu.news.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hrbwmxx.framework.model.Page;
import com.hrbwmxx.hrbu.news.entity.NewsTypeNH;
import com.hrbwmxx.hrbu.news.vo.NewsTypeNHCustom;

public interface NewsTypeNHMapper {

	public List<NewsTypeNHCustom> queryNewsTypeListPage(@Param("page")Page page, @Param("obj")NewsTypeNH obj);

	public int queryNewsTypeForCount(@Param("page")Page page, @Param("obj")NewsTypeNH obj);

	public NewsTypeNHCustom queryNewsTypeById (@Param("obj")NewsTypeNH obj);
	
	public void saveNewsType(NewsTypeNH obj);
	
	public void updateNewsType (NewsTypeNH obj);
	
	public void deleteNewsTypeById (NewsTypeNH obj);
	
	public void cancelOrPublishNewsType (NewsTypeNH obj);

	public List<NewsTypeNHCustom> queryNewsTypeList(@Param("obj")NewsTypeNH newsType);
	
	
}
