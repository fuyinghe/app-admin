package com.hrbwmxx.system.vo;

import com.hrbwmxx.system.model.GN;

public class GNCustom extends GN{

	private static final long serialVersionUID = -8081371636054537664L;
	
	private String jsId;
	
	private String userId;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getJsId() {
		return jsId;
	}

	public void setJsId(String jsId) {
		this.jsId = jsId;
	}
	
	
}
