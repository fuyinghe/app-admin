package com.hrbwmxx.system.vo;

import com.hrbwmxx.framework.model.ResultPage;

public class AppManageVo extends ResultPage{
	
	private AppManageCustom custom ;

	public AppManageCustom getCustom() {
		return custom;
	}

	public void setCustom(AppManageCustom custom) {
		this.custom = custom;
	}
}
