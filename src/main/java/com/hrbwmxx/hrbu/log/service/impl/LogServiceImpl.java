package com.hrbwmxx.hrbu.log.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hrbwmxx.framework.model.Page;
import com.hrbwmxx.framework.model.Result;
import com.hrbwmxx.framework.model.ResultEntity;
import com.hrbwmxx.framework.model.ResultPage;
import com.hrbwmxx.framework.util.StringSplit;
import com.hrbwmxx.hrbu.log.service.ILogService;
import com.hrbwmxx.hrbu.log.vo.LogVo;
import com.hrbwmxx.system.model.Login;
import com.hrbwmxx.hrbu.log.dao.LogMapper;
import com.hrbwmxx.hrbu.log.model.Log;

@Service
public class LogServiceImpl implements ILogService {
	@Autowired
	private LogMapper logMapper;
	

	public Result queryAllLogForPage(Page page, LogVo logVo) {
		ResultPage result = new ResultPage();
		try {
			if(logVo.getDate()!=null) {
				String [] strs=logVo.getDate().split(",");
				if(strs[0].equals("*")) {
					logVo.setBeginTime("");
				}else {
					logVo.setBeginTime(strs[0]);
				}
				if(strs[1].equals("*")) {
					logVo.setEndTime("");
				}else {
				  logVo.setEndTime(strs[1]);
			    }
			}
			List<Log> logs = logMapper.queryAllLogForPage(page, logVo);
			int count = logMapper.queryLogForCount(page,logVo);
			result.setPageNo(page.getPageNo());
			result.setPageSize(page.getPageSize());
			result.setRows(logs);
			result.setTotalCount(count);
			System.out.println("kkkk"+logs.size()+"ppp"+count);
		} catch (Exception e) {
			e.printStackTrace();
			result.setErrcode("500");
			result.setErrmsg("操作失败");
		}
		return result;
	}


	@Override
	public Result delLog(String rzid) {
		ResultEntity re = new ResultEntity();
		try {
			if(rzid!=null){
				logMapper.delLog(rzid);
			}else{
				re.setErrcode("500");
				re.setErrmsg("操作失败");	
			}
		} catch (Exception e) {
			e.printStackTrace();
			re.setErrcode("500");
			re.setErrmsg("操作失败");
		}
		return re;
	}
}
