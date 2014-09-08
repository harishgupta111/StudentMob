package com.student.app.core.model;

import java.util.Set;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "SM_ClientMaster")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "clientId")
@Cache(region = "SM_ClientMaster", usage = CacheConcurrencyStrategy.READ_WRITE)
@Cacheable
public class ClientMaster extends SMBaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4299561728564151890L;
	
	@Id
	@Column(name = "clientId", insertable = false, updatable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long clientId;

	@Column(name = "clientName", nullable = false, unique = true)
	private String clientName;
	
	@Column(name = "clientCode", nullable = false, unique = true)
	private String clientCode;
	
	@OneToMany(mappedBy = "clientMaster", fetch = FetchType.LAZY)
	private Set<UserMaster> userMasterSet;

	public Long getClientId() {
		return clientId;
	}

	public void setClientId(Long clientId) {
		this.clientId = clientId;
	}

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public Set<UserMaster> getUserMasterSet() {
		return userMasterSet;
	}

	public void setUserMasterSet(Set<UserMaster> userMasterSet) {
		this.userMasterSet = userMasterSet;
	}
	
	public String getClientCode() {
		return clientCode;
	}

	public void setClientCode(String clientCode) {
		this.clientCode = clientCode;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ ((clientCode == null) ? 0 : clientCode.hashCode());
		result = prime * result
				+ ((clientId == null) ? 0 : clientId.hashCode());
		result = prime * result
				+ ((clientName == null) ? 0 : clientName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		ClientMaster other = (ClientMaster) obj;
		if (clientCode == null) {
			if (other.clientCode != null)
				return false;
		} else if (!clientCode.equals(other.clientCode))
			return false;
		if (clientId == null) {
			if (other.clientId != null)
				return false;
		} else if (!clientId.equals(other.clientId))
			return false;
		if (clientName == null) {
			if (other.clientName != null)
				return false;
		} else if (!clientName.equals(other.clientName))
			return false;
		return true;
	}
	
}
