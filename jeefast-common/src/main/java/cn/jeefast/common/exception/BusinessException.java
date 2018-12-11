/*
 * www.jinvovo.com Inc.
 * Copyright (c) 2017 All Rights Reserved
 */
package cn.jeefast.common.exception;

/**
 * 业务自定义异常
 */
public class BusinessException extends RuntimeException {

	private static final long serialVersionUID = 1399971186994404117L;
	
	private boolean ignore = false;

	private String code;
	
	public BusinessException() {
		super();
	}
	
	public BusinessException(String message) {
		super(message);
	}

	public BusinessException(String message, String code) {
		super(message);
		this.code = code;
	}

	
	public BusinessException(String message, Throwable cause) {
		super(message, cause);
	}

	public BusinessException(String message, Throwable cause, String code) {
		super(message, cause);
		this.code = code;
	}
	
	public BusinessException(Throwable cause) {
		super(cause);
	}

	public BusinessException(Throwable cause, String code) {
		super(cause);
		this.code = code;
	}
	
	public boolean isIgnore() {
		return ignore;
	}
	
	public void setIgnore(boolean ignore) {
		this.ignore = ignore;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
}
