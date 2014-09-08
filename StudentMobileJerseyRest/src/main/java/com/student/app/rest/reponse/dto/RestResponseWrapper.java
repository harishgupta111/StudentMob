package com.student.app.rest.reponse.dto;

import java.io.Serializable;

import javax.ws.rs.core.Response.Status;

public class RestResponseWrapper<T extends Serializable> implements
		Serializable {

	/**
     * 
     */
	private static final long serialVersionUID = 3753234460328234824L;
	private Status status;
	private T data;

	public RestResponseWrapper() {
	}

	public RestResponseWrapper(Status status, T data, String opCode,
			String nextOpCode) {
		super();
		this.status = status;
		this.data = data;
	}

	public static class Builder<T extends Serializable> {
		private Status status;
		private T data;

		public Builder() {
		}

		public Builder<T> status(Status val) {
			status = val;
			return this;
		}

		public Builder<T> data(T val) {
			data = val;
			return this;
		}

		public RestResponseWrapper<T> build() {
			return new RestResponseWrapper<T>(this);
		}
	}

	private RestResponseWrapper(Builder<T> builder) {
		data = builder.data;
		status = builder.status;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		// result = prime * result + ((data == null) ? 0 : data.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RestResponseWrapper<?> other = (RestResponseWrapper<?>) obj;
		/*
		 * if (data == null) { if (other.data != null) return false; } else if
		 * (!data.equals(other.data)) return false;
		 */
		if (status != other.status)
			return false;
		return true;
	}

}
