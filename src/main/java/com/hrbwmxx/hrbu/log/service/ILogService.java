package com.hrbwmxx.hrbu.log.service;


import com.hrbwmxx.framework.model.Page;
import com.hrbwmxx.framework.model.Result;
import com.hrbwmxx.hrbu.log.model.Log;
import com.hrbwmxx.hrbu.log.vo.LogVo;

public interface ILogService {
	public Result queryAllLogForPage(Page page,LogVo log);
	public Result delLog(String rzid);
}
