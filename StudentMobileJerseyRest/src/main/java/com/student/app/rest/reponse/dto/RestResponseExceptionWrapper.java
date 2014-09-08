package com.student.app.rest.reponse.dto;

import java.io.Serializable;

import javax.ws.rs.core.Response.Status;

import com.fasterxml.jackson.annotation.JsonFilter;

@JsonFilter("theFilter")
public class RestResponseExceptionWrapper<T extends Throwable> implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2506682585932971964L;
	private Status status;
	private T exception;
	private String errorMessage;
	
	public static class Builder<T extends Throwable> {
		private Status status;
		private T exception;
		private String errorMessage;
		
		public Builder() {
		}
		
		public Builder<T> errorMessage(String val) {
			errorMessage = val;
			return this;
		}

		public Builder<T> status(Status val) {
			status = val;
			return this;
		}

		public Builder<T> exception(T val) {
			exception = val;
			return this;
		}

		public RestResponseExceptionWrapper<T> build() {
			return new RestResponseExceptionWrapper<T>(this);
		}
	}

	private RestResponseExceptionWrapper(Builder<T> builder) {
		exception = builder.exception;
		status = builder.status;
		errorMessage = builder.errorMessage;
	}
	
	public T getException() {
		return exception;
	}
	public void setException(T exception) {
		this.exception = exception;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((errorMessage == null) ? 0 : errorMessage.hashCode());
		result = prime * result
				+ ((exception == null) ? 0 : exception.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		return result;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RestResponseExceptionWrapper other = (RestResponseExceptionWrapper) obj;
		if (errorMessage == null) {
			if (other.errorMessage != null)
				return false;
		} else if (!errorMessage.equals(other.errorMessage))
			return false;
		if (exception == null) {
			if (other.exception != null)
				return false;
		} else if (!exception.equals(other.exception))
			return false;
		if (status != other.status)
			return false;
		return true;
	}
	
	
	
}
