package com.hrbwmxx.hrbu.news.controller;

import java.math.BigDecimal;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.hrbwmxx.framework.model.Page;
import com.hrbwmxx.framework.model.Result;
import com.hrbwmxx.hrbu.news.entity.News;
import com.hrbwmxx.hrbu.news.service.INewsService;
import com.hrbwmxx.hrbu.news.vo.NewsCustom;
/**
 * 
* @title: News 
* @description：新闻

 */
@Controller
@RequestMapping("news")
public class NewsController {
	@Autowired
	private INewsService newsService;
	/**
	 * 
	* @MethodName: queryNewsListPage 
	* @description : 新闻查询（含分页）
	* @param page
	* @param news
	* @return Result
	* @author LiuGuoHui
	 */
	@ResponseBody
	@RequestMapping("queryNewsListPage")
	
	public Result queryNewsListPage( Page page, NewsCustom obj){
		return newsService.queryNewsListPage(page,obj);
	}
	/**
	 * 
	* @MethodName: queryNewsList 
	* @description : 新闻查询（不含分页）
	* @return Result
	* @author LiuGuoHui
	 */
	@ResponseBody
	@RequestMapping("queryNewsList")
	public Result queryNewsList( NewsCustom obj){
		return newsService.queryNewsList(obj);
	}
	/**
	 * 
	 *
	* @MethodName: queryNewsByNewsColumnid
	* @description : 查询-根据栏目查看新闻list--查看的是已经发布的
	* @param newsColumnid
	* @return Result
	* @author LiuGuoHui
	 */
	@ResponseBody
	@RequestMapping("queryNewsByNewsColumnid")
	public Result queryNewsByNewsColumnid( String newsColumnid){
		return newsService.queryNewsByNewsColumnid(newsColumnid);
	}
	/**
	 * 
	* @MethodName: queryNewsById 
	* @description : 查询-根据id查看新闻详情
	* @param id
	* @return Result
	* @author LiuGuoHui
	 */
	@ResponseBody
	@RequestMapping("queryNewsById")
	public Result queryNewsById(NewsCustom obj){
		return newsService.queryNewsById(obj);
	}
	/**
	 * 
	* @MethodName: saveNewsData 
	* @description : 保存-新闻数据
	* @param news
	* @return Result
	* @author LiuGuoHui
	 */
	@ResponseBody
	@RequestMapping("saveNewsData")
		public Result saveNewsData( News obj,HttpServletRequest request){
		return  newsService.insertNewsData(obj,request);
	}
	/**
	 * 
	* @MethodName: updateNewsData 
	* @description : 修改-新闻数据
	* @param news
	* @return Result
	* @author LiuGuoHui
	 */
	@ResponseBody
	@RequestMapping("updateNewsData")
	
	public Result updateNewsData(News obj,HttpServletRequest request){
		return newsService.updateNewsData(obj,request);
	}
	/**
	 * 
	* @MethodName: publishNewsData 
	* @description : 发布-新闻数据
	* @param news
	* @return Result
	* @author LiuGuoHui
	 */
	@ResponseBody
	@RequestMapping("publishNewsData")
	
	public Result publishNewsData(News obj,HttpServletRequest request){
		return newsService.publishNewsData(obj,request);
	}
	/**
	 * 
	* @MethodName: cancelPublishNewsData 
	* @description : 取消发布-新闻数据
	* @param news
	* @return Result
	* @author LiuGuoHui
	 */
	@ResponseBody
	@RequestMapping("cancelPublishNewsData")
	
	public Result cancelPublishNewsData(News obj){
		return newsService.cancelPublishNewsData(obj);
	}
	/**
	 * 
	* @MethodName: deleteNewsDataById 
	* @description : 删除-新闻数据
	* @param id
	* @return Result
	* @author LiuGuoHui
	 */
	@ResponseBody
	@RequestMapping("deleteNewsDataById")
	
	public Result deleteNewsDataById(News obj){
		return newsService.deleteNewsDataById(obj);
	}
}
