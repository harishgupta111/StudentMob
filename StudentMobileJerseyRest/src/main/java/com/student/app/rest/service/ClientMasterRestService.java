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
import com.student.app.core.dao.service.IClientMasterService;
import com.student.app.core.model.ClientMaster;
import com.student.app.core.validator.ClientMasterValidator;
import com.student.app.rest.reponse.dto.RestResponseConstraintVoilationWrapper;
import com.student.app.rest.reponse.dto.RestResponseWrapper;

@Controller
@Path("/clientMaster")
public class ClientMasterRestService extends BaseRestService implements InitializingBean {
	
	@Autowired
	private IClientMasterService iClientMasterService;
	
	@POST
	@Path("/getByClientCode")
	@Produces(MediaType.APPLICATION_JSON)
	@com.yammer.metrics.annotation.Timed
	public @ResponseBody Response getByClientCode(String jsonRequest) throws JsonProcessingException 
	{
		super.validate();

		ObjectMapper clientMasterReqJSONMapper = super
				.getClientMasterJSONReader().fetchEagerly(false);
		System.out.println();
		clientMasterReqJSONMapper.setSerializationInclusion(Include.NON_NULL);
		
		ClientMaster cm = super.getClientMasterJSONReader()
				.readValue(clientMasterReqJSONMapper, jsonRequest,
						ClientMaster.class);

		RestResponseConstraintVoilationWrapper<ClientMaster> v = super
				.getiValidationServiceClientMaster().validateInput(cm,
						ClientMasterValidator.class);

		if (v != null) {
			return super.getiValidationServiceClientMaster()
					.sendValidationErrorsInResponse(v, clientMasterReqJSONMapper);
		}

		ClientMaster persisted = this.iClientMasterService
				.findByClientCode(cm.getClientCode());

		RestResponseWrapper<ClientMaster> clientMasterResponseWrapper = new RestResponseWrapper.Builder<ClientMaster>()
				.data(persisted).status(Status.CREATED).build();

		String json = this.getClientMasterJSONWriter().prepareJSON(
				clientMasterReqJSONMapper, clientMasterResponseWrapper, new String[]{""});

		return Response.status(clientMasterResponseWrapper.getStatus())
				.header("Content-Type", "application/json").entity(json)
				.build();
	}
	
	@POST
	@Path("/create")
	@Produces(MediaType.APPLICATION_JSON)
	@com.yammer.metrics.annotation.Timed
	public @ResponseBody
	Response create(String jsonRequest) throws JsonProcessingException {

		super.validate();

		ObjectMapper clientMasterReqJSONMapper = super
				.getClientMasterJSONReader().fetchEagerly(false);
		
		clientMasterReqJSONMapper.setSerializationInclusion(Include.NON_NULL);
		
		ClientMaster cm = super.getClientMasterJSONReader()
				.readValue(clientMasterReqJSONMapper, jsonRequest,
						ClientMaster.class);

		RestResponseConstraintVoilationWrapper<ClientMaster> v = super
				.getiValidationServiceClientMaster().validateInput(cm,
						ClientMasterValidator.class);

		if (v != null) {
			return super.getiValidationServiceClientMaster()
					.sendValidationErrorsInResponse(v, clientMasterReqJSONMapper);
		}

		ClientMaster persisted = this.iClientMasterService
				.createEntity(cm);

		RestResponseWrapper<ClientMaster> clientMasterResponseWrapper = new RestResponseWrapper.Builder<ClientMaster>()
				.data(persisted).status(Status.CREATED).build();

		String json = this.getClientMasterJSONWriter().prepareJSON(
				clientMasterReqJSONMapper, clientMasterResponseWrapper, new String[]{""});

		return Response.status(clientMasterResponseWrapper.getStatus())
				.header("Content-Type", "application/json").entity(json)
				.build();
	}

}
