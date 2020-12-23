package com.hrbwmxx.hrbu.tzgg.service.impl;

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
import com.hrbwmxx.framework.util.ExceptionUtil;
import com.hrbwmxx.framework.util.StringSplit;
import com.hrbwmxx.hrbu.log.model.Log;
import com.hrbwmxx.hrbu.tzgg.dao.TZGGMapper;
import com.hrbwmxx.hrbu.tzgg.dao.TZNRMapper;
import com.hrbwmxx.hrbu.tzgg.model.TZNR;
import com.hrbwmxx.hrbu.tzgg.service.ITZGGService;
import com.hrbwmxx.hrbu.tzgg.vo.TZGGCustom;
import com.hrbwmxx.hrbu.tzgg.vo.TZGGVO;
import com.hrbwmxx.system.model.Login;

@Service
public class TZGGServiceImpl implements ITZGGService {
	@Autowired
	private TZGGMapper tzggMapper;
	@Autowired
	private TZNRMapper tznrMapper;
	@Autowired
    private ExceptionUtil exceptionUtil;
	public Result queryAllTzggForPage(Page page, TZGGCustom tzgg) {
		ResultPage result = new ResultPage();
		List<TZGGCustom> tzggList = new ArrayList<TZGGCustom>();
		try {
			List<TZGGCustom> tzggs = tzggMapper.queryAllTzggForPage(page, tzgg);
			// 根据新闻ID找到内容
			for (TZGGCustom ztgg : tzggs) {
				TZGGCustom newgg = ztgg;
				newgg.setNr(this.getNr(ztgg.getTzid()));
				tzggList.add(newgg);
			}
			int count = tzggMapper.queryTZggForCount(page, tzgg);
			result.setPageNo(page.getPageNo());
			result.setPageSize(page.getPageSize());
			result.setRows(tzggList);
			result.setTotalCount(count);
		} catch (Exception e) {
			e.printStackTrace();
			
			result.setErrcode("500");
			result.setErrmsg("操作失败");
			String classname = new Exception().getStackTrace()[1].getClassName(); //获取调用者的类名
			String method_name = new Exception().getStackTrace()[1].getMethodName(); //获取调用者的方法名
			Log rz=exceptionUtil.buildRZ(result.getErrcode(),result.getErrmsg()+"发生在"+classname+"的"+method_name+"方法上");
			exceptionUtil.addLog(rz);
		}
		return result;
	}


	@Override
	public Result queryTZggList(TZGGCustom obj) {
		ResultEntity re = new ResultEntity();
		List<TZGGCustom> tzggList = new ArrayList<TZGGCustom>();
		try {
			List<TZGGCustom> tzggs = tzggMapper.queryTZggList(obj);
			// 根据新闻ID找到内容
			for (TZGGCustom ztgg : tzggs) {
				TZGGCustom newgg = ztgg;
				newgg.setNr(this.getNr(ztgg.getTzid()));
				tzggList.add(newgg);
			}
			re.setList(tzggList);
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
	public Result queryTzggById(TZGGCustom obj) {
		
		TZGGVO re = new TZGGVO();
		try {
			 TZGGCustom tzggs = tzggMapper.queryTzggById(obj);
			 // 根据新闻ID找到内容
			 tzggs.setNr(this.getNr(obj.getTzid()));
			 re.setTzggCustom(tzggs);
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
	public Result saveTZggData(TZGGCustom obj, HttpServletRequest request) {
		TZGGVO adv = new TZGGVO();
		try {
			if(obj!=null){
				String id=UUID.randomUUID().toString();
				obj.setTzid(id);
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String editTime=sdf.format(new Date());
				obj.setBjsj(editTime);
				Login sessionLogin = (Login) request.getSession().getAttribute("LoginUserSession");
				if(sessionLogin==null) {
					adv.setErrcode("403");
					adv.setErrmsg("系统监测到用户未登录");
					String classname = new Exception().getStackTrace()[1].getClassName(); //获取调用者的类名
					String method_name = new Exception().getStackTrace()[1].getMethodName(); //获取调用者的方法名
					Log rz=exceptionUtil.buildRZ(adv.getErrcode(),adv.getErrmsg()+"发生在"+classname+"的"+method_name+"方法上");
					exceptionUtil.addLog(rz);
					return  adv;
				}else {
				   String userid=String.valueOf(sessionLogin.getUserId());
				   obj.setBjyhid(userid);
				}
				obj.setZt("1");
				obj.setYdrs(0);
				tzggMapper.saveTZggData(obj);//保存新闻
				this.addContext(obj.getNr(),id);
			}else{
				adv.setErrcode("500");
				adv.setErrmsg("操作失败");	
				String classname = new Exception().getStackTrace()[1].getClassName(); //获取调用者的类名
				String method_name = new Exception().getStackTrace()[1].getMethodName(); //获取调用者的方法名
				Log rz=exceptionUtil.buildRZ(adv.getErrcode(),adv.getErrmsg()+"发生在"+classname+"的"+method_name+"方法上");
				exceptionUtil.addLog(rz);
			}
		} catch (Exception e) {
			e.printStackTrace();
			adv.setErrcode("500");
			adv.setErrmsg("操作失败");
			String classname = new Exception().getStackTrace()[1].getClassName(); //获取调用者的类名
			String method_name = new Exception().getStackTrace()[1].getMethodName(); //获取调用者的方法名
			Log rz=exceptionUtil.buildRZ(adv.getErrcode(),adv.getErrmsg()+"发生在"+classname+"的"+method_name+"方法上");
			exceptionUtil.addLog(rz);
		}
		return adv;
	}
	@Transactional
	@Override
	public Result updateTZggData(TZGGCustom obj, HttpServletRequest request) {

		TZGGVO adv = new TZGGVO();
		try {
			if(obj!=null){
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String editTime=sdf.format(new Date());
				obj.setBjsj(editTime);
				Login sessionLogin = (Login) request.getSession().getAttribute("LoginUserSession");
				if(sessionLogin==null) {
					adv.setErrcode("403");
					adv.setErrmsg("系统监测到用户未登录");
					String classname = new Exception().getStackTrace()[1].getClassName(); //获取调用者的类名
					String method_name = new Exception().getStackTrace()[1].getMethodName(); //获取调用者的方法名
					Log rz=exceptionUtil.buildRZ(adv.getErrcode(),adv.getErrmsg()+"发生在"+classname+"的"+method_name+"方法上");
					exceptionUtil.addLog(rz);
					return  adv;
				}else {
				   String userid=String.valueOf(sessionLogin.getUserId());
				   obj.setBjyhid(userid);
				}
				tzggMapper.updateTZggData(obj);
				//首先删除原来新闻的新闻内容然后重新加入
				tznrMapper.deleteContext(obj.getTzid());
				this.addContext(obj.getNr(),obj.getTzid());
			}else{
				adv.setErrcode("500");
				adv.setErrmsg("操作失败");	
				String classname = new Exception().getStackTrace()[1].getClassName(); //获取调用者的类名
				String method_name = new Exception().getStackTrace()[1].getMethodName(); //获取调用者的方法名
				Log rz=exceptionUtil.buildRZ(adv.getErrcode(),adv.getErrmsg()+"发生在"+classname+"的"+method_name+"方法上");
				exceptionUtil.addLog(rz);
			}	
			
		} catch (Exception e) {
			e.printStackTrace();
			adv.setErrcode("500");
			adv.setErrmsg("操作失败");
			String classname = new Exception().getStackTrace()[1].getClassName(); //获取调用者的类名
			String method_name = new Exception().getStackTrace()[1].getMethodName(); //获取调用者的方法名
			Log rz=exceptionUtil.buildRZ(adv.getErrcode(),adv.getErrmsg()+"发生在"+classname+"的"+method_name+"方法上");
			exceptionUtil.addLog(rz);
		}
		return adv;
	
	}
	@Transactional
	@Override
	public Result publishTZggData(TZGGCustom obj, HttpServletRequest request) {

		TZGGVO adv = new TZGGVO();
		try {
			if(obj.getTzid()!=null&&!"".equals(obj.getTzid())){
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String publishTime=sdf.format(new Date());
				obj.setFbsj(publishTime);
				Login sessionLogin = (Login) request.getSession().getAttribute("LoginUserSession");
				if(sessionLogin==null) {
					  adv.setErrcode("403");
					  adv.setErrmsg("系统监测到用户未登录");	
						String classname = new Exception().getStackTrace()[1].getClassName(); //获取调用者的类名
						String method_name = new Exception().getStackTrace()[1].getMethodName(); //获取调用者的方法名
						Log rz=exceptionUtil.buildRZ(adv.getErrcode(),adv.getErrmsg()+"发生在"+classname+"的"+method_name+"方法上");
						exceptionUtil.addLog(rz);
					  return adv;
				} else {
					  String userid=String.valueOf(sessionLogin.getUserId());
					  obj.setFbyhid(userid);
				}
				obj.setZt("2");//设置成发布状态
				tzggMapper.updateTZggData(obj);
			}else{
				adv.setErrcode("500");
				adv.setErrmsg("操作失败");	
				String classname = new Exception().getStackTrace()[1].getClassName(); //获取调用者的类名
				String method_name = new Exception().getStackTrace()[1].getMethodName(); //获取调用者的方法名
				Log rz=exceptionUtil.buildRZ(adv.getErrcode(),adv.getErrmsg()+"发生在"+classname+"的"+method_name+"方法上");
				exceptionUtil.addLog(rz);
			}
		} catch (Exception e) {
			e.printStackTrace();
			adv.setErrcode("500");
			adv.setErrmsg("操作失败");
			String classname = new Exception().getStackTrace()[1].getClassName(); //获取调用者的类名
			String method_name = new Exception().getStackTrace()[1].getMethodName(); //获取调用者的方法名
			Log rz=exceptionUtil.buildRZ(adv.getErrcode(),adv.getErrmsg()+"发生在"+classname+"的"+method_name+"方法上");
			exceptionUtil.addLog(rz);
		}
		return adv;
	
	}
	@Transactional
	@Override
	public Result cancelPublishTZggData(TZGGCustom obj) {
		TZGGVO adv = new TZGGVO();
		try {
			if(obj.getTzid()!=null&&!"".equals(obj.getTzid())){
				obj.setFbsj("");
				obj.setFbyhid("");
			    obj.setZt("1");//设置成编辑状态
			    tzggMapper.updateTZggData(obj);
			}else{
				adv.setErrcode("500");
				adv.setErrmsg("操作失败");	
				String classname = new Exception().getStackTrace()[1].getClassName(); //获取调用者的类名
				String method_name = new Exception().getStackTrace()[1].getMethodName(); //获取调用者的方法名
				Log rz=exceptionUtil.buildRZ(adv.getErrcode(),adv.getErrmsg()+"发生在"+classname+"的"+method_name+"方法上");
				exceptionUtil.addLog(rz);
			}
		} catch (Exception e) {
			e.printStackTrace();
			adv.setErrcode("500");
			adv.setErrmsg("操作失败");
			String classname = new Exception().getStackTrace()[1].getClassName(); //获取调用者的类名
			String method_name = new Exception().getStackTrace()[1].getMethodName(); //获取调用者的方法名
			Log rz=exceptionUtil.buildRZ(adv.getErrcode(),adv.getErrmsg()+"发生在"+classname+"的"+method_name+"方法上");
			exceptionUtil.addLog(rz);
		}
		return adv;
	}

	@Transactional
	@Override
	public Result deleteTZggDataById(TZGGCustom obj) {
		ResultEntity re = new ResultEntity();
		try {
			if(obj.getTzid()!=null){
				tznrMapper.deleteContext(obj.getTzid());
				tzggMapper.deleteTZggDataById(obj);
			}else{
				re.setErrcode("500");
				re.setErrmsg("操作失败");	
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

	private String getNr(String tzid) {
		String nr = "";
		// 根据通知Id找到通知的内容List
		List<TZNR> list = tznrMapper.getNRListByID(tzid);
		// 拼接内容信息
		if (list.size() != 0) {
			for (TZNR tz : list) {
				nr += tz.getNr();
			}
		}
		return nr;
	}
	
	//保存内容
	private void addContext(String contexts,String id) throws Exception{
		if(contexts!=null&&!contexts.equals("")) {
			 List<String> contextList=StringSplit.getListFromContent(contexts,2000);
			 int count=1;
			 for(String context:contextList) {
				TZNR tznr=new TZNR();
				tznr.setTzid(id);
				tznr.setNr(context);
				tznr.setXh(new Integer(count).toString());
				tznrMapper.saveContext(tznr);
				count++;
			  }
		}
			
		}
}
