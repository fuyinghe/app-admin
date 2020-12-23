package com.hrbwmxx.framework.fileUpload.vo;

import java.util.List;
import java.util.Map;

import com.hrbwmxx.framework.model.ResultEntity;

public class FileVo extends ResultEntity{
	private List<Map<String, String>> upfile;

	public List<Map<String, String>> getUpfile() {
		return upfile;
	}

	public void setUpfile(List<Map<String, String>> upfile) {
		this.upfile = upfile;
	}

	

	

	 
}
