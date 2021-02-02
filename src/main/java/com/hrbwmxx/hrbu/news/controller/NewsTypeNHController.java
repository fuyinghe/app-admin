package com.hrbwmxx.hrbu.news.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hrbwmxx.framework.model.Page;
import com.hrbwmxx.framework.model.Result;
import com.hrbwmxx.hrbu.news.entity.NewsTypeNH;
import com.hrbwmxx.hrbu.news.service.INewsTypeNHService;

@Controller
@RequestMapping("newsTypeNH")
public class NewsTypeNHController {
	
	public static final Logger logger = LoggerFactory
			.getLogger(NewsTypeNHController.class);
	
	@Autowired
	private INewsTypeNHService newsTypeNHService;
	
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
	public Result queryNewsTypeListPage(Page page, NewsTypeNH obj) {
		return newsTypeNHService.queryNewsTypeListPage(page,obj);
	}
	
	/**
	 * 查询发布类别
	 * @Title: queryPublishForNewsType 
	 * @return  
	 * @author: Mr.Zhao
	 */
	@ResponseBody
	@RequestMapping("queryNewsTypeList")
	public Result queryNewsTypeList(NewsTypeNH obj) {
		return newsTypeNHService.queryNewsTypeList(obj);
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
	public Result queryNewsTypeById(NewsTypeNH obj) {
		return newsTypeNHService.queryNewsTypeById(obj);
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
	public Result saveNewsType(NewsTypeNH obj) {
		return newsTypeNHService.saveNewsType(obj);
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
	public Result updateNewsType(NewsTypeNH obj) {
		return newsTypeNHService.updateNewsType(obj);
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
	public Result deleteNewsTypeById(NewsTypeNH obj) {
		return newsTypeNHService.deleteNewsTypeById(obj);
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
	public Result publishNewsType(NewsTypeNH obj) {
		return newsTypeNHService.publishNewsType(obj);
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
	public Result cancelPublishNewsType(NewsTypeNH obj) {
		return newsTypeNHService.cancelPublishNewsType(obj);
	}
	
	
}
