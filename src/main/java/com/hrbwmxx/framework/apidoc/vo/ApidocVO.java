package com.hrbwmxx.framework.apidoc.vo;

import java.math.BigDecimal;

import com.hrbwmxx.framework.apidoc.model.Apidoc;
import com.hrbwmxx.framework.model.ResultEntity;

public class ApidocVO extends ResultEntity{
	private Apidoc apidoc;
	
	private BigDecimal id;
	

	public BigDecimal getId() {
		return id;
	}

	public void setId(BigDecimal id) {
		this.id = id;
	}

	public Apidoc getApidoc() {
		return apidoc;
	}

	public void setApidoc(Apidoc apidoc) {
		this.apidoc = apidoc;
	}
}
