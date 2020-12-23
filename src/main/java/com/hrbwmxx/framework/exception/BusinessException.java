package com.hrbwmxx.framework.exception;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


public class BusinessException extends RuntimeException {

	private static final long serialVersionUID = 8404900224024637251L;
	protected String errorCode = "ERR9999";
	protected String messageToken;
	private Map<String,List<String>> fieldErrors = null;
	private String[] errorMsgArgs = null;
	
	
	
	public BusinessException(String errorCode) {
		super(errorCode);
		this.errorCode = errorCode;
	}

	@SuppressWarnings("unchecked")
	public BusinessException(Map<String,String> fieldErrorMsg) {
		  final Map errors =  GetFieldErrorsMap();
		  for (String key : fieldErrorMsg.keySet()){
			 String value = fieldErrorMsg.get(key);
			 List<String> thisFieldErrors = new ArrayList<String>();
			 thisFieldErrors.add(value);
			 errors.put(key, thisFieldErrors);
		  }
		  fieldErrorMsg = null;
	}
	
	public BusinessException(String fieldName, String errorMessage,
			String messageToken) {
		    super(fieldName);
			this.addFieldError(fieldName, errorMessage);
	}
	
	public BusinessException(String errorMsgKey, String[] errorMsgArgs) {
		super(errorMsgKey);
		this.errorCode = errorMsgKey;
		this.errorMsgArgs = errorMsgArgs;
	}
	
	@SuppressWarnings("unchecked")
	private void addFieldError(String fieldName, String errorMessage) {
        final Map errors = GetFieldErrorsMap();
        List thisFieldErrors = (List) errors.get(fieldName);
        if (thisFieldErrors == null) {
            thisFieldErrors = new ArrayList();
            errors.put(fieldName, thisFieldErrors);
        }
        thisFieldErrors.add(errorMessage);
    }
    
    @SuppressWarnings("unchecked")
	private Map GetFieldErrorsMap() {
        if (fieldErrors == null) {
            fieldErrors = new LinkedHashMap();
        }
        return fieldErrors;
    }
	
	public BusinessException(String errorCode ,String messageToken) {
		super(errorCode);
		this.errorCode = errorCode;
		this.messageToken = messageToken;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public String getMessageToken() {
		return messageToken;
	}

	/**
	 * @return the fieldErrors
	 */
	@SuppressWarnings("unchecked")
	public Map getFieldErrors() {
		return fieldErrors;
	}

	/**
	 * @return the errorMsgParam
	 */
	public String[] getErrorMsgArgs() {
		return errorMsgArgs;
	}

}
