package com.hrbwmxx.hrbu.news.service;

import javax.servlet.http.HttpServletRequest;

import com.hrbwmxx.framework.model.Page;
import com.hrbwmxx.framework.model.Result;
import com.hrbwmxx.hrbu.news.vo.NewsInfoCustom;

public interface INewsInfoService {
	
	Result queryNewsInfoListPage(Page page, NewsInfoCustom obj);

	Result queryNewsInfoList(NewsInfoCustom obj);

	Result queryNewsInfoById(NewsInfoCustom obj);

	Result saveNewsData(NewsInfoCustom obj, HttpServletRequest request);

	Result updateNewsInfo(NewsInfoCustom obj, HttpServletRequest request);

	Result publishNewsInfo(NewsInfoCustom obj, HttpServletRequest request);

	Result cancelPublishNewsInfo(NewsInfoCustom obj, HttpServletRequest request);

	Result deleteNewsInfo(NewsInfoCustom obj);

}
