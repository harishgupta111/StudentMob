package com.student.app.core.dao.impl;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.student.app.core.dao.IClientMasterDao;
import com.student.app.core.model.ClientMaster;

@Transactional(readOnly = true)
@Repository("iClientMasterDao")
public class ClientMasterHibernateImpl extends BaseDaoHibernateSupport<ClientMaster, Long> implements IClientMasterDao {

	@Override
	@CacheEvict(value = "SM_ClientMaster", allEntries = true)
	@Transactional(readOnly = false, isolation = Isolation.DEFAULT, propagation = Propagation.MANDATORY)
	public ClientMaster createEntity(ClientMaster t) {
		return super.insert(t, true);
	}

	@Override
	@CacheEvict(value = "SM_ClientMaster", allEntries = true)
	@Transactional(readOnly = false, isolation = Isolation.DEFAULT, propagation = Propagation.MANDATORY)
	public ClientMaster updateEntity(ClientMaster t) {
		return super.update(t, true);
	}

	@Override
	@Cacheable(value = "SM_ClientMaster", key="#pk")
	public ClientMaster getById(Long pk) {
		return super.byId(pk);
	}

	@Override
	@Cacheable(value = "SM_ClientMaster")
	public Set<ClientMaster> getAll() {
		String strSQL = "Select u from ClientMaster u";
		@SuppressWarnings("unchecked")
		List<ClientMaster> list = (List<ClientMaster>) this.executeQuery(strSQL);
		return new LinkedHashSet<ClientMaster>(new LinkedList<ClientMaster>(list));
	}

	@Override
	@Cacheable(value = "SM_ClientMaster", key="#clientCode")
	public ClientMaster findByClientCode(String clientCode) {
		String strSQL = "Select u from ClientMaster u WHERE u.clientCode = :clientCode";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("clientCode", clientCode);
		@SuppressWarnings("unchecked")
		List<ClientMaster> list = (List<ClientMaster>) this
				.executeQuery(strSQL, map);
		if (list != null && list.size() > 0)
			return list.get(0);
		else
			return null;

	}

}
