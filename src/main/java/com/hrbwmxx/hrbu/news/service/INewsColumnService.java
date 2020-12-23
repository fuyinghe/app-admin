package com.hrbwmxx.hrbu.news.service;


import com.hrbwmxx.framework.model.Page;
import com.hrbwmxx.framework.model.Result;
import com.hrbwmxx.hrbu.news.entity.NewsColumn;
import com.hrbwmxx.hrbu.news.vo.NewsColumnCustom;

public interface INewsColumnService {
	public Result queryNewsColumnById(NewsColumnCustom obj);
	public Result saveNewsColumnData(NewsColumn obj);
	public Result updateNewsColumnData(NewsColumn obj);
	public Result deleteNewsColumnDataById(NewsColumn obj);
	public Result queryNewsColumnList(NewsColumnCustom obj);
	public Result queryNewsColumnListPage(Page page, NewsColumnCustom obj);
}
