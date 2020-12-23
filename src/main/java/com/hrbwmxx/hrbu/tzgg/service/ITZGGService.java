package com.hrbwmxx.hrbu.tzgg.service;

import javax.servlet.http.HttpServletRequest;

import com.hrbwmxx.framework.model.Page;
import com.hrbwmxx.framework.model.Result;
import com.hrbwmxx.hrbu.tzgg.vo.TZGGCustom;

public interface ITZGGService {
	/**
	 * 获取全部通知公告列表分页
	 * @return
	 */
	public Result queryAllTzggForPage(Page page,TZGGCustom tzgg);
	/**
	 * 获取全部通知公告列表不分页
	 * @return
	 */
	public Result queryTZggList( TZGGCustom obj);
	/**
	 * 获取通知公告详情
	 * @return
	 */
	public Result queryTzggById(TZGGCustom obj);
	
	public Result saveTZggData( TZGGCustom obj,HttpServletRequest request);
	
	public Result updateTZggData(TZGGCustom obj,HttpServletRequest request);
	public Result publishTZggData(TZGGCustom obj,HttpServletRequest request);
	public Result cancelPublishTZggData(TZGGCustom obj);
	public Result deleteTZggDataById(TZGGCustom obj);
}
