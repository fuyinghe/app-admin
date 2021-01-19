package com.hrbwmxx.hrbu.rotatepic.vo;

import java.util.List;

import com.hrbwmxx.framework.model.ResultPage;

public class AppRotatePicVo extends ResultPage{

	private AppRotatePicCustom custom;
	private List<AppRotatePicCustom> customList;

	public AppRotatePicCustom getCustom() {
		return custom;
	}

	public void setCustom(AppRotatePicCustom custom) {
		this.custom = custom;
	}

	public List<AppRotatePicCustom> getCustomList() {
		return customList;
	}

	public void setCustomList(List<AppRotatePicCustom> customList) {
		this.customList = customList;
	}
	
}
