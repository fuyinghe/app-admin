package com.hrbwmxx.framework.exception;

import java.util.Map;

public class DataSecurityException extends RuntimeException {

	private static final long serialVersionUID = 2609479090847446623L;
	
	private Map<String, Object> data;
	
	public DataSecurityException() {
		super();
	}

	public DataSecurityException(Map<String, Object> data) {
		super();
		this.data = data;
	}

	public DataSecurityException(String message) {
		super(message);
	}
	
	public String toMessage() {
		StringBuffer sb = new StringBuffer("Data access illegally.");
		
		if (data != null) {
			sb.append("[");
			for (String key : data.keySet()) {
				if (data.get(key) != null) {
					sb.append("{");
					sb.append(key);
					sb.append(":");
					sb.append(data.get(key));
					sb.append("},");
				}
			}
			sb.deleteCharAt(sb.length()-1); // remove the last char ","
			sb.append("]");
		}
		
		return sb.toString();
	}

}
