package com.student.app.rest.validation;

import java.io.Serializable;

import javax.ws.rs.core.Response;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.student.app.rest.reponse.dto.RestResponseConstraintVoilationWrapper;

public interface IValidationService<T extends Serializable> {
	
	public RestResponseConstraintVoilationWrapper<T> validateInput(T t, Class<?> p);
	
	public Response sendValidationErrorsInResponse(RestResponseConstraintVoilationWrapper<T> constraintVoilationWrapper, ObjectMapper mapper) throws JsonProcessingException;


}