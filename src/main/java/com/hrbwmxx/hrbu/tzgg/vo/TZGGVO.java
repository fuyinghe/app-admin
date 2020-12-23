package com.hrbwmxx.hrbu.tzgg.vo;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.hrbwmxx.framework.model.ResultEntity;

public class TZGGVO extends ResultEntity{
	private TZGGCustom tzggCustom;
	private String id;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public TZGGCustom getTzggCustom() {
		return tzggCustom;
	}
	public void setTzggCustom(TZGGCustom tzggCustom) {
		this.tzggCustom = tzggCustom;
	}
	
	 
 
}
