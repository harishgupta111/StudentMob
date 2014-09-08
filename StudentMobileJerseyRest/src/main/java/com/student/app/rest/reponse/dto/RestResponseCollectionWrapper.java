package com.student.app.rest.reponse.dto;

import java.io.Serializable;
import java.util.Collection;

import javax.ws.rs.core.Response.Status;

import com.student.app.core.model.SMBaseEntity;

public class RestResponseCollectionWrapper<T extends SMBaseEntity> implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2931713596923449387L;
	private Status status;
	private Collection<T> collection;
	
	public static class Builder<T extends SMBaseEntity> {
		private Status status;
		private Collection<T> collection;

		public Builder() {
		}

		public Builder<T> status(Status val) {
			status = val;
			return this;
		}

		public Builder<T> collection(Collection<T> val) {
			collection = val;
			return this;
		}

		public RestResponseCollectionWrapper<T> build() {
			return new RestResponseCollectionWrapper<T>(this);
		}
	}

	private RestResponseCollectionWrapper(Builder<T> builder) {
		collection = builder.collection;
		status = builder.status;
	}


	public Collection<T> getCollection() {
		return collection;
	}

	public void setCollection(Collection<T> collection) {
		this.collection = collection;
	}


	public Status getStatus() {
		return status;
	}


	public void setStatus(Status status) {
		this.status = status;
	}
	
}
