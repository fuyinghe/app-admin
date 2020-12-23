package com.hrbwmxx.framework.attachment.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.hrbwmxx.framework.attachment.dao.AttachmentMapper;
import com.hrbwmxx.framework.attachment.modal.Attachment;
import com.hrbwmxx.framework.attachment.modal.AttachmentConfig;
import com.hrbwmxx.framework.attachment.modal.AttachmentsView;
import com.hrbwmxx.framework.attachment.service.IAttachmentService;
import com.hrbwmxx.framework.attachment.vo.AttachmentsVo;
import com.hrbwmxx.framework.attachment.vo.UploadFileVo;
import com.webmos.framework.util.PropertiesUtil;
import com.webmos.framework.util.TimeUtil;

@Service("UploadServiceImpl")
public class AttachmentServiceImpl implements IAttachmentService {

	@Autowired
	private AttachmentMapper attachmentMapper;
	
	public UploadFileVo attachmentUpload(HttpServletResponse response,MultipartFile file, Map<String, String> paramFiledMap) {
		UploadFileVo resultVo = new UploadFileVo();
		
		//参数上传类型获取
		String attachType = paramFiledMap.get("attachType");
		String ownerId = paramFiledMap.get("ownerId");
		String attachId = java.util.UUID.randomUUID().toString();
		
		//判断上传文件参数是否合格
		if("".equals(attachType)){
			resultVo.setErrcode("500");
			resultVo.setErrmsg("上传操作失败，参数非法");
			return resultVo;
		}
		
		//获取上传类型配置信息(包括文件大小/类型/存储路径等信息)
		AttachmentConfig ac = getAttaConfig(attachType);
		if(ac ==null){
			resultVo.setErrcode("500");
			resultVo.setErrmsg("配置参数不正确");
			return resultVo;
		}
		
		//上传文件无效
		if(file.getSize()<=0){
			resultVo.setErrcode("500");
			resultVo.setErrmsg("上传文件无效");
			return resultVo;
  	    }
		
		//判断文件是否超过指定大小
		long MAX_SIZE = (ac.getMAXFILESIZE() * 1024 * 1024);//20MB
		if(file.getSize()>MAX_SIZE) {
			resultVo.setErrcode("500");
			resultVo.setErrmsg("上传文件过大,应小于"+ac.getMAXFILESIZE()+"M");
			return resultVo;
		}
		
		//判断文件扩展类型是否合法
		String ALLOWEXT = ac.getALLOWEXT();
		String originalName = file.getOriginalFilename();
		String suffix = originalName.substring(originalName.lastIndexOf("."));
			   suffix = suffix.replaceAll(".", "");
		if( ALLOWEXT.indexOf(suffix)<0 && !ALLOWEXT.equals("*")){
			resultVo.setErrcode("500");
			resultVo.setErrmsg("上传文件不合法，上传支持【"+ALLOWEXT+"】文件类型");
			return resultVo;
		}
		
		//上传文件
		Map<String, String> uploadmap = null;
		try {
			uploadmap = transferToDir( ac, file );
		} catch (Exception e) {
			e.printStackTrace();
			resultVo.setErrcode("500");
			resultVo.setErrmsg("上传文件报错");
			return resultVo;
		}
		
		//检查文件是否上传成功，如果成功map应该有相应值
		if(uploadmap==null || uploadmap.isEmpty()) {
			resultVo.setErrcode("500");
			resultVo.setErrmsg("上传文件报错");
			return resultVo;
		}
		
		
		//封装存储实体对象
		Attachment attachment = new Attachment();
		attachment.setID(attachId);
		attachment.setOWNERID(ownerId);
		attachment.setSAVENAME(uploadmap.get("physicalPath"));
		attachment.setFILENAME(uploadmap.get("originalName"));
		attachment.setCREATOR("admin");
		attachment.setCONTENTTYPE(uploadmap.get("contentType"));
		attachment.setATTACHTYPE(attachType);
		attachment.setCREATETIME(TimeUtil.getTime());
		attachment.setFileSize(uploadmap.get("size"));

		//将上传数据保存至文件表中
		int insertStatus = transferToDb(attachment);
		if(insertStatus<=0) {
			resultVo.setErrcode("500");
			resultVo.setErrmsg("上传文件保存文件信息时报错");
			return resultVo;
		}else {
			String basePath="";
			try {
				Properties properties = PropertiesUtil.getKey("system.properties");
				basePath=properties.getProperty("basePath");
			} catch (IOException e) {
				e.printStackTrace();
			}
			resultVo.setErrcode("0");
			resultVo.setErrmsg("上传成功");
			resultVo.setAttachId(attachId);
			resultVo.setDownloadUrl(basePath+"/Attachment/attachmentDownload.do?attachId="+attachId);
			resultVo.setUrl(basePath+"/Attachment/attachmentDownload.do?attachId="+attachId);
			resultVo.setOriginalName(uploadmap.get("originalName"));
			resultVo.setName(uploadmap.get("FileName"));
			resultVo.setType(uploadmap.get("suffix"));
			resultVo.setState("SUCCESS");
			resultVo.setSize(uploadmap.get("size"));
			return resultVo;
		}
	}
	
	/**
	 * 获取附件配置
	 * @param attachType
	 * @return
	 */
	private AttachmentConfig getAttaConfig(String attachType){
		AttachmentConfig ac = null;
		Map<String,String> configmap= attachmentMapper.queryAttachmentConfig(attachType);
		if(!configmap.isEmpty()){
			ac = new AttachmentConfig();
			ac.setATTACH_KEY(configmap.get("ATTACH_KEY"));
			ac.setSAVEPATH(configmap.get("SAVEPATH"));
			ac.setMAXFILESIZE(Integer.valueOf(configmap.get("MAXFILESIZE")));
			ac.setALLOWEXT(configmap.get("ALLOWEXT"));
		}
		return ac;
	}
	
	/**
	 * 
	* @MethodName: transferToDir 
	* @description : 文件上传
	* @author：lijingyu 
	* @date： 2018年3月16日 下午2:51:33
	* @param file
	* @param map_request
	* @return Map<String,String>
	 * @throws IOException 
	 * @throws IllegalStateException 
	 */
	private Map<String, String> transferToDir(AttachmentConfig ac,MultipartFile file) throws  Exception {
		/**定义参数*/
		String rootDir="";
		String separator ="";
		String relativePath="";
		String suffix="";
		String newFileName="";
		String oriName ="";
		Properties properties=null;
		File fileDir =null;
		File newFile=null;
		
		//1.1获得文件根目录配置
		properties=PropertiesUtil.getKey("system.properties");
		rootDir=properties.getProperty("baseDir");
		oriName= file.getOriginalFilename();
		//1.2获得文件分割符
		separator =PropertiesUtil.SEPARATOR;
		//1.3创建相对路径
		relativePath= TimeUtil.getDay();
		fileDir = new File(rootDir +separator+ac.getSAVEPATH() +separator +relativePath);
		if(!fileDir.exists()){
			fileDir.mkdirs();
		}
		
		//1.3获得新文件名字
		suffix = oriName.substring(oriName.lastIndexOf("."));
		newFileName = System.nanoTime() + suffix;
		//spring框架创建新文件
		newFile = new File(fileDir + separator + newFileName);
		newFile.createNewFile();
		file.transferTo(newFile);
		
		/**返回数据*/
		HashMap<String, String> uploadInfo = new HashMap<String, String>();
		uploadInfo.put("physicalPath",ac.getSAVEPATH() +separator +relativePath+separator+newFileName);
		uploadInfo.put("originalName",oriName);//原始文件名字
		uploadInfo.put("FileName",newFileName);//时间戳名字
		uploadInfo.put("suffix",suffix);//扩展名
		uploadInfo.put("size",file.getSize()+"");//文件大小
		uploadInfo.put("contentType",file.getContentType());//文件类型
		return uploadInfo;
	}
	
	/**
	 * 将上传文件信息保存至数据库中
	 * @param attachment
	 * @return
	 */
	private int transferToDb(Attachment attachment) {
		//将上传信息存于库中
		return attachmentMapper.insertAttachment(attachment);
	}
	
	/**
	 * 获得附件信息
	 * @param attachmentId
	 * @return
	 */
	private Attachment getAttachment(String attachmentId){
		
		Attachment attachment = null;

		attachment = attachmentMapper.getAttachment(attachmentId);
		
		return attachment;
	}

	/**
	 * 下载文件
	 */
	public void attachmentDownload(HttpServletResponse response, Map<String, String> paramFiledMap) throws IOException {

		response.setContentType("text/html ;charset=UTF-8");
		response.setCharacterEncoding("UTF-8"); // 设置字符编码为 UTF-8, 这样支持汉字显示
		PrintWriter out = response.getWriter();

		String attachId = paramFiledMap.get("attachId");
		//判断上传文件参数是否合格
		if("".equals(attachId)){
			out.print("\"errcode\":\"301\",\"errmsg\":\"下载文件失败,参数不完整\"}");
			return ;
		}
		
		Attachment attachment = getAttachment(attachId);
		if(attachment==null) {
			out.print("\"errcode\":\"301\",\"errmsg\":\"下载文件失败,文件不存在或已删除\"}");
		}else {
			String savename = attachment.getSAVENAME();//文件路径
			String filename = attachment.getFILENAME();
			String contentype = attachment.getCONTENTTYPE();
			
			//设置文件头信息
			response.reset();//清空respones中的缓存数据
			response.setContentType(contentype);
			response.setHeader("Content-Disposition", "attachment; filename=" +  new String(filename.getBytes(), "iso-8859-1") );
			try {
				Properties properties = PropertiesUtil.getKey("system.properties");
				String rootDir=properties.getProperty("baseDir");
				File file = new File(rootDir +"/"+ savename);
				System.out.println(rootDir + savename);
				if (file.exists()) {
					int fileLength = (int) file.length();
					response.setContentLength(fileLength);
					/*如果文件长度大于0*/
					if (fileLength != 0) {
						/*创建输入流*/
						InputStream inputStream = new FileInputStream(file);
						/*创建输出流*/
						OutputStream outputStream = response.getOutputStream();
						byte[] buffer = new byte[1024];
						int i = -1;
						while ((i = inputStream.read(buffer)) != -1) 
						{
							outputStream.write(buffer, 0, i);
						}
						outputStream.flush();
						outputStream.close();
						inputStream.close();
					}else {
						//下载发生错误的时候，在网页上显错误信息
						out.print("\"errcode\":\"500\",\"errmsg\":\"文件不存在\"}");
					}
				}else{	  
					//下载发生错误的时候，在网页上显错误信息
					out.print("\"errcode\":\"500\",\"errmsg\":\"文件不存在\"}");
				} 
			}catch(FileNotFoundException e1){
				e1.printStackTrace();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}

	public Map<String, String> attachmentDelete(Map<String, String> paramFiledMap) {
		
		HashMap<String, String> resultMap = new HashMap<String,String>();
		resultMap.put("errcode", "0");
		resultMap.put("errmsg", "文件删除成功");
		String attachId = paramFiledMap.get("attachId");
		//判断上传文件参数是否合格
		if("".equals(attachId)){
			resultMap.put("errcode", "301");
			resultMap.put("errmsg", "删除文件失败,参数不完整");
			return resultMap;
		}
		
		Attachment attachment = getAttachment(attachId);
		if(attachment==null) {
			resultMap.put("errcode", "301");
			resultMap.put("errmsg", "删除文件失败,文件不存在或已删除");
			return resultMap;
		}else {
			///**
			int delStatus = attachmentMapper.deleteAttachment(attachId);
			if(delStatus==1) {
				resultMap.put("errcode", "0");
				resultMap.put("errmsg", "文件成功删除");
				return resultMap;
			}else {
				resultMap.put("errcode", "500");
				resultMap.put("errmsg", "文件删除失败");
				return resultMap;
			}
			//**/
			//resultMap.put("errcode", "301");
			//resultMap.put("errmsg", "系统暂无身份校验,暂不进行删除");
			//return resultMap;
		}
	}

	public AttachmentsVo queryAttachments(Map<String, String> paramFiledMap) {
		AttachmentsVo vo = new AttachmentsVo();
		
		String ownerId = paramFiledMap.get("ownerId");
		//判断上传文件参数是否合格
		if("".equals(ownerId)){
			vo.setErrcode("301");
			vo.setErrmsg("获取失败,参数不合法");
			return vo;
		}
		
		String basePath="";
		try {
			Properties properties = PropertiesUtil.getKey("system.properties");
			basePath=properties.getProperty("basePath");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		List<Map<String,String>> attachmentsMaps= attachmentMapper.queryAttachments(ownerId);
		List<AttachmentsView> attachmentsViews = new ArrayList<AttachmentsView>();
		for(int i=0 ;i <attachmentsMaps.size();i++) {
			Map<String, String> map = attachmentsMaps.get(i);
			String filedownpath = basePath+"/Attachment/attachmentDownload.do?attachId="+map.get("ID");
			AttachmentsView av = new AttachmentsView();
			av.setAttachId(map.get("ID"));
			av.setDownloadUrl(filedownpath);
			av.setViewUrl(filedownpath);
			av.setName(map.get("FILENAME"));
			av.setUrl(filedownpath);
			av.setOriginalName(map.get("FILENAME"));
			
			av.setFileSize(map.get("FILESIZE"));
			av.setLen(map.get("FILESIZE"));
			av.setType(map.get("CONTENTTYPE"));
			av.setCreateTime(map.get("CREATETIME"));
			attachmentsViews.add(av);
		}
		vo.setFile(attachmentsViews);
		
		return vo;
	}
	
	
	
	

}
