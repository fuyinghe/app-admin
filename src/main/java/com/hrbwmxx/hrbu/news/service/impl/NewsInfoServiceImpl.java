package com.hrbwmxx.hrbu.news.service.impl;

import org.springframework.stereotype.Service;

import com.hrbwmxx.framework.fileUpload.service.INewsFileUploadService;
import com.hrbwmxx.framework.job.clean.CleanDataJob;
import com.hrbwmxx.framework.model.Page;
import com.hrbwmxx.framework.model.Result;
import com.hrbwmxx.framework.model.ResultEntity;
import com.hrbwmxx.framework.model.ResultPage;
import com.hrbwmxx.framework.util.ExceptionUtil;
import com.hrbwmxx.hrbu.log.model.Log;
import com.hrbwmxx.hrbu.news.dao.NewsInfoMapper;
import com.hrbwmxx.hrbu.news.service.INewsInfoService;
import com.hrbwmxx.hrbu.news.vo.NewsInfoCustom;
import com.hrbwmxx.hrbu.news.vo.NewsInfoVO;
import com.hrbwmxx.system.model.Login;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;


@Service
public class NewsInfoServiceImpl implements INewsInfoService {
	// 新闻
	@Autowired
	private NewsInfoMapper newsInfoMapper;
	// 图片
	@Autowired
	private INewsFileUploadService newsFileService;
	// 异常
	@Autowired
    private ExceptionUtil exceptionUtil;
	
	/**
	 * 新闻列表(分页)
	 */
	@Override
	public Result queryNewsInfoListPage(Page page, NewsInfoCustom obj) {
		ResultPage re = new ResultPage();
		List<NewsInfoCustom> newsCustomListAll = new ArrayList<NewsInfoCustom>();
		try {
			newsCustomListAll = newsInfoMapper.queryNewsInfoListPage(page, obj);
			/*for (NewsInfoCustom info : newsCustomListAll) {
				// 图回显
				Map<String, String> map_request = new HashMap<String, String>();
				if (info.getPic() != null) {
					map_request.put("attachId", info.getPic());
					List<Map<String, String>> list_file_pic = fileService.queryAttrList(map_request);
					info.setList_file_pic(list_file_pic);
				}
			}*/
			int count = newsInfoMapper.queryNewsInfoForCount(page, obj);
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

	/**
	 * 新闻列表-未分页
	 */
	@Override
	public Result queryNewsInfoList(NewsInfoCustom obj) {
		ResultEntity re = new ResultEntity();
		List<NewsInfoCustom> newsCustomListAll = new ArrayList<NewsInfoCustom>();
		try {
			newsCustomListAll = newsInfoMapper.queryNewsInfoList(obj);
			/*for (NewsInfoCustom info : newsCustomListAll) {
				// 图回显
				Map<String, String> map_request = new HashMap<String, String>();
				if (info.getPic() != null) {
					map_request.put("attachId", info.getPic());
					List<Map<String, String>> list_file_pic = fileService.queryAttrList(map_request);
					info.setList_file_pic(list_file_pic);
				}
			}*/
			re.setList(newsCustomListAll);
		} catch (Exception e) {
			e.printStackTrace();
			re.setErrcode("500");
			re.setErrmsg("操作失败");
		}
		return re;
	}

	/**
	 * 查询单条新闻
	 */
	@Override
	public Result queryNewsInfoById(NewsInfoCustom obj) {
		NewsInfoVO re = new NewsInfoVO();
		try {
			if (obj.getId() != null && !"".equals(obj.getId())) {
				NewsInfoCustom info = newsInfoMapper.queryNewsInfoById(obj);
				if (info != null) {
					// 查看之后增加阅读次数
					String readTimes = String.valueOf(Integer.parseInt(info.getReadTimes()) + 1);
					NewsInfoCustom news = new NewsInfoCustom();
					news.setId(info.getId());
					news.setReadTimes(readTimes);
					newsInfoMapper.updateNewsInfoForTimes(news);
					// 图回显
					Map<String, String> map_request = new HashMap<String, String>();
					if (info.getPic() != null) {
						map_request.put("attachId", info.getPic());
						List<Map<String, String>> list_file_pic = newsFileService.queryAttrList(map_request);
						info.setList_file_pic(list_file_pic);
					}
				} else {
					re.setErrcode("500");
					re.setErrmsg("数据已删除");
				}
				re.setNewsInfoCustom(info);
			} else {
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
	/**
	 * 添加新闻信息
	 */
	@Override
	public Result saveNewsData(NewsInfoCustom obj, HttpServletRequest request) {
		NewsInfoVO adv = new NewsInfoVO();
		try {
			if(obj != null) {
				//设置主键
				String id = UUID.randomUUID().toString();
				obj.setId(id);
				//设置发布时间
				if (obj.getReleaseDate() == null || "".equals(obj.getReleaseDate())) {
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					String newsTime=sdf.format(new Date());
					obj.setReleaseDate(newsTime);
				}
				//阅读次数
				if (obj.getReadTimes() == null || "".equals(obj.getReadTimes())) {
					obj.setReadTimes("0");
				}
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
				   obj.setUserId(userid);
				}
				obj.setState("N");//设置成编辑状态
				
				//如果添加了图片
				if (obj.getPic()!=null){
					newsFileService.updateFileStateByIds(obj.getPic(), "1");
				}
				//保存内容
				newsInfoMapper.saveNewsInfo(obj);
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
	/**
	 * 修改新闻信息
	 */
	@Override
	public Result updateNewsInfo(NewsInfoCustom obj, HttpServletRequest request) {
		NewsInfoVO adv = new NewsInfoVO();
		try {
			if(obj != null) {
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
				   obj.setUserId(userid);
				}
				//查一下原来的内容
				NewsInfoCustom info = new NewsInfoCustom();
				info.setId(obj.getId());
				info = newsInfoMapper.queryNewsInfoById(info);
				//判断下图片是否为空  (如为空,查原图片是否为空,不为空则删)
				if ("".equals(obj.getPic()) && null == obj.getPic()) {
					if (!"".equals(info.getPic()) && info.getPic()!= null ) {
						//删除原图片
						deletePicOrAttachmentfile(info.getPic());
					}
				}else {
					//原来的是否为空
					if (info.getPic()!= null && !"".equals(info.getPic())) {
						//不为空,判断下是否与原来的相同
						if(!obj.getPic().equals(info.getPic())) {
							//删除原图片
							deletePicOrAttachmentfile(info.getPic());
						}
					 }
					//改新图片状态
					newsFileService.updateFileStateByIds(obj.getPic(), "1");
				   }
				//修改内容
				newsInfoMapper.updateNewsInfo(obj);
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
	/**
	 * 发布新闻信息
	 */
	@Override
	public Result publishNewsInfo(NewsInfoCustom obj, HttpServletRequest request) {
		NewsInfoVO adv = new NewsInfoVO();
		try {
			if(obj.getId()!=null&&!"".equals(obj.getId())){
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String publishTime=sdf.format(new Date());
				obj.setReleaseDate(publishTime);
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
					  obj.setUserId(userid);
				}
				obj.setState("Y");//设置成发布状态
				newsInfoMapper.cancelOrPublishNewsInfo(obj);
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
	/**
	 * 取消发布信息
	 */
	@Override
	public Result cancelPublishNewsInfo(NewsInfoCustom obj, HttpServletRequest request) {
		NewsInfoVO adv = new NewsInfoVO();
		try {
			if(obj.getId()!=null&&!"".equals(obj.getId())){
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
					  obj.setUserId(userid);
				}
				obj.setState("N");//设置成发布状态
				newsInfoMapper.cancelOrPublishNewsInfo(obj);
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
	/**
	 * 删除新闻信息
	 */
	@Override
	public Result deleteNewsInfo(NewsInfoCustom obj) {
		ResultEntity re = new ResultEntity();
		try {
			if(obj.getId()!=null){
				NewsInfoCustom temp = new NewsInfoCustom();  
				temp.setId(obj.getId());
				temp = newsInfoMapper.queryNewsInfoById(temp);
				if (temp.getPic() != null) {
					deletePicOrAttachmentfile(temp.getPic());
				}
				newsInfoMapper.deleteNewsInfoById(obj);
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
	
	/**
	 * 删除失效图片
	 * @Title: deletePicOrAttachmentfile 
	 * @param ids  
	 * @author: Mr.Zhao
	 */
	public  void deletePicOrAttachmentfile(String ids) {
		   if (!"".equals(ids)){
			   Map<String, String>  map_request=new HashMap<String, String>();
			   map_request.put("attachId",ids);
			  //得到所有的附件列表
    	         List<Map<String, String>> list_file_pic = newsFileService.queryAttrList(map_request);
    	         System.out.println("list_file_pic"+list_file_pic.size());
	             String[] attrs=ids.split(",");
	             System.out.println("attrs.length"+attrs.length+ids);
	             for(int i=0;i<attrs.length;i++) {
	            	
	            	 newsFileService.deleteStateInvalidByAttachId(attrs[i]);
	            	 //清除文件
	            	 if(list_file_pic.size()!=0) {
	            		 CleanDataJob.deleteFile(list_file_pic.get(i).get("physicalPath"));
	            	 }
					 
	            }
	        }
	   }
}
