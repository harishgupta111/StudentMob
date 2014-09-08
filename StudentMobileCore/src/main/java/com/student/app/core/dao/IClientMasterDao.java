package com.student.app.core.dao;

import com.student.app.core.model.ClientMaster;

public interface IClientMasterDao  extends IBaseDao<ClientMaster, Long> {
	
	ClientMaster findByClientCode(String clientCode);

}
