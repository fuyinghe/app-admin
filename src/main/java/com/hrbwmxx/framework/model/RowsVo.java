package com.hrbwmxx.framework.model;

import java.util.List;
import java.util.Map;

import com.hrbwmxx.framework.model.ResultPage;

public class RowsVo extends ResultPage {

	@Override
	public void setRows(List<?> rows) {
		super.setRows(rows);
	}

	@Override
	public void setTows(Map<?,List> tows){
		super.setTows(tows);
	}
	
}
