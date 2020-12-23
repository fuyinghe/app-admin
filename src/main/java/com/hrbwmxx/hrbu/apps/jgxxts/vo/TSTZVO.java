package com.hrbwmxx.hrbu.apps.jgxxts.vo;

import java.util.List;

import com.hrbwmxx.framework.model.ResultEntity;

public class TSTZVO extends ResultEntity {
	//返回数组list
	private List<TSTZCustom> rows;
	
	public List<TSTZCustom> getRows() {
		return rows;
	}

	public void setRows(List<TSTZCustom> rows) {
		this.rows = rows;
	}		
}
