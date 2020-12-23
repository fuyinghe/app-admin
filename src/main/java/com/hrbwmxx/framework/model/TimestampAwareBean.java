package com.hrbwmxx.framework.model;

import java.math.BigDecimal;
import java.util.Date;

public interface TimestampAwareBean {
	
	public static final String KEY_CREATED_TIMESTAMP = "createdTimestamp";
	public static final String KEY_CREATED_BY = "createdBy";
	public static final String KEY_MODIFIED_TIMESTAMP = "modifiedTimestamp";
	public static final String KEY_MODIFIED_BY= "modifiedBy";
	public static final String KEY_REC_ACTIVE_FLAG = "recActiveFlag";
	
	public Date getCreatedTimestamp();

	public void setCreatedTimestamp(Date createdTimestamp);

	public void setCreatedBy(Integer createdBy);

	public Date getModifiedTimestamp();

	public void setModifiedTimestamp(Date modifiedTimestamp);

	public BigDecimal getModifiedBy();

	public void setModifiedBy(BigDecimal modifiedBy);

	public String getRecActiveFlag();

	public void setRecActiveFlag(String recActiveFlag);
}
