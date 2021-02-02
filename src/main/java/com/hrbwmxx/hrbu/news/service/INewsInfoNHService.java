package com.hrbwmxx.hrbu.news.service;

import javax.servlet.http.HttpServletRequest;

import com.hrbwmxx.framework.model.Page;
import com.hrbwmxx.framework.model.Result;
import com.hrbwmxx.hrbu.news.vo.NewsInfoNHCustom;

public interface INewsInfoNHService {
	
	Result queryNewsInfoListPage(Page page, NewsInfoNHCustom obj);

	Result queryNewsInfoList(NewsInfoNHCustom obj);

	Result queryNewsInfoById(NewsInfoNHCustom obj);

	Result saveNewsData(NewsInfoNHCustom obj, HttpServletRequest request);

	Result updateNewsInfo(NewsInfoNHCustom obj, HttpServletRequest request);

	Result publishNewsInfo(NewsInfoNHCustom obj, HttpServletRequest request);

	Result cancelPublishNewsInfo(NewsInfoNHCustom obj, HttpServletRequest request);

	Result deleteNewsInfo(NewsInfoNHCustom obj);

}
