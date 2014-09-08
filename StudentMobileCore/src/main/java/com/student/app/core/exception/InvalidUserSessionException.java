package com.student.app.core.exception;

public class InvalidUserSessionException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 477368487543575373L;

	public InvalidUserSessionException() {
	}

	public InvalidUserSessionException(String message) {
		super(message);
	}

	public InvalidUserSessionException(Throwable cause) {
		super(cause);
	}

	public InvalidUserSessionException(String message, Throwable cause) {
		super(message, cause);
	}



}
