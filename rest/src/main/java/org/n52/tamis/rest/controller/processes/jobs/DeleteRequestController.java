/**
 * Copyright (C) 2016-2016 52°North Initiative for Geospatial Open Source
 * Software GmbH
 *
 * This program is free software; you can redistribute it and/or modify it under
 * the terms of the GNU General Public License version 2 as publishedby the Free
 * Software Foundation.
 *
 * If the program is linked with libraries which are licensed under one of the
 * following licenses, the combination of the program with the linked library is
 * not considered a "derivative work" of the program:
 *
 *     - Apache License, version 2.0
 *     - Apache Software License, version 1.0
 *     - GNU Lesser General Public License, version 3
 *     - Mozilla Public License, versions 1.0, 1.1 and 2.0
 *     - Common Development and Distribution License (CDDL), version 1.0
 *
 * Therefore the distribution of the program linked with libraries licensed under
 * the aforementioned licenses, is permitted by the copyright holders if the
 * distribution is compliant with both the GNU General Public License version 2
 * and the aforementioned licenses.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A
 * PARTICULAR PURPOSE. See the GNU General Public License for more details.
 */
package org.n52.tamis.rest.controller.processes.jobs;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.n52.tamis.core.urlconstants.URL_Constants_TAMIS;
import org.n52.tamis.rest.controller.AbstractRestController;
import org.n52.tamis.rest.controller.ParameterValueStore;
import org.n52.tamis.rest.forward.processes.jobs.DeleteRequestForwarder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * REST Controller for delete requests. (Only handles GET requests to that URL).
 * 
 * @author Christian Danowski (contact: c.danowski@52north.org)
 *
 */
@RequestMapping(value = URL_Constants_TAMIS.DELETE, method = RequestMethod.DELETE)
public class DeleteRequestController extends AbstractRestController {
	private static final Logger logger = LoggerFactory.getLogger(DeleteRequestController.class);

	@Autowired
	DeleteRequestForwarder deleteRequestForwarder;

	@Autowired
	ParameterValueStore parameterValueStore;

	@RequestMapping("")
	public ResponseEntity delete(@PathVariable(URL_Constants_TAMIS.SERVICE_ID_VARIABLE_NAME) String serviceId,
			@PathVariable(URL_Constants_TAMIS.PROCESS_ID_VARIABLE_NAME) String processId,
			@PathVariable(URL_Constants_TAMIS.JOB_ID_VARIABLE_NAME) String jobId, HttpServletRequest request,
			HttpServletResponse response) {

		logger.info("Received delete request for service id \"{}\", process id \"{}\" and job id \"{}\" !", serviceId,
				processId, jobId);

		initializeParameterValueStore(serviceId, processId, jobId);

		// prepare response entity with HTTP status 404, not found
		ResponseEntity responseEntity = new ResponseEntity(HttpStatus.NOT_FOUND);

		try {
			logger.info("Trying to delete resource at \"{}\"", request.getRequestURL());
			responseEntity = deleteRequestForwarder.forwardRequestToWpsProxy(request, null, parameterValueStore);

		} catch (Exception e) {
			logger.info("DELETE request for resouce at \"{}\" failed.", request.getRequestURL());
			response.setStatus(HttpStatus.NOT_FOUND.value());
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}

		return responseEntity;

	}

	private void initializeParameterValueStore(String serviceId, String processId, String jobId) {
		parameterValueStore.addParameterValuePair(URL_Constants_TAMIS.SERVICE_ID_VARIABLE_NAME, serviceId);
		parameterValueStore.addParameterValuePair(URL_Constants_TAMIS.PROCESS_ID_VARIABLE_NAME, processId);
		parameterValueStore.addParameterValuePair(URL_Constants_TAMIS.JOB_ID_VARIABLE_NAME, jobId);
	}
}
