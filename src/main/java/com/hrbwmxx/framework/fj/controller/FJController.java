package com.hrbwmxx.framework.fj.controller;

import java.awt.Image;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hrbwmxx.framework.fj.service.IFJService;
import com.hrbwmxx.framework.fj.vo.FJCustom;
import com.hrbwmxx.framework.fj.vo.FJVo;
import com.hrbwmxx.framework.model.Result;
import com.hrbwmxx.framework.util.PropertiesUtil;
import com.hrbwmxx.framework.util.TimeUtil;

import common.Logger;
@Controller
@RequestMapping("fj")
public class FJController {
	@SuppressWarnings("unused")
	private static Logger logger=Logger.getLogger( FJController.class );
	@Autowired
	private IFJService  fjService;
	/**
	 * 
	* @MethodName: upload 
	* @description : 文件上传
	* @author：lijingyu
	* @date： 2018年1月15日 上午10:30:30
	* @param response
	* @param file
	* @param obj
	* @throws Exception void
	 */
	@RequestMapping("upload")
	public void upload(HttpServletResponse response, @RequestParam  MultipartFile file, FJCustom obj) throws Exception {
		response.setContentType("text/html;charset=utf-8");
		String  json=fjService.upload(file,obj);
		response.getWriter().write(json);
	}
	/**
	 * 
	* @MethodName: download 
	* @description : 文件下载
	* @author：lijingyu
	* @date： 2018年1月15日 上午10:54:07
	* @param response
	* @param obj
	* @throws Exception void
	 */
	@RequestMapping("download")
	public void  download(HttpServletResponse response,  FJCustom obj) throws  Exception {
		//查询数据
		List<FJCustom> list=fjService.queryFJList(obj);
		obj=list.get(0);
		//获得文件全路径
 		String newName=list.get(0).getWjqlj();
 		//下载文件
		File file = new File(newName);
		if(file.exists()){
				response.setHeader("Content-Disposition", "attachment;filename="+new String(obj.getYmc().getBytes("utf-8"), "iso8859-1"));
				FileInputStream in = new FileInputStream(file);
				byte[] buffer = new byte[1000];
				int len=0;
 				while ((len = in.read(buffer)) > 0){
 					response.getOutputStream().write(buffer,0,len);
 				}
				response.getOutputStream().flush();
		}
	}
	/**
	 * 
	* @MethodName: showImg 
	* @description : 图片预览接口
	* @author：lijingyu
	* @date： 2018年1月15日 下午5:21:22
	* @param response
	* @param obj
	* @throws Exception void
	 */
	@RequestMapping("showImg")
	public void showImg(HttpServletResponse response,  FJCustom obj)throws  Exception {
		//根据文件id查询文件对象
		List<FJCustom> list=fjService.queryFJList(obj);
		obj=list.get(0);
		//根路径+相对路径+文件名字
		response.setContentType("image/jpeg;charset=UTF-8"); 
		response.setHeader("Content-Disposition","inline; filename=\"" + obj.getYmc() + "\"");
		OutputStream out = response.getOutputStream();  
		//2查看数据库已经存在的
		try {
			if(null!=obj){
					// 根据图片地址构造file对象  
				File file = new File(obj.getWjqlj());  
				InputStream is = new FileInputStream(file);  
				Image image = ImageIO.read(is);// 读图片  
				String imageType = obj.getXmc().substring(obj.getXmc().lastIndexOf(".") + 1);  
				RenderedImage img = (RenderedImage) image;  
				ImageIO.write(img, imageType, out);   
				
			}
		} catch (Exception e) {
			logger.warn("FileNotFoundException:"+obj.getWjqlj());
		}  
		out.flush();  
		out.close(); 
	}

	/**
	 * 
	* @MethodName: queryFjList 
	* @description : 查询附件列表
	* @author：lijingyu
	* @date： 2018年1月15日 下午5:21:42
	* @param obj
	* @return
	* @throws JsonProcessingException Result
	 */
	@RequestMapping("queryFjList")
	@ResponseBody
	public Result queryFjList(FJCustom obj) throws JsonProcessingException {
		//查询数据
		List<FJCustom> list=fjService.queryFJList(obj);
		FJVo result =new FJVo();
		result.setRows(list);
		return result;
	}
}
