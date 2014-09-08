package com.student.app.rest.service;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.student.app.core.dao.service.IUserMasterService;
import com.student.app.core.dto.UserMasterCreateDTO;
import com.student.app.core.model.UserMaster;
import com.student.app.core.validator.UserMasterCreateDTOValidator;
import com.student.app.rest.reponse.dto.RestResponseConstraintVoilationWrapper;
import com.student.app.rest.reponse.dto.RestResponseWrapper;

@Controller
@Path("/userMaster")
public class UserMasterRestService  extends BaseRestService implements InitializingBean {
	
	@Autowired
	private IUserMasterService iUserMasterService;
	
	@POST
	@Path("/create")
	@Produces(MediaType.APPLICATION_JSON)
	@com.yammer.metrics.annotation.Timed
	public @ResponseBody
	Response create(String jsonRequest) throws JsonProcessingException {

		super.validate();

		ObjectMapper userMasterReqJSONMapper = super
				.getUserMasterCreateDTOJSONReader().fetchEagerly(false);
		
		userMasterReqJSONMapper.setSerializationInclusion(Include.NON_NULL);
		
		UserMasterCreateDTO cm = super.getUserMasterCreateDTOJSONReader()
				.readValue(userMasterReqJSONMapper, jsonRequest,
						UserMasterCreateDTO.class);

		RestResponseConstraintVoilationWrapper<UserMasterCreateDTO> v = super
				.getiValidationServiceUserMasterCreateDTO().validateInput(cm,
						UserMasterCreateDTOValidator.class);

		if (v != null) {
			return super.getiValidationServiceUserMasterCreateDTO()
					.sendValidationErrorsInResponse(v, userMasterReqJSONMapper);
		}

		UserMaster persisted = this.iUserMasterService
				.createEntity(cm);
		
		ObjectMapper responseMapper = super.getUserMasterJSONWriter()
				.fetchEagerly(false);
				
		responseMapper.setSerializationInclusion(Include.NON_NULL);

		RestResponseWrapper<UserMaster> userMasterResponseWrapper = new RestResponseWrapper.Builder<UserMaster>()
				.data(persisted).status(Status.CREATED).build();

		String json = this.getUserMasterJSONWriter().prepareJSON(
				responseMapper, userMasterResponseWrapper, new String[]{""});

		return Response.status(userMasterResponseWrapper.getStatus())
				.header("Content-Type", "application/json").entity(json)
				.build();
	}

}
