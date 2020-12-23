package com.hrbwmxx.hrbu.tzgg.dao;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.annotations.Param;


import com.hrbwmxx.framework.model.Page;
import com.hrbwmxx.hrbu.tzgg.vo.TZGGCustom;

public interface TZGGMapper {
	
 
   public int queryTZggForCount(@Param("page")Page page, @Param("obj")  TZGGCustom obj);
	
	/**
	 * 获取全部通知公告列表分页
	 * @return
	 */
	public List<TZGGCustom> queryAllTzggForPage(@Param("page")Page page,@Param("obj")TZGGCustom tzgg);
	
	public List<TZGGCustom> queryTZggList(@Param("obj")TZGGCustom obj);
	
	public TZGGCustom queryTzggById(@Param("obj") TZGGCustom obj);
	
	void saveTZggData( TZGGCustom obj);
	
	void updateTZggData(TZGGCustom obj);
	void deleteTZggDataById(TZGGCustom obj);
	
}
