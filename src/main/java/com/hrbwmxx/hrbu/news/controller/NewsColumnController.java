package com.hrbwmxx.hrbu.news.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.ResponseBody;


import com.hrbwmxx.framework.model.Page;
import com.hrbwmxx.framework.model.Result;
import com.hrbwmxx.hrbu.news.entity.NewsColumn;
import com.hrbwmxx.hrbu.news.service.INewsColumnService;
import com.hrbwmxx.hrbu.news.vo.NewsColumnCustom;
/**
 * 
* @title: NewsColumn 
* @description：新闻栏目

 */
@Controller
@RequestMapping("newsColumn")
public class NewsColumnController {
	@Autowired
	private INewsColumnService newsColumnService;
	
	/**
	 * 
	* @MethodName: queryNewsColumnListPage 
	* @description : 新闻栏目查询（含分页）
	* @param page
	* @param obj
	* @return Result
	* @author LiuGuoHui
	 */
	
	@ResponseBody
	@RequestMapping("queryNewsColumnListPage")
	public Result queryNewsColumnListPage(Page page,NewsColumnCustom obj) {
		return newsColumnService.queryNewsColumnListPage(page,obj);
	}
	/**
	 * 
	* @MethodName: queryNewsColumnList 
	* @description : 新闻栏目查询（不含分页）
	* @return Result
	 */
	@ResponseBody
	@RequestMapping("queryNewsColumnList")
	public Result queryNewsColumnList(NewsColumnCustom obj) {
		return newsColumnService.queryNewsColumnList(obj);
	}
	/**
	 * 
	* @MethodName: queryNewsColumnById 
	* @description : 查询-根据id查看新闻栏目详情
	* @param obj
	* @return Result
	 */
	@ResponseBody
	@RequestMapping("queryNewsColumnById")
	public Result queryNewsColumnById(NewsColumnCustom obj) {
		return newsColumnService.queryNewsColumnById(obj);
		
	}
	/**
	 * 
	* @MethodName: saveNewsColumnData 
	* @description : 保存-新闻栏目数据
	* @param news
	* @return Result
	 */
	@ResponseBody
	@RequestMapping("saveNewsColumnData")
	public Result saveNewsColumnData(NewsColumn obj) {
		return newsColumnService.saveNewsColumnData(obj);
	}
	/**
	 * 
	* @MethodName: updateNewsColumnData 
	* @description : 修改-新闻栏目数据
	* @param news
	* @return Result
	 */
	@ResponseBody
	@RequestMapping("updateNewsColumnData")
	public Result updateNewsColumnData(NewsColumn obj) {
		return newsColumnService.updateNewsColumnData(obj);
	}
	/**
	 * 
	* @MethodName: deleteNewsColumnDataById 
	* @description : 删除-新闻栏目数据
	* @param id
	* @return Result
	 */
	@ResponseBody
	@RequestMapping("deleteNewsColumnDataById")
	public Result deleteNewsColumnDataById(NewsColumn obj) {
		return newsColumnService.deleteNewsColumnDataById(obj);
	}
	
	
}
