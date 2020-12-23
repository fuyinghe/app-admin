package com.hrbwmxx.framework.exception;

public class LackOfPrimaryKeyException extends RuntimeException {
	
	private static final long serialVersionUID = 4080687441438489243L;

	public LackOfPrimaryKeyException() {
		this("More than one rows are updated or deleted. please check the primary key of entity");
	}
	
	public LackOfPrimaryKeyException(String msg) {
		super(msg);
	}
}
