package com.student.app.core.dao.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.student.app.core.dto.UserMasterCreateDTO;
import com.student.app.core.model.UserMaster;

public interface IUserMasterService extends IBaseDaoService<UserMaster, Long>, UserDetailsService {
	
	public UserMaster login(String userName, String password);

	public Boolean isUserSessionValid();

	public Boolean logout(HttpServletRequest request);

	public UserMaster createEntity(UserMasterCreateDTO cm);



}
