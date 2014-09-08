package com.student.app.core.dao;

import com.student.app.core.model.UserMaster;

public interface IUserMasterDao extends IBaseDao<UserMaster, Long> {
	
	public UserMaster findUserByUsername(String username);
	
	public UserMaster findUserByEmail(String email);
}
