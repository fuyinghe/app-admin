package com.hrbwmxx.framework.attachment.service;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.hrbwmxx.framework.attachment.vo.AttachmentsVo;
import com.hrbwmxx.framework.attachment.vo.UploadFileVo;


public interface IAttachmentService {

	/**
	 * 上传接口
	 * @param response
	 * @param file
	 * @param map_request
	 */
	public UploadFileVo attachmentUpload(HttpServletResponse response,MultipartFile file,@RequestParam Map<String,String>  paramFiledMap);

	/**
	 * 下载接口
	 * @param response
	 * @param paramFiledMap
	 * @throws IOException 
	 */
	public void attachmentDownload(HttpServletResponse response,@RequestParam Map<String, String> paramFiledMap) throws IOException;
	
	/**
	 * 
	 * @param paramFiledMap
	 * @return
	 */
	public Map<String,String> attachmentDelete(Map<String, String> paramFiledMap);

	/**
	 * 获取附件列表
	 * @param paramFiledMap
	 * @return
	 */
	public AttachmentsVo queryAttachments(Map<String, String> paramFiledMap);
	
}
