package com.student.app.core.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

@MappedSuperclass
public class SMBaseEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 246317689363437681L;
	
	
	
	public SMBaseEntity() {
		super();
		
		if(this.createdTS == null)
			this.createdTS = new Date();
		
		if(this.updatedTS == null)
			this.updatedTS = new Date();

	}


	@JsonIgnore
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "createdTS", nullable = false)
	private Date createdTS;
	
	@JsonIgnore
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updatedTS", nullable = false)
	private Date updatedTS;

	public Date getCreatedTS() {
		return createdTS;
	}


	public void setCreatedTS(Date createdTS) {
		this.createdTS = createdTS;
	}


	public Date getUpdatedTS() {
		return updatedTS;
	}


	public void setUpdatedTS(Date updatedTS) {
		this.updatedTS = updatedTS;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((createdTS == null) ? 0 : createdTS.hashCode());
		result = prime * result
				+ ((updatedTS == null) ? 0 : updatedTS.hashCode());
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
		SMBaseEntity other = (SMBaseEntity) obj;
		if (createdTS == null) {
			if (other.createdTS != null)
				return false;
		} else if (!createdTS.equals(other.createdTS))
			return false;
		if (updatedTS == null) {
			if (other.updatedTS != null)
				return false;
		} else if (!updatedTS.equals(other.updatedTS))
			return false;
		return true;
	}

}
