package com.hrbwmxx.framework.attachment.dao;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;

import com.hrbwmxx.framework.attachment.modal.Attachment;

public interface AttachmentMapper {
	
	/**
	 * 查询上传文件配置信息
	 * @param attachId
	 * @return
	 */
	public Map<String,String> queryAttachmentConfig(@Param("attachId") String attachId);
	
	/**
	 *保存附件文件上传信息
	 */
	public int insertAttachment(@Param("attachment") Attachment attachment);

	/**
	 * 获取附件文件信息
	 * @param attachmentId
	 * @return
	 */
	public Attachment getAttachment(@Param("attachmentId") String attachmentId);

	/**
	 * 删除附件信息
	 * @param attachId
	 * @return
	 */
	public int deleteAttachment(@Param("attachmentId") String attachmentId);

	/**
	 * 获取附件信息列表
	 * @param ownerId
	 * @return
	 */
	public List<Map<String,String>> queryAttachments(@Param("ownerId") String ownerId);
	
	
}
