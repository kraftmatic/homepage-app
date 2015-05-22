package com.kraftmatic.homepage.model.nytimes;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ServiceResponse {

	private TimesResponse response;

	public TimesResponse getResponse() {
		return response;
	}

	public void setResponse(TimesResponse response) {
		this.response = response;
	}
}
