package com.student.app.core.dao;

import java.io.Serializable;
import java.util.Set;

import com.student.app.core.model.SMBaseEntity;

public interface IBaseDao<T extends SMBaseEntity, PK extends Serializable> {

	T createEntity(T t);

	T updateEntity(T t);

	T getById(PK pk);

	Set<T> getAll();

}
