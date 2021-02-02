package com.hrbwmxx.hrbu.news.service;

import com.hrbwmxx.framework.model.Page;
import com.hrbwmxx.framework.model.Result;
import com.hrbwmxx.hrbu.news.entity.NewsTypeNH;

public interface INewsTypeNHService {

	Result queryNewsTypeListPage(Page page, NewsTypeNH obj);

	Result queryNewsTypeById(NewsTypeNH obj);

	Result saveNewsType(NewsTypeNH obj);

	Result updateNewsType(NewsTypeNH obj);

	Result publishNewsType(NewsTypeNH obj);

	Result cancelPublishNewsType(NewsTypeNH obj);

	Result deleteNewsTypeById(NewsTypeNH obj);

	Result queryNewsTypeList(NewsTypeNH obj);

}
