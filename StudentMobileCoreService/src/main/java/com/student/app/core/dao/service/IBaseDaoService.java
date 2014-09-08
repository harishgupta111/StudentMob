package com.student.app.core.dao.service;

import java.io.Serializable;

import com.student.app.core.dao.IBaseDao;
import com.student.app.core.model.SMBaseEntity;

public interface IBaseDaoService<T extends SMBaseEntity, PK extends Serializable> extends IBaseDao<T , PK> {

}
