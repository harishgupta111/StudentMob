package com.student.app.rest.exceptionmapper;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.student.app.core.exception.InvalidUserSessionException;
import com.student.app.rest.jsonmapper.HibernateObjectMapper;
import com.student.app.rest.reponse.dto.RestResponseExceptionWrapper;
import com.yammer.metrics.annotation.Timed;

@Provider
@Component("invalidUserSessionExceptionMapper")
public class InvalidUserSessionExceptionMapper implements
		ExceptionMapper<InvalidUserSessionException> {

	private static final Logger logger = Logger.getLogger(InvalidUserSessionExceptionMapper.class);
	
	private HibernateObjectMapper<RestResponseExceptionWrapper<InvalidUserSessionException>> invalidUserSessionExceptionResponseMapper;

	@Override
	@Produces(MediaType.APPLICATION_JSON)
	@Timed
	public Response toResponse(InvalidUserSessionException exception) {
		logger.error(exception.getMessage());
		RestResponseExceptionWrapper<InvalidUserSessionException> restResponseExceptionWrapper = new RestResponseExceptionWrapper.Builder<InvalidUserSessionException>()
				.status(Status.NOT_ACCEPTABLE)
				.errorMessage(exception.getMessage()).build();
		ObjectMapper mapper = this.invalidUserSessionExceptionResponseMapper.fetchEagerly(false);
		String json = null;
		try {
			json = this.invalidUserSessionExceptionResponseMapper.prepareJSON(mapper,
					restResponseExceptionWrapper, new String[]{"exception"} );
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Response.status(restResponseExceptionWrapper.getStatus())
				.header("Content-Type", "application/json").entity(json)
				.build();
	}

	public HibernateObjectMapper<RestResponseExceptionWrapper<InvalidUserSessionException>> getInvalidUserSessionExceptionResponseMapper() {
		return invalidUserSessionExceptionResponseMapper;
	}

	public void setInvalidUserSessionExceptionResponseMapper(
			HibernateObjectMapper<RestResponseExceptionWrapper<InvalidUserSessionException>> invalidUserSessionExceptionResponseMapper) {
		this.invalidUserSessionExceptionResponseMapper = invalidUserSessionExceptionResponseMapper;
	}

}
