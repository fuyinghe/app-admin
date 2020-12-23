package com.hrbwmxx.framework.model;

import java.math.BigDecimal;
import java.util.Date;

import com.hrbwmxx.framework.dao.annotations.Column;

public abstract class TimestampSupportBean extends BasicEntity implements TimestampAwareBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5196894799891036024L;

	@Column(mappedName = "createdTimestamp")
	private Date createdTimestamp;

	@Column(mappedName = "createdBy")
	private Integer createdBy;

	@Column(mappedName = "modifiedTimestamp")
	private Date modifiedTimestamp;
	
	@Column(mappedName = "modifiedBy")
	private BigDecimal modifiedBy;
	
	@Column(mappedName = "recActiveFlag")
	private String recActiveFlag = "Y";

	private String code;
	private String message;
	
	
	public Date getCreatedTimestamp() {
		return createdTimestamp;
	}
	public void setCreatedTimestamp(Date createdTimestamp) {
		this.createdTimestamp = createdTimestamp;
	}
	public Integer getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}
	public Date getModifiedTimestamp() {
		return modifiedTimestamp;
	}
	public void setModifiedTimestamp(Date modifiedTimestamp) {
		this.modifiedTimestamp = modifiedTimestamp;
	}

	public BigDecimal getModifiedBy() {
		return modifiedBy;
	}
	public void setModifiedBy(BigDecimal modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
	public String getRecActiveFlag() {
		return recActiveFlag;
	}
	public void setRecActiveFlag(String recActiveFlag) {
		this.recActiveFlag = recActiveFlag;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
}