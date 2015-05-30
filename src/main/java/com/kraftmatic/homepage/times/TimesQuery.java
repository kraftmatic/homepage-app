package com.kraftmatic.homepage.times;

public class TimesQuery {

	private String searchTerm;
	private String beginDate;
	private String endDate;

	public TimesQuery(String searchTerm, String beginDate, String endDate) {
		this.searchTerm = searchTerm;
		this.beginDate = beginDate;
		this.endDate = endDate;
	}

	public String getSearchTerm() {
		return searchTerm;
	}

	public void setSearchTerm(String searchTerm) {
		this.searchTerm = searchTerm;
	}

	public String getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(String beginDate) {
		this.beginDate = beginDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

}
