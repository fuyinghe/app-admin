package com.hrbwmxx.hrbu.apps.tsgxx.vo;

import java.util.List;

import com.hrbwmxx.framework.model.ResultEntity;

public class TSGXXVO  extends ResultEntity{
	private List<TSGXXCustom> rows;

	public List<TSGXXCustom> getRows() {
		return rows;
	}

	public void setRows(List<TSGXXCustom> rows) {
		this.rows = rows;
	}
}
