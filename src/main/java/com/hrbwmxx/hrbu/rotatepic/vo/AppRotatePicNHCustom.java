package com.hrbwmxx.hrbu.rotatepic.vo;

import java.util.List;
import java.util.Map;

import com.hrbwmxx.hrbu.rotatepic.model.AppRotatePicNH;

public class AppRotatePicNHCustom extends AppRotatePicNH{

	private String openName;
	private String cityName;
	private List<Map<String, String>> list_file_pic;

	
	public String getOpenName() {
		return openName;
	}

	public void setOpenName(String openName) {
		this.openName = openName;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public List<Map<String, String>> getList_file_pic() {
		return list_file_pic;
	}

	public void setList_file_pic(List<Map<String, String>> list_file_pic) {
		this.list_file_pic = list_file_pic;
	}
}
