package com.student.app.rest.jsonmapper.impl;

import java.io.IOException;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.TimeZone;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.fasterxml.jackson.datatype.hibernate4.Hibernate4Module;
import com.student.app.core.exception.SMSystemException;
import com.student.app.rest.jsonmapper.HibernateObjectMapper;

public class HibernateObjectMapperImpl<T extends Serializable> implements
		HibernateObjectMapper<T> {

	public ObjectMapper fetchEagerly(boolean forceLazyLoading) {
		ObjectMapper mapper = new ObjectMapper();
		Hibernate4Module mod = new Hibernate4Module();
		mod.configure(Hibernate4Module.Feature.FORCE_LAZY_LOADING,
				forceLazyLoading);
		mapper.registerModule(mod);
		final DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
		df.setTimeZone(TimeZone.getTimeZone("US/Pacific"));
		mapper.setDateFormat(df);
		return mapper;
	}

	@Override
	public String prepareJSON(ObjectMapper objectMapper, T data)
			throws SMSystemException {
		String json = null;
		try {
			json = objectMapper.writerWithDefaultPrettyPrinter()
					.writeValueAsString(data);
		} catch (JsonProcessingException jpe) {
			jpe.printStackTrace();
			throw new SMSystemException(jpe.getCause());
		}

		return json;
	}

	@Override
	public T readValue(ObjectMapper mapper, String jsonRequest, Class<T> t) {
		try {
			return (T) mapper.readValue(jsonRequest, t);
		} catch (IOException e) {
			throw new SMSystemException(e);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public HashSet<T> readValue(ObjectMapper mapper, String jsonRequest,
			HashSet<T> t, Class<T> tt) {
		HashSet<T> data = new HashSet<T>();
		try {
			HashSet<LinkedHashMap<String, String>> set = (HashSet<LinkedHashMap<String, String>>) mapper
					.readValue(jsonRequest, t.getClass());
			for (LinkedHashMap<String, String> map : set) {
				T ttt = (T) mapper.convertValue(map, tt);
				data.add(ttt);
			}
		} catch (IOException e) {
			throw new SMSystemException(e);
		}
		return data;
	}

	@Override
	public String prepareJSON(ObjectMapper objectMapper, T data,
			String[] ignorableFieldNames) throws SMSystemException,
			JsonProcessingException {

		SimpleBeanPropertyFilter theFilter = SimpleBeanPropertyFilter.serializeAllExcept(ignorableFieldNames);
		SimpleFilterProvider filterProvider = new SimpleFilterProvider();
		filterProvider.setFailOnUnknownId(false);
		filterProvider.addFilter("theFilter", theFilter);
		

		// ObjectWriter writer = objectMapper.writer(filters);
		 ObjectWriter writer = objectMapper.writerWithDefaultPrettyPrinter();
		return writer.with(filterProvider).writeValueAsString(data);

	}

}
