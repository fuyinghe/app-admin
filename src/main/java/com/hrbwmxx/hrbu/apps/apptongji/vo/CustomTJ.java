package com.hrbwmxx.hrbu.apps.apptongji.vo;

import com.hrbwmxx.framework.model.ResultEntity;

public class CustomTJ extends ResultEntity {
	
	private String year;//年份
	private String citycode;//城市代码

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getCitycode() {
		return citycode;
	}

	public void setCitycode(String citycode) {
		this.citycode = citycode;
	}


}
