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
package org.n52.tamis.core.javarepresentations.processes.jobs.result;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Java representation of a WPS output description inside a result document.
 * 
 * @author Christian Danowski (contact: c.danowski@52north.org)
 *
 */
@JsonInclude(Include.NON_NULL)
public class ResultOutput {

	@JsonProperty("ID")
	private String id;
	
	@JsonProperty("Data")
	private ResultData data;
	
	@JsonProperty("Reference")
	private ResultReference reference;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public ResultReference getReference() {
		return reference;
	}

	public void setReference(ResultReference reference) {
		this.reference = reference;
	}

	public ResultData getData() {
		return data;
	}

	public void setData(ResultData data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "ResultOutput [id=" + id + ", data=" + data + ", reference=" + reference + "]";
	}

}
