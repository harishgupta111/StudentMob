package com.student.app.core.model;

import java.util.Collection;
import java.util.Set;

import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.Index;
import org.hibernate.annotations.Type;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@SuppressWarnings("deprecation")
@JsonFilter("theFilter")
@Entity
@org.hibernate.annotations.Table(indexes = { @Index(name = "IDX_SM_UserMaster_username_password", columnNames = {
		"username", "password" }) }, appliesTo = "SM_UserMaster")
@Table(name = "SM_UserMaster")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "userId")
@Cache(region = "SM_UserMaster", usage = CacheConcurrencyStrategy.READ_WRITE)
@Cacheable
public class UserMaster extends SMBaseEntity implements UserDetails {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5764853939933757942L;

	@Id
	@Column(name = "userId", nullable = false, insertable = false, updatable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long userId;

	@Column(name = "username", nullable = false, unique = true)
	private String username;

	@Column(name = "password", nullable = false)
	private String password;

	@Column(name = "enabled", nullable = false)
	@Type(type = "yes_no")
	private Boolean enabled;

	@Column(name = "credentialsNonExpired", nullable = false)
	@Type(type = "yes_no")
	private Boolean credentialsNonExpired;

	@Column(name = "accountNonLocked", nullable = false)
	@Type(type = "yes_no")
	private Boolean accountNonLocked;

	@Column(name = "accountNonExpired", nullable = false)
	@Type(type = "yes_no")
	private Boolean accountNonExpired;

	@Column(name = "email", nullable = false, unique = true)
	private String email;

	@Column(name = "name", nullable = false)
	private String name;

	@Type(type = "yes_no")
	@Column(name = "tempPasswordIssued", nullable = false)
	private Boolean tempPasswordIssued;

	@OneToMany(mappedBy = "userMaster", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<UserAuthorityMaster> userAuthorityMasterSet;

	@Fetch(FetchMode.JOIN)
	@JoinColumn(name = "clientID", referencedColumnName = "clientID", nullable = false)
	@ManyToOne(targetEntity = ClientMaster.class, fetch = FetchType.LAZY)
	private ClientMaster clientMaster;
	
	public Set<UserAuthorityMaster> getUserAuthorityMasterSet() {
		return userAuthorityMasterSet;
	}

	public void setUserAuthorityMasterSet(
			Set<UserAuthorityMaster> userAuthorityMasterSet) {
		this.userAuthorityMasterSet = userAuthorityMasterSet;
	}

	public ClientMaster getClientMaster() {
		return clientMaster;
	}

	public void setClientMaster(ClientMaster clientMaster) {
		this.clientMaster = clientMaster;
	}

	@JsonIgnore
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.userAuthorityMasterSet;
	}

	@Override
	public String getPassword() {
		return this.password;
	}

	@Override
	public String getUsername() {
		return this.username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return this.accountNonExpired;
	}

	@Override
	public boolean isAccountNonLocked() {
		return this.accountNonLocked;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return credentialsNonExpired;
	}

	@Override
	public boolean isEnabled() {
		return enabled;
	}

	public String getEmail() {
		return email;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public void setCredentialsNonExpired(Boolean credentialsNonExpired) {
		this.credentialsNonExpired = credentialsNonExpired;
	}

	public void setAccountNonLocked(Boolean accountNonLocked) {
		this.accountNonLocked = accountNonLocked;
	}

	public void setAccountNonExpired(Boolean accountNonExpired) {
		this.accountNonExpired = accountNonExpired;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Boolean getTempPasswordIssued() {
		return tempPasswordIssued;
	}

	public void setTempPasswordIssued(Boolean tempPasswordIssued) {
		this.tempPasswordIssued = tempPasswordIssued;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime
				* result
				+ ((accountNonExpired == null) ? 0 : accountNonExpired
						.hashCode());
		result = prime
				* result
				+ ((accountNonLocked == null) ? 0 : accountNonLocked.hashCode());
		result = prime
				* result
				+ ((credentialsNonExpired == null) ? 0 : credentialsNonExpired
						.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((enabled == null) ? 0 : enabled.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result
				+ ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
		result = prime * result
				+ ((username == null) ? 0 : username.hashCode());
		result = prime
				* result
				+ ((tempPasswordIssued == null) ? 0 : tempPasswordIssued
						.hashCode());
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
		UserMaster other = (UserMaster) obj;
		if (accountNonExpired == null) {
			if (other.accountNonExpired != null)
				return false;
		} else if (!accountNonExpired.equals(other.accountNonExpired))
			return false;
		if (accountNonLocked == null) {
			if (other.accountNonLocked != null)
				return false;
		} else if (!accountNonLocked.equals(other.accountNonLocked))
			return false;
		if (credentialsNonExpired == null) {
			if (other.credentialsNonExpired != null)
				return false;
		} else if (!credentialsNonExpired.equals(other.credentialsNonExpired))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (enabled == null) {
			if (other.enabled != null)
				return false;
		} else if (!enabled.equals(other.enabled))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		if (tempPasswordIssued == null) {
			if (other.tempPasswordIssued != null)
				return false;
		} else if (!tempPasswordIssued.equals(other.tempPasswordIssued))
			return false;
		return true;
	}

}
