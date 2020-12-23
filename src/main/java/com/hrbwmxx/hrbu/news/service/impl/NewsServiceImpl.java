package com.hrbwmxx.hrbu.news.service.impl;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hrbwmxx.framework.fileUpload.service.FileUploadService;
import com.hrbwmxx.framework.job.clean.CleanDataJob;
import com.hrbwmxx.framework.model.Page;
import com.hrbwmxx.framework.model.Result;
import com.hrbwmxx.framework.model.ResultEntity;
import com.hrbwmxx.framework.model.ResultPage;
import com.hrbwmxx.framework.security.SecurityContext;
import com.hrbwmxx.framework.security.user.UserContext;
import com.hrbwmxx.framework.util.ExceptionUtil;
import com.hrbwmxx.framework.util.PropertiesUtil;
import com.hrbwmxx.framework.util.StringSplit;
import com.hrbwmxx.hrbu.log.model.Log;
import com.hrbwmxx.hrbu.news.dao.NewsContextMapper;
import com.hrbwmxx.hrbu.news.dao.NewsMapper;
import com.hrbwmxx.hrbu.news.entity.News;
import com.hrbwmxx.hrbu.news.entity.NewsContext;
import com.hrbwmxx.hrbu.news.service.INewsService;
import com.hrbwmxx.hrbu.news.vo.NewsCustom;
import com.hrbwmxx.hrbu.news.vo.NewsVO;
import com.hrbwmxx.system.model.Login;
/*import com.hrbwmxx.news.service.impl.IFileService;*/
@Service
public class NewsServiceImpl implements INewsService {
	@Autowired
	private NewsMapper newsMapper;
	@Autowired
	private NewsContextMapper newsContextMapper;
	@Autowired
	private FileUploadService fileUploadService;
	@Autowired
	private CleanDataJob cleanDataJob;
	@Autowired
    private ExceptionUtil exceptionUtil;
	private String getNr(String id) {
		String nr = "";
		// 根据通知Id找到通知的内容List
		List<NewsContext> list = newsContextMapper.getNRListByID(id);
		// 拼接内容信息
		if (list.size() != 0) {
			for (NewsContext tz : list) {
				nr += tz.getContext();
			}
		}
		return nr;
	}
	@Override
	public Result queryNewsListPage(Page page, NewsCustom obj) {
		ResultPage re = new ResultPage();
		List<NewsCustom> newsCustomListAll=new ArrayList<NewsCustom>();
		try {
			List<NewsCustom> newsCustomList = newsMapper.queryNewsListPage(page,obj);
			//循环拼接得到内容
			for (NewsCustom news : newsCustomList) {
				NewsCustom newgg = news;
				newgg.setContext(this.getNr(news.getId()));
				newsCustomListAll.add(newgg);
			}
			int count = newsMapper.queryNewsForCount(page,obj);
			re.setPageNo(page.getPageNo());
			re.setPageSize(page.getPageSize());
			re.setRows(newsCustomListAll);
			re.setTotalCount(count);
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
	public Result queryNewsList(NewsCustom newsCustom) {
		ResultEntity re = new ResultEntity();
		List<NewsCustom> newsCustomListAll=new ArrayList<NewsCustom>();
		try {
			List<NewsCustom> newsCustomList = newsMapper.queryNewsList(newsCustom);
			//循环拼接得到内容
			for (NewsCustom news : newsCustomList) {
				NewsCustom newgg = news;
				newgg.setContext(this.getNr(news.getId()));
				newsCustomListAll.add(newgg);
			}
			re.setList(newsCustomListAll);
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
	public Result queryNewsByNewsColumnid(String newsColumnid) {
		ResultEntity re = new ResultEntity();
		List<News> newsCustomListAll=new ArrayList<News>();
		try {
			List<News> newsCustomList = newsMapper.queryNewsByNewsColumnid(newsColumnid);
			//循环拼接得到内容
			for (News news : newsCustomList) {
				News newgg = news;
				newgg.setContext(this.getNr(news.getId()));
				newsCustomListAll.add(newgg);
			}
			re.setList(newsCustomListAll);
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
	public Result queryNewsById(NewsCustom obj) {
		NewsVO adv = new NewsVO();
		try {
			if(obj.getId()!=null&&!"".equals(obj.getId())){
				NewsCustom ad = newsMapper.queryNewsById(obj);
				if(ad!=null) {
					ad.setContext(this.getNr(ad.getId()));
					//查看之后增加阅读次数
					String readTimes= String.valueOf(Integer.parseInt(ad.getReadTimes())+1);
					News news = new News();
					news.setId(obj.getId());
					news.setReadTimes(readTimes);
					newsMapper.updateNewsData(news);
					Map<String, String>  map_request=new HashMap<String, String>();
					if (ad.getAttachmentfile()!=null){
						map_request.put("attachId",ad.getAttachmentfile());
						List<Map<String, String>> list_file_attachment = fileUploadService.queryAttrList(map_request);
						ad.setList_file_attachment(list_file_attachment);
					}
					if (ad.getPic()!=null){
					map_request.put("attachId",ad.getPic());
					List<Map<String, String>> list_file_pic = fileUploadService.queryAttrList(map_request);
					ad.setList_file_pic(list_file_pic);
					}
				}else{
					adv.setErrcode("500");
					adv.setErrmsg("数据已删除");
					String classname = new Exception().getStackTrace()[1].getClassName(); //获取调用者的类名
					String method_name = new Exception().getStackTrace()[1].getMethodName(); //获取调用者的方法名
					Log rz=exceptionUtil.buildRZ(adv.getErrcode(),adv.getErrmsg()+"发生在"+classname+"的"+method_name+"方法上");
					exceptionUtil.addLog(rz);
				}
				adv.setNewsCustom(ad);
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
	public Result insertNewsData(News obj,HttpServletRequest request) {
		NewsVO adv = new NewsVO();
		try {
			if(obj!=null){
				String id=UUID.randomUUID().toString();
				obj.setId(id);
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String editTime=sdf.format(new Date());
				obj.setEditTime(editTime);
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
				   obj.setEditUserid(userid);
				}
				obj.setState("N");//设置成编辑状态
				//使用文本编辑器传入图片了
				if (obj.getPic()!=null && !"".equals(obj.getPic())){
					Properties properties = PropertiesUtil.getKey("system.properties");
					String baseHttpPath=properties.getProperty("basePath")+"/FileService/showImg.do?attachId="+obj.getPic();
					obj.setFilePath(baseHttpPath);
				}
				
				if (obj.getPic()!=null){
					fileUploadService.updateFileStateByIds(obj.getPic(), "1");
				}
				if (obj.getAttachmentfile()!=null){
					fileUploadService.updateFileStateByIds(obj.getAttachmentfile(), "1");
				}
				if (obj.getContext()!=null){
					fileUploadService.updateFileStateByContext(obj.getContext(), "1");
				}
				newsMapper.saveNewsData(obj);//保存新闻
				this.addContext(obj.getContext(),id);
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
	//保存内容
	private void addContext(String contexts,String id) throws Exception{
		 List<String> contextList=StringSplit.getListFromContent(contexts,2000);
		 int count=1;
		 for(String context:contextList) {
			NewsContext newsContext=new NewsContext();
			newsContext.setId(id);
			newsContext.setContext(context);
			newsContext.setCount(new Integer(count).toString());
			newsContextMapper.saveContext(newsContext);
			count++;
		  }
	}

	@Transactional
	@Override
	public Result updateNewsData(News obj,HttpServletRequest request) {
		NewsVO adv = new NewsVO();
		
		try {
			if(obj!=null){
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String editTime=sdf.format(new Date());
				obj.setEditTime(editTime);
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
				   obj.setEditUserid(userid);
				}
				//图片处理
				if (obj.getPic()!=null && !"".equals(obj.getPic())){
					Properties properties = PropertiesUtil.getKey("system.properties");
					String baseHttpPath=properties.getProperty("basePath")+"/FileService/showImg.do?attachId="+obj.getPic();
					obj.setFilePath(baseHttpPath);
				}
				//obj.setState("N");//设置成编辑状态
				
				NewsCustom temp  =new NewsCustom();
				temp.setId(obj.getId());
				NewsCustom objCustom = newsMapper.queryNewsById(temp);
				//根据原来的新闻找到原来新闻的内容
				if(objCustom!=null) {
					objCustom.setContext(this.getNr(objCustom.getId()));
				}
				//旧文件
				//删除旧文件
				/*if (objCustom.getPic() != null) {
					cleanDataJob.deletePicOrAttachmentfile(objCustom.getPic());
				}
				if (objCustom.getAttachmentfile() != null) {
					cleanDataJob.deletePicOrAttachmentfile(objCustom.getAttachmentfile());
				}
				if (objCustom.getContext() != null) {
					cleanDataJob.deletePicOrAttachmentfile(cleanDataJob.getFiles(objCustom.getContext()));
				}*/
				//新文件
				if (obj.getPic()!=null){
					fileUploadService.updateFileStateByIds(obj.getPic(), "1");
				}
				if (obj.getAttachmentfile()!=null){
					fileUploadService.updateFileStateByIds(obj.getAttachmentfile(), "1");
				}
				if (obj.getContext()!=null){
					fileUploadService.updateFileStateByContext(obj.getContext(), "1");
				}
				newsMapper.updateNewsData(obj);
				//首先删除原来新闻的新闻内容然后重新加入
				newsContextMapper.deleteContext(obj.getId());
				this.addContext(obj.getContext(),obj.getId());
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
	@Override
	public Result publishNewsData(News obj,HttpServletRequest request) {
		NewsVO adv = new NewsVO();
		try {
			if(obj.getId()!=null&&!"".equals(obj.getId())){
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String publishTime=sdf.format(new Date());
				obj.setPublishTime(publishTime);
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
					  obj.setPublishUserid(userid);
				}
				obj.setState("Y");//设置成发布状态
				newsMapper.updateNewsData(obj);
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
	
	
	@Override
	public Result cancelPublishNewsData(News obj) {
		NewsVO adv = new NewsVO();
		try {
			if(obj.getId()!=null&&!"".equals(obj.getId())){
				obj.setPublishTime("");
				obj.setPublishUserid("");
			    obj.setState("N");//设置成编辑状态
				newsMapper.updateNewsData(obj);
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
	public Result deleteNewsDataById(News obj) {
		ResultEntity re = new ResultEntity();
		try {
			if(obj.getId()!=null){
				NewsCustom temp = new NewsCustom();
				temp.setId(obj.getId());
				NewsCustom objCustom = newsMapper.queryNewsById(temp);
				if(objCustom!=null) {
					objCustom.setContext(this.getNr(objCustom.getId()));
				}
				if (objCustom.getPic() != null) {
					cleanDataJob.deletePicOrAttachmentfile(objCustom.getPic());
				}
				if (objCustom.getAttachmentfile() != null) {
					cleanDataJob.deletePicOrAttachmentfile(objCustom.getAttachmentfile());
				}
				if (objCustom.getContext() != null) {
					cleanDataJob.deletePicOrAttachmentfile(cleanDataJob.getFiles(objCustom.getContext()));
				}
				newsContextMapper.deleteContext(obj.getId());
				newsMapper.deleteNewsDataById(obj);
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
	   
    
}
