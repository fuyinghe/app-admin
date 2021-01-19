package com.hrbwmxx.hrbu.news.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hrbwmxx.framework.model.Page;
import com.hrbwmxx.framework.model.Result;
import com.hrbwmxx.hrbu.news.service.INewsInfoService;
import com.hrbwmxx.hrbu.news.vo.NewsInfoCustom;

/**
 * 
 * <p>Title: NewsInfoController</p>  
 * <p>Description: 新闻信息</p>  
 */

@Controller
@RequestMapping("newsInfo")
public class NewsInfoController {
	
	public static final Logger logger = LoggerFactory
			.getLogger(NewsInfoController.class);
	
	@Autowired
	private INewsInfoService newsInfoService;
	
	/**
	 * @Title: queryNewsInfoListPage  
	 * @Description: TODO(新闻信息列表-含分页)
	 * @param page
	 * @param newsInfo
	 * @return  
	 * @author: Mr.Zhao
	 */
	@ResponseBody
	@RequestMapping("queryNewsInfoListPage")
	public Result queryNewsInfoListPage(Page page, NewsInfoCustom obj) {
		return newsInfoService.queryNewsInfoListPage(page,obj);
	}
	
	/**
	 * @Title: queryNewsInfoList  
	 * @Description: TODO(新闻列表-未分页)
	 * @param obj
	 * @return  
	 * @author: Mr.Zhao
	 */
	@ResponseBody
	@RequestMapping("queryNewsInfoList")
	public Result queryNewsInfoList(NewsInfoCustom obj) {
		return newsInfoService.queryNewsInfoList(obj);
	}
	
	/**
	 * @Title: queryNewsInfoById  
	 * @Description: TODO(查询单条新闻)
	 * @param obj
	 * @return  
	 * @author: Mr.Zhao
	 */
	@ResponseBody
	@RequestMapping("queryNewsInfoById")
	public Result queryNewsInfoById(NewsInfoCustom obj) {
		return newsInfoService.queryNewsInfoById(obj);
	}
	
	/**
	 * 添加新闻
	 * @Title: saveNewsData 
	 * @param obj
	 * @param request
	 * @return  
	 * @author: Mr.Zhao
	 */
	@ResponseBody
	@RequestMapping("saveNewsInfo")
		public Result saveNewsData( NewsInfoCustom obj,HttpServletRequest request){
		return  newsInfoService.saveNewsData(obj,request);
	}
	/**
	 * 修改新闻
	 * @Title: updateNewsInfo 
	 * @param obj
	 * @param request
	 * @return  
	 * @author: Mr.Zhao
	 */
	@ResponseBody
	@RequestMapping("updateNewsInfo")
		public Result updateNewsInfo( NewsInfoCustom obj,HttpServletRequest request){
		return  newsInfoService.updateNewsInfo(obj,request);
	}
	/**
	 * 发布新闻
	 * @Title: publishNewsInfo 
	 * @param obj
	 * @param request
	 * @return  
	 * @author: Mr.Zhao
	 */
	@ResponseBody
	@RequestMapping("publishNewsInfo")
		public Result publishNewsInfo( NewsInfoCustom obj,HttpServletRequest request){
		return  newsInfoService.publishNewsInfo(obj,request);
	}
	/**
	 * 取消发布新闻信息
	 * @Title: saveNewsData 
	 * @param obj
	 * @param request
	 * @return  
	 * @author: Mr.Zhao
	 */
	@ResponseBody
	@RequestMapping("cancelPublishNewsInfo")
		public Result cancelPublishNewsInfo( NewsInfoCustom obj,HttpServletRequest request){
		return  newsInfoService.cancelPublishNewsInfo(obj,request);
	}
	
	/**
	 * 删除新闻
	 * @Title: deleteNewsInfo 
	 * @param obj
	 * @return  
	 * @author: Mr.Zhao
	 */
	@ResponseBody
	@RequestMapping("deleteNewsInfo")
		public Result deleteNewsInfo( NewsInfoCustom obj){
		return  newsInfoService.deleteNewsInfo(obj);
	}
	
	
}
