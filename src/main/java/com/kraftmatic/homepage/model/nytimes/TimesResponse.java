package com.kraftmatic.homepage.model.nytimes;

import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TimesResponse {

	List<Docs> docs;

	public List<Docs> getDocs() {
		return docs;
	}

	public void setDocs(List<Docs> docs) {
		this.docs = docs;
	}

}
