package com.hrbwmxx.framework.exception;

public class AnnotationException extends RuntimeException {
	
	private static final long serialVersionUID = -2454606284136173418L;

	public AnnotationException(String errorCode) {
		super(errorCode);
	}
}
