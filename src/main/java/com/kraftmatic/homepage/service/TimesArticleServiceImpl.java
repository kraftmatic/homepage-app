package com.kraftmatic.homepage.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import com.kraftmatic.homepage.Article;
import com.kraftmatic.homepage.model.nytimes.ServiceResponse;
import com.kraftmatic.homepage.times.TimesQuery;
import com.kraftmatic.homepage.times.TimesTransformer;

@Component
public class TimesArticleServiceImpl implements TimesArticleService {

	@Override
	public List<Article> getArticles(String keyword, String startDate,
			String endDate) {

		TimesQuery queryParams = new TimesQuery(keyword, startDate, endDate);
		String timesApiEndpoint = buildApiQuery(queryParams);
		List<Article> articles = fetchArticles(timesApiEndpoint);

		return articles;
	}

	private List<Article> fetchArticles(String timesApiEndpoint) {
		RestTemplate restTemplate = new RestTemplate();
		List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();
		messageConverters.add(new MappingJackson2HttpMessageConverter());
		restTemplate.setMessageConverters(messageConverters);
		ServiceResponse response = restTemplate.getForObject(timesApiEndpoint,
				ServiceResponse.class);
		List<Article> articles = TimesTransformer.generateArticles(response);
		return articles;
	}

	private String buildApiQuery(TimesQuery queryParams) {
		StringBuilder apiString = new StringBuilder();
		apiString
				.append("http://api.nytimes.com/svc/search/v2/articlesearch.json?fq="
						+ queryParams.getSearchTerm());
		if (!StringUtils.isEmpty(queryParams.getBeginDate())) {
			apiString.append("&begin_date="
					+ processDate(queryParams.getBeginDate()));
		}
		if (!StringUtils.isEmpty(queryParams.getEndDate())) {
			apiString.append("&begin_date="
					+ processDate(queryParams.getEndDate()));
		}
		apiString
				.append("&sort=newest&api-key=54998d0970a4e8ca37483968d1206549:8:72125525");
		return apiString.toString();
	}

	public String processDate(String startdate) {
		return startdate.substring(6, 10) + startdate.substring(0, 2)
				+ startdate.substring(3, 5);
	}

}
