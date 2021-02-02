package com.hrbwmxx.hrbu.news.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrbwmxx.framework.fileUpload.service.NHFileUploadService;
import com.hrbwmxx.framework.job.clean.CleanDataJob;
import com.hrbwmxx.framework.model.Page;
import com.hrbwmxx.framework.model.Result;
import com.hrbwmxx.framework.model.ResultPage;
import com.hrbwmxx.framework.util.ExceptionUtil;
import com.hrbwmxx.hrbu.log.model.Log;
import com.hrbwmxx.hrbu.news.dao.NewsTypeNHMapper;
import com.hrbwmxx.hrbu.news.entity.NewsTypeNH;
import com.hrbwmxx.hrbu.news.service.INewsTypeNHService;
import com.hrbwmxx.hrbu.news.vo.NewsTypeNHCustom;
import com.hrbwmxx.hrbu.news.vo.NewsTypeNHVO;


@Service
public class NewsTypeNHServiceImpl implements INewsTypeNHService{
	
	//新闻类别
	@Autowired
	private NewsTypeNHMapper newsTypeNHMapper;
	// 图片
	@Autowired
	private NHFileUploadService newsFileService;
	// 异常
	@Autowired
    private ExceptionUtil exceptionUtil;
	
	@Override
	public Result queryNewsTypeListPage(Page page, NewsTypeNH obj) {
		ResultPage re = new ResultPage();
		List<NewsTypeNHCustom> typeList = new ArrayList<NewsTypeNHCustom>();
		try {
			typeList = newsTypeNHMapper.queryNewsTypeListPage(page,obj);
			int typeCount = newsTypeNHMapper.queryNewsTypeForCount(page,obj);
			re.setPageNo(page.getPageNo());
			re.setPageSize(page.getPageSize());
			re.setRows(typeList);
			re.setTotalCount(typeCount);
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
	public Result queryNewsTypeList(NewsTypeNH obj) {
		// TODO Auto-generated method stub
		NewsTypeNHVO re = new NewsTypeNHVO();
		List<NewsTypeNHCustom> typeList = new ArrayList<NewsTypeNHCustom>();
		try {
			typeList = newsTypeNHMapper.queryNewsTypeList(obj);
			re.setList(typeList);
		} catch (Exception e) {
			// TODO: handle exception
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
	public Result queryNewsTypeById(NewsTypeNH obj) {
		NewsTypeNHVO re = new NewsTypeNHVO();
		try {
			if (obj.getId() != null && !"".equals(obj.getId())) {
				NewsTypeNHCustom info = newsTypeNHMapper.queryNewsTypeById(obj);
				if (info != null) {
					Map<String, String> map_request = new HashMap<String, String>();
					if (info.getPic() != null) {
						map_request.put("attachId", info.getPic());
						List<Map<String, String>> list_file_pic = newsFileService.queryAttrList(map_request);
						info.setList_file_pic(list_file_pic);
					}
				}else {
					re.setErrcode("500");
					re.setErrmsg("数据已删除");
				}
				re.setNewsTypeNHCustom(info);
			} else {
				re.setErrcode("500");
				re.setErrmsg("操作失败");
			}
		} catch (Exception e) {
			// TODO: handle exception
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
	public Result saveNewsType(NewsTypeNH obj) {
		// TODO Auto-generated method stub
		NewsTypeNHVO re = new NewsTypeNHVO();
		try {
			if (obj != null) {
				//设置主键
				String id = UUID.randomUUID().toString();
				obj.setId(id);
				obj.setState("N");//设置成编辑状态
				//如果添加了图片
				if (obj.getPic()!=null){
					newsFileService.updateFileStateByIds(obj.getPic(), "1");
				}
				newsTypeNHMapper.saveNewsType(obj);
				
			}
		} catch (Exception e) {
			// TODO: handle exception
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
	public Result updateNewsType(NewsTypeNH obj) {
		// TODO Auto-generated method stub
		NewsTypeNHVO re = new NewsTypeNHVO();
		try {
			if (obj!= null && obj.getId()!= null && !"".equals(obj.getId()) ) {
				NewsTypeNHCustom info = new NewsTypeNHCustom();
				info.setId(obj.getId());
				info = newsTypeNHMapper.queryNewsTypeById(info);
				if ("".equals(obj.getPic()) && null == obj.getPic()) {
					System.err.println("没有新图");
					if (!"".equals(info.getPic()) && info.getPic()!= null ) {
						System.err.println("没有原图");
						//删除原图片
						deletePicOrAttachmentfile(info.getPic());
					}
				}else {
					System.err.println("有新图");
					if (info.getPic()!= null && !"".equals(info.getPic())) {
						System.err.println("有原图");
						//不为空,判断下是否与原来的相同
						if(!obj.getPic().equals(info.getPic())) {
							System.err.println("新图和原图不一样");
							//删除原图片
							deletePicOrAttachmentfile(info.getPic());
						}
					}
					//改新图片状态
					newsFileService.updateFileStateByIds(obj.getPic(), "1");
					System.err.println("改新图状态");
				}
				newsTypeNHMapper.updateNewsType(obj);
			}
		} catch (Exception e) {
			// TODO: handle exception
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
	public Result deleteNewsTypeById(NewsTypeNH obj) {
		NewsTypeNHVO re = new NewsTypeNHVO();
		try {
			if (obj.getId() != null && !"".equals(obj.getId())) {
				NewsTypeNHCustom info = newsTypeNHMapper.queryNewsTypeById(obj);
				if (info.getPic() != null) {
					deletePicOrAttachmentfile(info.getPic());
				}
				newsTypeNHMapper.deleteNewsTypeById(obj);
			}else {
				re.setErrcode("500");
				re.setErrmsg("操作失败");
				String classname = new Exception().getStackTrace()[1].getClassName(); //获取调用者的类名
				String method_name = new Exception().getStackTrace()[1].getMethodName(); //获取调用者的方法名
				Log rz=exceptionUtil.buildRZ(re.getErrcode(),re.getErrmsg()+"发生在"+classname+"的"+method_name+"方法上");
				exceptionUtil.addLog(rz);
			}
		} catch (Exception e) {
			// TODO: handle exception
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
	public Result publishNewsType(NewsTypeNH obj) {
		NewsTypeNHVO re = new NewsTypeNHVO();
		try {
			if (obj.getId() != null && !"".equals(obj.getId())) {
				NewsTypeNHCustom type = new NewsTypeNHCustom();
				type.setId(obj.getId());
				type.setState("Y");
				newsTypeNHMapper.cancelOrPublishNewsType(type);
			}
		} catch (Exception e) {
			// TODO: handle exception
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
	public Result cancelPublishNewsType(NewsTypeNH obj) {
		NewsTypeNHVO re = new NewsTypeNHVO();
		try {
			if (obj.getId() != null && !"".equals(obj.getId())) {
				NewsTypeNHCustom type = new NewsTypeNHCustom();
				type.setId(obj.getId());
				type.setState("N");
				newsTypeNHMapper.cancelOrPublishNewsType(type);
			}
		} catch (Exception e) {
			// TODO: handle exception
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
