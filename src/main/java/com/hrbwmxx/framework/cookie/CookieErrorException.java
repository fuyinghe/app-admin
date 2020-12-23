package com.hrbwmxx.framework.cookie;

public class CookieErrorException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4079086446889141719L;
	
	public CookieErrorException(String errCode) {
		super(errCode);
	}

}
