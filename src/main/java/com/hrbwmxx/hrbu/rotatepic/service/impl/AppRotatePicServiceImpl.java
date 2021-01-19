package com.hrbwmxx.hrbu.rotatepic.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrbwmxx.framework.fileUpload.service.INewsFileUploadService;
import com.hrbwmxx.framework.job.clean.CleanDataJob;
import com.hrbwmxx.framework.model.Page;
import com.hrbwmxx.framework.model.Result;
import com.hrbwmxx.hrbu.rotatepic.dao.AppRotatePicMapper;
import com.hrbwmxx.hrbu.rotatepic.service.IAppRotatePicService;
import com.hrbwmxx.hrbu.rotatepic.vo.AppRotatePicCustom;
import com.hrbwmxx.hrbu.rotatepic.vo.AppRotatePicVo;

@Service
public class AppRotatePicServiceImpl implements IAppRotatePicService{

	@Autowired
	private AppRotatePicMapper picMapper;
	
	@Autowired
	private INewsFileUploadService newsFileService;
	
	@Override
	public Result queryRotatePicListPage(Page page, AppRotatePicCustom obj) {
		AppRotatePicVo re = new AppRotatePicVo();
		List<AppRotatePicCustom> typeList = new ArrayList<AppRotatePicCustom>();
		try {
			typeList = picMapper.queryRotatePicListPage(page,obj);
			int typeCount = picMapper.queryRotatePicForCount(page,obj);
			re.setPageNo(page.getPageNo());
			re.setPageSize(page.getPageSize());
			re.setRows(typeList);
			re.setTotalCount(typeCount);
		} catch (Exception e) {
			e.printStackTrace();
			re.setErrcode("500");
			re.setErrmsg("操作失败");
		}
		return re;
	}

	@Override
	public Result queryRotatePicList(AppRotatePicCustom obj) {
		AppRotatePicVo re = new AppRotatePicVo();
		List<AppRotatePicCustom> customList = new ArrayList<AppRotatePicCustom>();
		try {
			customList = picMapper.queryRotatePicList(obj);
			re.setCustomList(customList);
		} catch (Exception e) {
			e.printStackTrace();
			re.setErrcode("500");
			re.setErrmsg("操作失败");
		}
		return re;
	}

	@Override
	public Result queryRotatePicById(AppRotatePicCustom obj) {
		AppRotatePicVo re = new AppRotatePicVo();
		try {
			if (obj.getId()!= null && !"".equals(obj.getId())) {
				AppRotatePicCustom  info = picMapper.queryRotatePicById(obj);
				if (info != null) {
					Map<String, String> map_request = new HashMap<String, String>();
					if (info.getAttachId() != null) {
						map_request.put("attachId", info.getAttachId());
						List<Map<String, String>> list_file_pic = newsFileService.queryAttrList(map_request);
						info.setList_file_pic(list_file_pic);
					}
				}else {
					re.setErrcode("500");
					re.setErrmsg("数据已删除");
				}
				re.setCustom(info);
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

	@Override
	public Result saveRotatePic(AppRotatePicCustom obj) {
		AppRotatePicVo re = new AppRotatePicVo();
		try {
		if (obj!= null) {
			//设置主键
			String id = UUID.randomUUID().toString();
			obj.setId(id);
			obj.setState("0");//设置成编辑状态
			//如果添加了图片
			if (obj.getAttachId()!=null && !"".equals(obj.getAttachId())){
				newsFileService.updateFileStateByIds(obj.getAttachId(), "1");
			}
			picMapper.saveRotatePic(obj);
		  }
			
		} catch (Exception e) {
			e.printStackTrace();
			re.setErrcode("500");
			re.setErrmsg("操作失败");
		}
		return re;
	}

	@Override
	public Result updateRotatePic(AppRotatePicCustom obj) {
		AppRotatePicVo re = new AppRotatePicVo();
		try {
			//1.判断下传来的值
			if (obj!= null && obj.getId()!= null && !"".equals(obj.getId()) ) {
				//2.根据传来的查下原来的内容
				AppRotatePicCustom info = new AppRotatePicCustom();
				info.setId(obj.getId());
				info = picMapper.queryRotatePicById(info);
				//3.判断传来的值里,图片是否为空,再看下原图片是否为空 如何不为空,说明删除图片了
				if ("".equals(obj.getAttachId()) && null == obj.getAttachId()) {
					if (!"".equals(info.getAttachId()) && info.getAttachId()!= null ) {
						//删除原图片
						deletePicOrAttachmentfile(info.getAttachId());
					}
				}else {
					//4.传的图片不为空,原图不为空,就比较下,如果不同就改下
					if (info.getAttachId()!= null && !"".equals(info.getAttachId())) {
						if(!obj.getAttachId().equals(info.getAttachId())) {
							//删除原图片
							deletePicOrAttachmentfile(info.getAttachId());
							//改新图片状态
							newsFileService.updateFileStateByIds(obj.getAttachId(), "1");
						}
					}
				}
				picMapper.updateRotatePic(obj);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			re.setErrcode("500");
			re.setErrmsg("操作失败");
		}
		return re;
	}

	@Override
	public Result delRotatePic(AppRotatePicCustom obj) {
		AppRotatePicVo re = new AppRotatePicVo();
		try {
			if (obj.getId()!= null && !"".equals(obj.getId())) {
				AppRotatePicCustom pic = new AppRotatePicCustom();
				pic.setId(obj.getId());
				pic = picMapper.queryRotatePicById(pic);
				if (pic.getAttachId()!= null && !"".equals(pic.getAttachId())) {
					deletePicOrAttachmentfile(pic.getAttachId());
				}
				picMapper.delRotatePic(pic);
			}else {
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

	@Override
	public Result cancelRotatePic(AppRotatePicCustom obj) {
		AppRotatePicVo re = new AppRotatePicVo();
		try {
			if (obj.getId()!= null && !"".equals(obj.getId())) {
				AppRotatePicCustom info = new AppRotatePicCustom();
				info.setId(obj.getId());
				info.setState("0");
				picMapper.updateRotatePic(info);
			}else {
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

	@Override
	public Result publishRotatePic(AppRotatePicCustom obj) {
		AppRotatePicVo re = new AppRotatePicVo();
		try {
			if (obj.getId()!= null && !"".equals(obj.getId())) {
				AppRotatePicCustom info = new AppRotatePicCustom();
				info.setId(obj.getId());
				info.setState("1");
				picMapper.updateRotatePic(info);
			}else {
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
