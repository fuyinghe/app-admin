package com.hrbwmxx.hrbu.log.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hrbwmxx.framework.model.Page;
import com.hrbwmxx.framework.model.Result;
import com.hrbwmxx.framework.util.ExceptionUtil;
import com.hrbwmxx.hrbu.log.model.Log;
import com.hrbwmxx.hrbu.log.service.ILogService;
import com.hrbwmxx.hrbu.log.vo.LogVo;



/**
 * @author LiuGuoHui
 * @date 2018-10-15
 *
 */
@Controller
@RequestMapping("log")
public class LogController {
	public static final Logger logger = LoggerFactory
			.getLogger(LogController.class);
	@Autowired
	private ILogService logService;
	@Autowired
	private ExceptionUtil exceptionUtil;
	/* * 
	* @MethodName: queryAllTzggForPage 
	* @description : 日志信息（含分页）
	* @param page
	* @param Log
	* @return Result
	* @author LiuGuoHui
	 */
	@RequestMapping(value="queryAllLogForPage", method = { RequestMethod.POST,
			RequestMethod.GET })
	@ResponseBody
	public Result queryAllLogForPage(LogVo logVo,Page page) {
		System.out.println("date"+logVo.getDate());
		return logService.queryAllLogForPage(page,logVo);
	}
	@RequestMapping(value="delLog", method = { RequestMethod.POST,
			RequestMethod.GET })
	@ResponseBody
	public Result delLog(String rzid) { 	
		return logService.delLog(rzid);
	}
	@RequestMapping(value="testRZ", method = { RequestMethod.POST,
			RequestMethod.GET })
	@ResponseBody
	public void test() throws Exception{
		Log rz=exceptionUtil.buildRZ("500","数组越界异常test");
		exceptionUtil.addLog(rz);
		throw new ArrayIndexOutOfBoundsException("数组越界异常test");
	}
}
