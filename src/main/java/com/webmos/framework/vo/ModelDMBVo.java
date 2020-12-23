package com.webmos.framework.vo;

import java.util.List;

import com.webmos.framework.model.ModelDMB;
import com.webmos.framework.model.ResultPage;

public class ModelDMBVo extends ResultPage{
	
	private List<ModelDMB> list;

	public List<ModelDMB> getList() {
		return list;
	}

	public void setList(List<ModelDMB> list) {
		this.list = list;
	}

	
}
