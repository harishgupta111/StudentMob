package com.student.app.core.model;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.Index;
import org.springframework.security.core.GrantedAuthority;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@SuppressWarnings("deprecation")
@Entity
@org.hibernate.annotations.Table(
indexes = {
        @Index(name = "IDX_SM_UserAuthorityMaster_userid",
                columnNames = {"userid"}
        )
}, appliesTo = "SM_UserAuthorityMaster")
@Table(name = "SM_UserAuthorityMaster", uniqueConstraints={@UniqueConstraint(columnNames={"userID", "authority"})})
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "userAuthorityId")
@Cache(region = "SM_UserAuthorityMaster", usage = CacheConcurrencyStrategy.READ_WRITE)
@Cacheable
public class UserAuthorityMaster extends SMBaseEntity implements GrantedAuthority {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5119148668265626444L;

	@Id
	@Column(name = "userAuthorityId", nullable = false, insertable = false, updatable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long userAuthorityId;
	
	@Fetch(FetchMode.JOIN)
	@JoinColumn(name = "userID", referencedColumnName = "userID", nullable=false)
	@ManyToOne(targetEntity = UserMaster.class, fetch = FetchType.LAZY)
	private UserMaster userMaster;
	
	@Column(name = "authority", nullable=false)
	private String authority;

	public UserMaster getUserMaster() {
		return userMaster;
	}
	
	public void setUserMaster(UserMaster userMaster) {
		this.userMaster = userMaster;
	}

	@Override
	public String getAuthority() {
		return this.authority;
	}
	
	public UserAuthorityMaster() {
		super();
	}
	
	public Long getUserAuthorityId() {
		return userAuthorityId;
	}

	public void setUserAuthorityId(Long userAuthorityId) {
		this.userAuthorityId = userAuthorityId;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ ((authority == null) ? 0 : authority.hashCode());
		result = prime
				* result
				+ ((userAuthorityId == null) ? 0 : userAuthorityId
						.hashCode());
		result = prime * result
				+ ((userMaster == null) ? 0 : userMaster.hashCode());
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
		UserAuthorityMaster other = (UserAuthorityMaster) obj;
		if (authority == null) {
			if (other.authority != null)
				return false;
		} else if (!authority.equals(other.authority))
			return false;
		if (userAuthorityId == null) {
			if (other.userAuthorityId != null)
				return false;
		} else if (!userAuthorityId.equals(other.userAuthorityId))
			return false;
		if (userMaster == null) {
			if (other.userMaster != null)
				return false;
		} else if (!userMaster.equals(other.userMaster))
			return false;
		return true;
	}


}
