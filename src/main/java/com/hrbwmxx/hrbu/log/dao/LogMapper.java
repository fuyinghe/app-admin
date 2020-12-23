package com.hrbwmxx.hrbu.log.dao;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.annotations.Param;


import com.hrbwmxx.framework.model.Page;
import com.hrbwmxx.framework.model.Result;
import com.hrbwmxx.hrbu.log.model.Log;
import com.hrbwmxx.hrbu.log.vo.LogVo;
import com.hrbwmxx.hrbu.tzgg.vo.TZGGCustom;

public interface LogMapper {
	List<Log> queryAllLogForPage(@Param("page")Page page,@Param("obj")LogVo logVo);
	int queryLogForCount(@Param("page")Page page,@Param("obj")LogVo logVo);
	void addLog(Log log);
	void delLog(@Param("rzid")String id);
}
