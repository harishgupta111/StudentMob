package com.student.app.core.dao.service.impl;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.student.app.core.dao.IClientMasterDao;
import com.student.app.core.dao.IUserMasterDao;
import com.student.app.core.dao.service.IUserMasterService;
import com.student.app.core.dto.UserMasterCreateDTO;
import com.student.app.core.model.UserAuthorityMaster;
import com.student.app.core.model.UserMaster;

@Transactional(readOnly = true)
@Repository("iUserMasterService")
public class UserMasterServiceImpl implements IUserMasterService {
	
	@Autowired
	private IUserMasterDao iUserMasterDao;
	
	@Autowired
	private IClientMasterDao iClientMasterDao;

	@Override
	@Transactional(readOnly = false, isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
	public UserMaster createEntity(UserMaster t) {
		return this.iUserMasterDao.createEntity(t);
	}

	@Override
	@Transactional(readOnly = false, isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
	public UserMaster updateEntity(UserMaster t) {
		return this.iUserMasterDao.updateEntity(t);
	}

	@Override
	public UserMaster getById(Long pk) {
		return this.iUserMasterDao.getById(pk);
	}

	@Override
	public Set<UserMaster> getAll() {
		return this.iUserMasterDao.getAll();
	}

	@Override
	public UserMaster login(String userName, String password) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean isUserSessionValid() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean logout(HttpServletRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		return this.iUserMasterDao.findUserByUsername(username);
	}

	@Override
	@Transactional(readOnly = false, isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
	public UserMaster createEntity(UserMasterCreateDTO cm) {
		UserMaster um = new UserMaster();
		um.setAccountNonExpired(true);
		um.setAccountNonLocked(true);
		um.setCreatedTS(new Date());
		um.setCredentialsNonExpired(true);
		um.setEmail(cm.getEmail());
		um.setEnabled(true);
		um.setName(cm.getName());
		um.setPassword(cm.getPassword());
		um.setTempPasswordIssued(false);
		um.setUpdatedTS(new Date());
		um.setUsername(cm.getUsername());
		um.setClientMaster(this.iClientMasterDao.getById(cm.getClientId()));
		UserAuthorityMaster uam = new UserAuthorityMaster();
		uam.setUserMaster(um);
		uam.setAuthority(cm.getAuthority());
		uam.setCreatedTS(new Date());
		uam.setUpdatedTS(new Date());
		Set<UserAuthorityMaster> uamSet = new HashSet<UserAuthorityMaster>();
		uamSet.add(uam);
		um.setUserAuthorityMasterSet(uamSet);
		return this.createEntity(um);
	}

}
