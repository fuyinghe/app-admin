package com.hrbwmxx.hrbu.news.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hrbwmxx.framework.model.Page;
import com.hrbwmxx.framework.model.Result;
import com.hrbwmxx.framework.util.ExceptionUtil;
import com.hrbwmxx.hrbu.log.model.Log;
import com.hrbwmxx.hrbu.news.dao.NewsColumnMapper;
import com.hrbwmxx.hrbu.news.entity.NewsColumn;
import com.hrbwmxx.hrbu.news.service.INewsColumnService;
import com.hrbwmxx.hrbu.news.vo.NewsColumnCustom;
import com.hrbwmxx.hrbu.news.vo.NewsPageVO;
import com.hrbwmxx.hrbu.news.vo.NewsVO;

@Service
public class NewsColumnServiceImpl implements INewsColumnService {
	@Autowired
	private NewsColumnMapper newsColumnMapper;
	@Autowired
    private ExceptionUtil exceptionUtil;
	@Override
	public Result queryNewsColumnById(NewsColumnCustom obj) {
		NewsVO re=new NewsVO();
		try {
			obj=newsColumnMapper.queryNewsColumnById(obj);
			re.setNewColumn(obj);
			if (obj==null){
				re.setErrcode("500");
				re.setErrmsg("数据已删除");
				String classname = new Exception().getStackTrace()[1].getClassName(); //获取调用者的类名
				String method_name = new Exception().getStackTrace()[1].getMethodName(); //获取调用者的方法名
				Log rz=exceptionUtil.buildRZ(re.getErrcode(),re.getErrmsg()+"发生在"+classname+"的"+method_name+"方法上");
				exceptionUtil.addLog(rz);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			re.setErrcode("500");
			re.setErrmsg("操作失败");
			String classname = new Exception().getStackTrace()[1].getClassName(); //获取调用者的类名
			String method_name = new Exception().getStackTrace()[1].getMethodName(); //获取调用者的方法名
			Log rz=exceptionUtil.buildRZ(re.getErrcode(),re.getErrmsg()+"发生在"+classname+"的"+method_name+"方法上");
			exceptionUtil.addLog(rz);
			
		}
		return re;
	}
	@Transactional
	@Override
	public Result saveNewsColumnData(NewsColumn obj) {
		Result re=new NewsVO();
		try {
			String id=UUID.randomUUID().toString();
			obj.setId(id);
			obj.setReleaseState("N");
			obj.setState("Y");
			newsColumnMapper.saveNewsColumnData(obj);
		} catch (Exception e) {
			e.printStackTrace();
			re.setErrcode("500");
			re.setErrmsg("操作失败");
			String classname = new Exception().getStackTrace()[1].getClassName(); //获取调用者的类名
			String method_name = new Exception().getStackTrace()[1].getMethodName(); //获取调用者的方法名
			Log rz=exceptionUtil.buildRZ(re.getErrcode(),re.getErrmsg()+"发生在"+classname+"的"+method_name+"方法上");
			exceptionUtil.addLog(rz);
		}
		return  re;
	}
	@Transactional
	@Override
	public Result updateNewsColumnData(NewsColumn obj) {
		Result  re=new NewsVO();
		try {
			newsColumnMapper.updateNewsColumnData(obj);
		} catch (Exception e) {
			e.printStackTrace();
			re.setErrcode("500");
			re.setErrmsg("操作失败");
			String classname = new Exception().getStackTrace()[1].getClassName(); //获取调用者的类名
			String method_name = new Exception().getStackTrace()[1].getMethodName(); //获取调用者的方法名
			Log rz=exceptionUtil.buildRZ(re.getErrcode(),re.getErrmsg()+"发生在"+classname+"的"+method_name+"方法上");
			exceptionUtil.addLog(rz);
		}
		return  re;
	}
	@Transactional
	@Override
	public Result deleteNewsColumnDataById(NewsColumn obj) {
		Result re=new NewsVO();
		try {
			newsColumnMapper.deleteNewsColumnDataById(obj);
		} catch (Exception e) {
			e.printStackTrace();
			re.setErrcode("500");
			re.setErrmsg("操作失败");
			String classname = new Exception().getStackTrace()[1].getClassName(); //获取调用者的类名
			String method_name = new Exception().getStackTrace()[1].getMethodName(); //获取调用者的方法名
			Log rz=exceptionUtil.buildRZ(re.getErrcode(),re.getErrmsg()+"发生在"+classname+"的"+method_name+"方法上");
			exceptionUtil.addLog(rz);
		}
		return  re;
	}
	@Override
	public Result queryNewsColumnList(NewsColumnCustom obj) {
		NewsVO re=new NewsVO();
		try {
			List<NewsColumnCustom> list = newsColumnMapper.queryNewsColumnList(obj);
			re.setNewColumnList(list);
		} catch (Exception e) {
			e.printStackTrace();
			re.setErrcode("500");
			re.setErrmsg("操作失败");
			String classname = new Exception().getStackTrace()[1].getClassName(); //获取调用者的类名
			String method_name = new Exception().getStackTrace()[1].getMethodName(); //获取调用者的方法名
			Log rz=exceptionUtil.buildRZ(re.getErrcode(),re.getErrmsg()+"发生在"+classname+"的"+method_name+"方法上");
			exceptionUtil.addLog(rz);
		}
		return re;
	}

	@Override
	public Result queryNewsColumnListPage(Page page, NewsColumnCustom obj) {
		NewsPageVO re=new NewsPageVO();
		try {
			List<NewsColumnCustom> list = newsColumnMapper.queryNewsColumnListPage(page,obj);
			int count=newsColumnMapper.queryNewsColumnListCount(page,obj);
			re.setTotalCount(count);
			re.setNewsColumnList(list);
		} catch (Exception e) {
			e.printStackTrace();
			re.setErrcode("500");
			re.setErrmsg("操作失败");
			String classname = new Exception().getStackTrace()[1].getClassName(); //获取调用者的类名
			String method_name = new Exception().getStackTrace()[1].getMethodName(); //获取调用者的方法名
			Log rz=exceptionUtil.buildRZ(re.getErrcode(),re.getErrmsg()+"发生在"+classname+"的"+method_name+"方法上");
			exceptionUtil.addLog(rz);
		}
		return re;
	}
 
}
