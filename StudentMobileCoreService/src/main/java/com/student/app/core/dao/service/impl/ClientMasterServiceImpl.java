package com.student.app.core.dao.service.impl;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.student.app.core.dao.IClientMasterDao;
import com.student.app.core.dao.service.IClientMasterService;
import com.student.app.core.model.ClientMaster;

@Transactional(readOnly = true)
@Repository("iClientMasterService")
public class ClientMasterServiceImpl implements IClientMasterService {
	
	@Autowired
	private IClientMasterDao iClientMasterDao;

	@Override
	@Transactional(readOnly = false, isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
	public ClientMaster createEntity(ClientMaster t) {
		return this.iClientMasterDao.createEntity(t);
	}

	@Override
	@Transactional(readOnly = false, isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
	public ClientMaster updateEntity(ClientMaster t) {
		return this.iClientMasterDao.updateEntity(t);
	}

	@Override
	public ClientMaster getById(Long pk) {
		return this.iClientMasterDao.getById(pk);
	}

	@Override
	public Set<ClientMaster> getAll() {
		return this.iClientMasterDao.getAll();
	}

	@Override
	public ClientMaster findByClientCode(String clientCode) {
		return this.iClientMasterDao.findByClientCode(clientCode);
	}

}
