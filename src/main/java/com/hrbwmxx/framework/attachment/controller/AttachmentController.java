package com.hrbwmxx.framework.attachment.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.hrbwmxx.framework.attachment.modal.UploadFile;
import com.hrbwmxx.framework.attachment.service.IAttachmentService;
import com.hrbwmxx.framework.attachment.vo.AttachmentsVo;
import com.hrbwmxx.framework.attachment.vo.UploadFileVo;
import com.webmos.framework.model.ResultEntity;


@Controller
@RequestMapping("Attachment")
public class AttachmentController {
	
	@Qualifier("UploadServiceImpl")
	@Autowired
	private IAttachmentService attachmentService;
	
	/**
	 * 上传文件
	 * @param file
	 * @param paramFiledMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("attachmentUpload")
	@ResponseBody
	public UploadFileVo attachmentUpload(HttpServletResponse response,@RequestParam ("upfile") MultipartFile file,@RequestParam Map<String,String>  paramFiledMap) throws Exception {
		
		response.setContentType("text/html;charset=utf-8");
		//设置文件保存路径
		UploadFileVo  uploadVo=attachmentService.attachmentUpload(response,file,paramFiledMap);
		return uploadVo;
	}
	
	/**
	 * 下载文件
	 * @param response
	 * @param paramFiledMap
	 * @throws IOException
	 */
	@RequestMapping("attachmentDownload")
	public void attachmentDownload(HttpServletResponse response,  @RequestParam Map<String,String>  paramFiledMap) throws IOException {
		attachmentService.attachmentDownload(response,paramFiledMap);
	}
	
	/**
	 * 删除文件
	 * @param paramFiledMap
	 * @return
	 */
	@RequestMapping("attachmentDelete")
	@ResponseBody
	public Map<String,String> attachmentDelete(@RequestParam Map<String,String>  paramFiledMap) {
		return attachmentService.attachmentDelete(paramFiledMap);
	}
	
	/**
	 * 获取附件列表
	 * @param paramFiledMap
	 * @return
	 */
	@RequestMapping("attachments")
	@ResponseBody
	public AttachmentsVo queryAttachments(@RequestParam Map<String,String>  paramFiledMap) {
		return attachmentService.queryAttachments(paramFiledMap);
	}
	
}
