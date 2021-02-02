package com.hrbwmxx.hrbu.rotatepic.vo;

import java.util.List;

import com.hrbwmxx.framework.model.ResultPage;

public class AppRotatePicNHVo extends ResultPage{

	private AppRotatePicNHCustom custom;
	private List<AppRotatePicNHCustom> customList;
	public AppRotatePicNHCustom getCustom() {
		return custom;
	}
	public void setCustom(AppRotatePicNHCustom custom) {
		this.custom = custom;
	}
	public List<AppRotatePicNHCustom> getCustomList() {
		return customList;
	}
	public void setCustomList(List<AppRotatePicNHCustom> customList) {
		this.customList = customList;
	}

	
	
}
