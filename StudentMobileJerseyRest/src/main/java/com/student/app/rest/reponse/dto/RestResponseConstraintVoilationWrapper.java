package com.student.app.rest.reponse.dto;

import java.io.Serializable;
import java.util.Set;

import javax.ws.rs.core.Response.Status;

public class RestResponseConstraintVoilationWrapper<T extends Serializable> implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6746343517422361391L;
	private Status status;
	private Set<String> voilationSet;
	
	public static class Builder<T extends Serializable> {
		private Status status;
		private Set<String> voilationSet;

		public Builder() {
		}

		public Builder<T> status(Status val) {
			status = val;
			return this;
		}

		public Builder<T> voilationSet(Set<String> val) {
			voilationSet = val;
			return this;
		}

		public RestResponseConstraintVoilationWrapper<T> build() {
			return new RestResponseConstraintVoilationWrapper<T>(this);
		}
	}

	private RestResponseConstraintVoilationWrapper(Builder<T> builder) {
		voilationSet = builder.voilationSet;
		status = builder.status;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result
				+ ((voilationSet == null) ? 0 : voilationSet.hashCode());
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RestResponseConstraintVoilationWrapper<T> other = (RestResponseConstraintVoilationWrapper<T>) obj;
		if (status != other.status)
			return false;
		if (voilationSet == null) {
			if (other.voilationSet != null)
				return false;
		} else if (!voilationSet.equals(other.voilationSet))
			return false;
		return true;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Set<String> getVoilationSet() {
		return voilationSet;
	}

	public void setVoilationSet(Set<String> voilationSet) {
		this.voilationSet = voilationSet;
	}

}
