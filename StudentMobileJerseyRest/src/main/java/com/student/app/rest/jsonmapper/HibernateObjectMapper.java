package com.student.app.rest.jsonmapper;

import java.io.Serializable;
import java.util.HashSet;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public interface HibernateObjectMapper<T extends Serializable> {

	public ObjectMapper fetchEagerly(boolean forceLazyLoading);
	
	public String prepareJSON(ObjectMapper objectMapper, T data);
	
	public String prepareJSON(ObjectMapper objectMapper, T data, String[] ignorableFieldNames) throws JsonProcessingException;
	
	public T readValue(ObjectMapper mapper, String jsonRequest, Class<T> t);
	
	public HashSet<T> readValue(ObjectMapper mapper, String jsonRequest, HashSet<T> t,Class<T> tt);
	
	
}
