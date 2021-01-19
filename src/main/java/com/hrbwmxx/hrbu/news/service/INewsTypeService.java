package com.hrbwmxx.hrbu.news.service;

import com.hrbwmxx.framework.model.Page;
import com.hrbwmxx.framework.model.Result;
import com.hrbwmxx.hrbu.news.entity.NewsType;

public interface INewsTypeService {

	Result queryNewsTypeListPage(Page page, NewsType obj);

	Result queryNewsTypeById(NewsType obj);

	Result saveNewsType(NewsType obj);

	Result updateNewsType(NewsType obj);

	Result publishNewsType(NewsType obj);

	Result cancelPublishNewsType(NewsType obj);

	Result deleteNewsTypeById(NewsType obj);

	Result queryNewsTypeList(NewsType obj);

}
