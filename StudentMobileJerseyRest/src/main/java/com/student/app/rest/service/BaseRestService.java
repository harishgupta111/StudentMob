package com.student.app.rest.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Variant;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.student.app.core.exception.InvalidUserSessionException;

@Component
public class BaseRestService extends RestService {

	@Context
	HttpServletRequest httpServletRequest;

	private static final Logger logger = Logger.getLogger(BaseRestService.class);

	public void validate() {
		// checking the access token validity
		Boolean status = (Boolean) httpServletRequest
				.getAttribute("SESSION_STATUS");
		if (status != null && !status) {
			List<Variant> list = new ArrayList<Variant>();
			list.add(new Variant(MediaType.APPLICATION_JSON_TYPE, Locale.US,
					null));
			logger.debug("SESSION_STATUS found as false, marking request as unacceptable");
			throw new InvalidUserSessionException("User session found invalid");

		} else {
			logger.debug("SESSION_STATUS found as true, proceeding to request processing");
			
		}

	}
	
	
}