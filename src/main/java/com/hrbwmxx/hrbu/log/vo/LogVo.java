package com.hrbwmxx.hrbu.log.vo;

import com.hrbwmxx.hrbu.log.model.Log;

public class LogVo extends Log {
	private String beginTime;//开始日期
	private String endTime;//结束日期
	private String date;
	
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getBeginTime() {
		return beginTime;
	}
	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
}
