package com.hrbwmxx.hrbu.news.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hrbwmxx.framework.model.Page;
import com.hrbwmxx.hrbu.news.entity.NewsType;
import com.hrbwmxx.hrbu.news.vo.NewsTypeCustom;

public interface NewsTypeMapper {

	public List<NewsTypeCustom> queryNewsTypeListPage(@Param("page")Page page, @Param("obj")NewsType obj);

	public int queryNewsTypeForCount(@Param("page")Page page, @Param("obj")NewsType obj);

	public NewsTypeCustom queryNewsTypeById (@Param("obj")NewsType obj);
	
	public void saveNewsType(NewsType obj);
	
	public void updateNewsType (NewsType obj);
	
	public void deleteNewsTypeById (NewsType obj);
	
	public void cancelOrPublishNewsType (NewsType obj);

	public List<NewsTypeCustom> queryNewsTypeList(@Param("obj")NewsType newsType);
	
	
}
