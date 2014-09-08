package com.student.app.rest.validation.impl;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.student.app.rest.jsonmapper.HibernateObjectMapper;
import com.student.app.rest.reponse.dto.RestResponseConstraintVoilationWrapper;
import com.student.app.rest.validation.IValidationService;

@Component("iValidationService")
public class ValidationServiceImpl<T extends Serializable> implements
		IValidationService<T> {

	private static Validator validator = Validation
			.buildDefaultValidatorFactory().getValidator();

	private HibernateObjectMapper<T> hibernateObjectMapper;
	
	private HibernateObjectMapper<RestResponseConstraintVoilationWrapper<T>> restResponseConstraintVoilationMapper;
	
	@Override
	public RestResponseConstraintVoilationWrapper<T> validateInput(T t, Class<?> p) {

		Set<ConstraintViolation<T>> constraintViolations = validator
				.validate(t, p);
		Set<String> voilations = new HashSet<String>();
		if (constraintViolations != null && constraintViolations.size() > 0) {

			for (ConstraintViolation<T> i : constraintViolations)
				voilations.add(i.getMessage());

			RestResponseConstraintVoilationWrapper<T> constraintVoilationWrapper = new RestResponseConstraintVoilationWrapper.Builder<T>()
					.voilationSet(voilations).status(Status.NOT_ACCEPTABLE)
					.build();

			return constraintVoilationWrapper;

		}
		else
			return null;

	}
	
	@Override
	public Response sendValidationErrorsInResponse(
			RestResponseConstraintVoilationWrapper<T> constraintVoilationWrapper, ObjectMapper mapper) throws JsonProcessingException {

			String json = this.restResponseConstraintVoilationMapper.prepareJSON(mapper,
					constraintVoilationWrapper, new String[] { "" });

			return Response.status(constraintVoilationWrapper.getStatus())
					.header("Content-Type", "application/json").entity(json)
					.build();

	}

	public HibernateObjectMapper<T> getHibernateObjectMapper() {
		return hibernateObjectMapper;
	}

	public void setHibernateObjectMapper(
			HibernateObjectMapper<T> hibernateObjectMapper) {
		this.hibernateObjectMapper = hibernateObjectMapper;
	}

	public HibernateObjectMapper<RestResponseConstraintVoilationWrapper<T>> getRestResponseConstraintVoilationMapper() {
		return restResponseConstraintVoilationMapper;
	}

	public void setRestResponseConstraintVoilationMapper(
			HibernateObjectMapper<RestResponseConstraintVoilationWrapper<T>> restResponseConstraintVoilationMapper) {
		this.restResponseConstraintVoilationMapper = restResponseConstraintVoilationMapper;
	}
	
}
