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

import com.student.app.core.dao.IUserMasterDao;
import com.student.app.core.model.UserMaster;

@Transactional(readOnly = true)
@Repository("iUserMasterDao")
public class UserMasterDaoHibernateImpl extends
		BaseDaoHibernateSupport<UserMaster, Long> implements IUserMasterDao {

	// private static final Logger logger = Logger.getLogger(UserMasterDaoHibernateImpl.class);
	
	@Override
	@CacheEvict(value = "SM_UserMaster", allEntries = true)
	@Transactional(readOnly = false, isolation = Isolation.DEFAULT, propagation = Propagation.MANDATORY)
	public UserMaster createEntity(UserMaster t) {
		return super.insert(t, true);
	}

	@Override
	@CacheEvict(value = "SM_UserMaster", allEntries = true)
	@Transactional(readOnly = false, isolation = Isolation.DEFAULT, propagation = Propagation.MANDATORY)
	public UserMaster updateEntity(UserMaster t) {
		return super.update(t, true);
	}

	@Override
	@Cacheable(value = "SM_UserMaster", key="#pk")
	public UserMaster getById(Long pk) {
		return super.byId(pk);
	}

	@Override
	@Cacheable(value = "SM_UserMaster")
	public Set<UserMaster> getAll() {
		String strSQL = "Select u from UserMaster u";
		@SuppressWarnings("unchecked")
		List<UserMaster> list = (List<UserMaster>) this.executeQuery(strSQL);
		return new LinkedHashSet<UserMaster>(new LinkedList<UserMaster>(list));
	}

	@Override
	@Cacheable(value = "SM_UserMaster", key="#username")
	public UserMaster findUserByUsername(String username) {
		String strSQL = "Select u from UserMaster u left join fetch u.userAuthorityMasterSet WHERE u.username = :username";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("username", username);
		@SuppressWarnings("unchecked")
		List<UserMaster> list = (List<UserMaster>) this.executeQuery(strSQL, map);
		if (list != null && list.size() > 0)
			return list.get(0);
		else
			return null;
	}

	@Override
	@Cacheable(value = "SM_UserMaster", key="#email")
	public UserMaster findUserByEmail(String email) {
		String strSQL = "Select u from UserMaster u left join fetch u.userAuthorityMasterSet WHERE u.email = :email";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("email", email);
		@SuppressWarnings("unchecked")
		List<UserMaster> list = (List<UserMaster>) this.executeQuery(strSQL, map);
		if (list != null && list.size() > 0)
			return list.get(0);
		else
			return null;
	}


}
