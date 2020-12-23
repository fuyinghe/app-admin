package com.hrbwmxx.hrbu.news.dao;


import java.util.List;

import org.apache.ibatis.annotations.Param;


import com.hrbwmxx.framework.model.Page;
import com.hrbwmxx.hrbu.news.entity.NewsColumn;
import com.hrbwmxx.hrbu.news.vo.NewsColumnCustom;

public interface NewsColumnMapper {
	public NewsColumnCustom queryNewsColumnById(@Param("obj") NewsColumnCustom obj);
	void saveNewsColumnData(NewsColumn obj);
	void updateNewsColumnData(NewsColumn obj);
	void deleteNewsColumnDataById(NewsColumn obj);
	public List<NewsColumnCustom> queryNewsColumnList(@Param("obj") NewsColumn obj);
	public  NewsColumn queryNewsColumnBySid(@Param("obj") NewsColumn obj);
	public List<NewsColumnCustom> queryNewsColumnListPage(@Param("page") Page page,@Param("obj") NewsColumnCustom obj);
	public int queryNewsColumnListCount(@Param("page") Page page,@Param("obj") NewsColumnCustom obj);
}
