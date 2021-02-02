package com.hrbwmxx.hrbu.news.vo;

import java.util.List;
import java.util.Map;

import com.hrbwmxx.hrbu.news.entity.NewsTypeNH;

public class NewsTypeNHCustom extends NewsTypeNH{

	private List<Map<String, String>> list_file_pic;//

	public List<Map<String, String>> getList_file_pic() {
		return list_file_pic;
	}

	public void setList_file_pic(List<Map<String, String>> list_file_pic) {
		this.list_file_pic = list_file_pic;
	}


	
	
}
