package com.webmos.framework.service;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

public interface IFileService {
	/**
	 * 
	* @MethodName: upload 
	* @description : 文件上上传
	* @author：lijingyu 
	* @date： 2018年3月16日 下午2:18:48
	* @param file  文件
	* @param map_request 传入参数
	* @param modid  模型ID
	* @return String
	 */
	String upload(MultipartFile file, Map<String, String> map_request, String modid)throws Exception;
	/**
	 * 
	* @MethodName: queryFileInlfoList 
	* @description : 查询文件列表
	* @author：lijingyu 
	* @date： 2018年3月16日 下午5:09:35
	* @param map_request
	* @param modid
	* @return List<Map<String,String>>
	 */
	List<Map<String, String>> queryAttrList(Map<String, String> map_request, String modid);

}
