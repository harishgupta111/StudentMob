package com.student.app.rest.service;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import com.student.app.core.dto.UserMasterCreateDTO;
import com.student.app.core.model.ClientMaster;
import com.student.app.core.model.UserMaster;
import com.student.app.rest.jsonmapper.HibernateObjectMapper;
import com.student.app.rest.reponse.dto.RestResponseWrapper;
import com.student.app.rest.validation.IValidationService;

@Component("restService")
public class RestService implements InitializingBean {

	// start beans for ClientMasterRestService
	private HibernateObjectMapper<ClientMaster> clientMasterJSONReader;
	private HibernateObjectMapper<RestResponseWrapper<ClientMaster>> clientMasterJSONWriter;
	private IValidationService<ClientMaster> iValidationServiceClientMaster;
	// end beans for ClientMasterRestService

	// start beans for UserMasterRestService
	private HibernateObjectMapper<UserMasterCreateDTO> userMasterCreateDTOJSONReader;
	private HibernateObjectMapper<RestResponseWrapper<UserMaster>> userMasterJSONWriter;
	private IValidationService<UserMasterCreateDTO> iValidationServiceUserMasterCreateDTO;
	// end beans for UserMasterRestService

	@Override
	public void afterPropertiesSet() throws Exception {
		Assert.notNull(clientMasterJSONReader, clientMasterJSONReader
				.getClass().getCanonicalName()
				+ " not autowired. Check the bean defn in beansWeb.xml");

		Assert.notNull(clientMasterJSONWriter, clientMasterJSONWriter
				.getClass().getCanonicalName()
				+ " not autowired. Check the bean defn in beansWeb.xml");

		Assert.notNull(iValidationServiceClientMaster,
				iValidationServiceClientMaster.getClass().getCanonicalName()
						+ " not autowired. Check the bean defn in beansWeb.xml");

		Assert.notNull(iValidationServiceUserMasterCreateDTO,
				iValidationServiceUserMasterCreateDTO.getClass()
						.getCanonicalName()
						+ " not autowired. Check the bean defn in beansWeb.xml");

		Assert.notNull(userMasterCreateDTOJSONReader,
				userMasterCreateDTOJSONReader.getClass().getCanonicalName()
						+ " not autowired. Check the bean defn in beansWeb.xml");

		Assert.notNull(userMasterJSONWriter, userMasterJSONWriter.getClass()
				.getCanonicalName()
				+ " not autowired. Check the bean defn in beansWeb.xml");

	}

	public HibernateObjectMapper<ClientMaster> getClientMasterJSONReader() {
		return clientMasterJSONReader;
	}

	public void setClientMasterJSONReader(
			HibernateObjectMapper<ClientMaster> clientMasterJSONReader) {
		this.clientMasterJSONReader = clientMasterJSONReader;
	}

	public HibernateObjectMapper<RestResponseWrapper<ClientMaster>> getClientMasterJSONWriter() {
		return clientMasterJSONWriter;
	}

	public void setClientMasterJSONWriter(
			HibernateObjectMapper<RestResponseWrapper<ClientMaster>> clientMasterJSONWriter) {
		this.clientMasterJSONWriter = clientMasterJSONWriter;
	}

	public IValidationService<ClientMaster> getiValidationServiceClientMaster() {
		return iValidationServiceClientMaster;
	}

	public void setiValidationServiceClientMaster(
			IValidationService<ClientMaster> iValidationServiceClientMaster) {
		this.iValidationServiceClientMaster = iValidationServiceClientMaster;
	}

	public IValidationService<UserMasterCreateDTO> getiValidationServiceUserMasterCreateDTO() {
		return iValidationServiceUserMasterCreateDTO;
	}

	public void setiValidationServiceUserMasterCreateDTO(
			IValidationService<UserMasterCreateDTO> iValidationServiceUserMasterCreateDTO) {
		this.iValidationServiceUserMasterCreateDTO = iValidationServiceUserMasterCreateDTO;
	}

	public HibernateObjectMapper<UserMasterCreateDTO> getUserMasterCreateDTOJSONReader() {
		return userMasterCreateDTOJSONReader;
	}

	public void setUserMasterCreateDTOJSONReader(
			HibernateObjectMapper<UserMasterCreateDTO> userMasterCreateDTOJSONReader) {
		this.userMasterCreateDTOJSONReader = userMasterCreateDTOJSONReader;
	}

	public HibernateObjectMapper<RestResponseWrapper<UserMaster>> getUserMasterJSONWriter() {
		return userMasterJSONWriter;
	}

	public void setUserMasterJSONWriter(
			HibernateObjectMapper<RestResponseWrapper<UserMaster>> userMasterJSONWriter) {
		this.userMasterJSONWriter = userMasterJSONWriter;
	}

}