package com.kraftmatic.homepage.model.nytimes;

import java.util.List;

public class TimesResponse {

	List<Docs> docs;

	public List<Docs> getDocs() {
		return docs;
	}

	public void setDocs(List<Docs> docs) {
		this.docs = docs;
	}

}
