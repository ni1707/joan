package com.teslagov.joan.portal.content.analyze;

import com.fasterxml.jackson.annotation.JsonRawValue;
import com.teslagov.joan.core.Response;

/**
 * Created by joncrain on 9/23/16.
 */
public class ItemAnalyzeResponse extends Response {
	private Object publishParameters;

	@JsonRawValue
	public Object getPublishParameters() {
		return publishParameters;
	}

	public void setPublishParameters(Object publishParameters) {
		this.publishParameters = publishParameters;
	}
}
