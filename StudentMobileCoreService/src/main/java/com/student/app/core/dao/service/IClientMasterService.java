package com.student.app.core.dao.service;

import com.student.app.core.model.ClientMaster;

public interface IClientMasterService extends IBaseDaoService<ClientMaster, Long> {

	ClientMaster findByClientCode(String clientCode);
}
