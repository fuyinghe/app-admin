package com.hrbwmxx.framework.attachment.vo;

import java.util.List;

import com.hrbwmxx.framework.attachment.modal.AttachmentsView;

public class AttachmentsVo {
	
	private String errcode="0";
	private String errmsg="ok";
	
	private List<AttachmentsView> file;

	public String getErrcode() {
		return errcode;
	}

	public void setErrcode(String errcode) {
		this.errcode = errcode;
	}

	public String getErrmsg() {
		return errmsg;
	}

	public void setErrmsg(String errmsg) {
		this.errmsg = errmsg;
	}

	public List<AttachmentsView> getFile() {
		return file;
	}

	public void setFile(List<AttachmentsView> file) {
		this.file = file;
	}
	
	

}
