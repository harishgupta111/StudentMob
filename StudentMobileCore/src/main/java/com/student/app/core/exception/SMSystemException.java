package com.student.app.core.exception;

public class SMSystemException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7700137622795987861L;

	public SMSystemException() {
		super();
	}

	public SMSystemException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public SMSystemException(String message, Throwable cause) {
		super(message, cause);
	}

	public SMSystemException(String message) {
		super(message);
	}

	public SMSystemException(Throwable cause) {
		super(cause);
	}
	
}
