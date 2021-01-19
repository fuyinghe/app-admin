package com.hrbwmxx.hrbu.news.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hrbwmxx.framework.model.Page;
import com.hrbwmxx.framework.model.Result;
import com.hrbwmxx.hrbu.news.entity.NewsType;
import com.hrbwmxx.hrbu.news.service.INewsTypeService;

@Controller
@RequestMapping("newsType")
public class NewsTypeController {
	
	public static final Logger logger = LoggerFactory
			.getLogger(NewsTypeController.class);
	
	@Autowired
	private INewsTypeService newsTypeService;
	
	/**
	 * 查询类型(分页)
	 * @Title: queryNewsTypeListPage 
	 * @param page
	 * @param obj
	 * @return  
	 * @author: Mr.Zhao
	 */
	@ResponseBody
	@RequestMapping("queryNewsTypeListPage")
	public Result queryNewsTypeListPage(Page page, NewsType obj) {
		return newsTypeService.queryNewsTypeListPage(page,obj);
	}
	
	/**
	 * 查询发布类别
	 * @Title: queryPublishForNewsType 
	 * @return  
	 * @author: Mr.Zhao
	 */
	@ResponseBody
	@RequestMapping("queryNewsTypeList")
	public Result queryNewsTypeList(NewsType obj) {
		return newsTypeService.queryNewsTypeList(obj);
	}
	/**
	 * 单条查询
	 * @Title: queryNewsTypeById 
	 * @param obj
	 * @return  
	 * @author: Mr.Zhao
	 */
	@ResponseBody
	@RequestMapping("queryNewsTypeById")
	public Result queryNewsTypeById(NewsType obj) {
		return newsTypeService.queryNewsTypeById(obj);
	}
	/**
	 * 保存
	 * @Title: saveNewsType 
	 * @param obj
	 * @return  
	 * @author: Mr.Zhao
	 */
	@ResponseBody
	@RequestMapping("saveNewsType")
	public Result saveNewsType(NewsType obj) {
		return newsTypeService.saveNewsType(obj);
	}
	/**
	 * 修改
	 * @Title: updateNewsType 
	 * @param obj
	 * @return  
	 * @author: Mr.Zhao
	 */
	@ResponseBody
	@RequestMapping("updateNewsType")
	public Result updateNewsType(NewsType obj) {
		return newsTypeService.updateNewsType(obj);
	}
	/**
	 * 删除
	 * @Title: deleteNewsType 
	 * @param obj
	 * @return  
	 * @author: Mr.Zhao
	 */
	@ResponseBody
	@RequestMapping("deleteNewsTypeById")
	public Result deleteNewsTypeById(NewsType obj) {
		return newsTypeService.deleteNewsTypeById(obj);
	}
	/**
	 * 发布
	 * @Title: publishNewsType 
	 * @param obj
	 * @return  
	 * @author: Mr.Zhao
	 */
	@ResponseBody
	@RequestMapping("publishNewsType")
	public Result publishNewsType(NewsType obj) {
		return newsTypeService.publishNewsType(obj);
	}
	/**
	 * 取消发布
	 * @Title: cancelPublishNewsType 
	 * @param obj
	 * @return  
	 * @author: Mr.Zhao
	 */
	@ResponseBody
	@RequestMapping("cancelPublishNewsType")
	public Result cancelPublishNewsType(NewsType obj) {
		return newsTypeService.cancelPublishNewsType(obj);
	}
	
	
}
