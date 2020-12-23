package com.hrbwmxx.hrbu.tzgg.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hrbwmxx.framework.model.Page;
import com.hrbwmxx.framework.model.Result;
import com.hrbwmxx.hrbu.news.entity.News;
import com.hrbwmxx.hrbu.news.vo.NewsCustom;
import com.hrbwmxx.hrbu.tzgg.model.TZGG;
import com.hrbwmxx.hrbu.tzgg.service.ITZGGService;
import com.hrbwmxx.hrbu.tzgg.vo.TZGGCustom;



/**
 * @author LiuGuoHui
 * @date 2018-10-15
 *
 */
@Controller
@RequestMapping("tzgg")
public class ZTGGController {
	public static final Logger logger = LoggerFactory
			.getLogger(ZTGGController.class);
	@Autowired
	private ITZGGService tzggService;
	/* * 
	* @MethodName: queryAllTzggForPage 
	* @description : 通知公告（含分页）
	* @param page
	* @param TZGG
	* @return Result
	* @author LiuGuoHui
	 */
	@RequestMapping(value="queryAllTzggForPage", method = { RequestMethod.POST,
			RequestMethod.GET })
	@ResponseBody
	public Result queryAllTzggForPage(TZGGCustom tzgg,Page page) { 	
		return tzggService.queryAllTzggForPage(page,tzgg);
	}
	/**
	 * 
	* @MethodName: queryTZggList 
	* @description : 通知公告（不含分页）
	* @return Result
	* @author LiuGuoHui
	 */
	@ResponseBody
	@RequestMapping("queryTZggList")
	public Result queryTZggList( TZGGCustom obj){
		return tzggService.queryTZggList(obj);
	}
	/**
	 * 
	* @MethodName: queryTzggById 
	* @description : 查询-根据id获取通知公告详情
	* @param id
	* @return Result
	* @author LiuGuoHui
	 */
	@RequestMapping(value="queryTzggById", method = { RequestMethod.POST,
			RequestMethod.GET })
	@ResponseBody
	public Result queryTzggById(TZGGCustom dto) {
		return tzggService.queryTzggById(dto);
	}
	/**
	 * 
	* @MethodName: saveTZggData 
	* @description : 保存-通知公告数据
	* @param TZGG
	* @return Result
	* @author LiuGuoHui
	 */
	@ResponseBody
	@RequestMapping("saveTZggData")
		public Result saveTZggData( TZGGCustom obj,HttpServletRequest request){
		return  tzggService.saveTZggData(obj,request);
	}
	/**
	 * 
	* @MethodName: updateTZggData 
	* @description : 修改-通知公告数据
	* @param TZGG
	* @return Result
	* @author LiuGuoHui
	 */
	@ResponseBody
	@RequestMapping("updateTZggData")
	
	public Result updateTZggData(TZGGCustom obj,HttpServletRequest request){
		return tzggService.updateTZggData(obj,request);
	}
	/**
	 * 
	* @MethodName: publishTZggData 
	* @description : 发布-通知公告数据
	* @param TZgg
	* @return Result
	* @author LiuGuoHui
	 */
	@ResponseBody
	@RequestMapping("publishTZggData")
	
	public Result publishTZggData(TZGGCustom obj,HttpServletRequest request){
		return tzggService.publishTZggData(obj,request);
	}
	/**
	 * 
	* @MethodName: cancelPublishTZggData 
	* @description : 取消发布-通知公告数据
	* @param TZgg
	* @return Result
	* @author LiuGuoHui
	 */
	@ResponseBody
	@RequestMapping("cancelPublishTZggData")
	
	public Result cancelPublishTZggData(TZGGCustom obj){
		return tzggService.cancelPublishTZggData(obj);
	}
	/**
	 * 
	* @MethodName: deleteTZggDataById 
	* @description : 删除-通知公告数据
	* @param id
	* @return Result
	* @author LiuGuoHui
	 */
	@ResponseBody
	@RequestMapping("deleteTZggDataById")
	public Result deleteTZggDataById(TZGGCustom obj){
		return tzggService.deleteTZggDataById(obj);
	}
	
}
