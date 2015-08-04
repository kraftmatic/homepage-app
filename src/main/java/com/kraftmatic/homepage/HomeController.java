package com.kraftmatic.homepage;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import com.kraftmatic.homepage.model.nytimes.ServiceResponse;
import com.kraftmatic.homepage.times.TimesQuery;
import com.kraftmatic.homepage.times.TimesTransformer;

@Controller
public class HomeController {

	private static final Logger logger = LoggerFactory
			.getLogger(HomeController.class);

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);

		generateYear(model);

		return "home";
	}

	private void generateYear(Model model) {
		Date date = new Date();
		SimpleDateFormat thisYearFormat = new SimpleDateFormat("yyyy");
		String thisYear = thisYearFormat.format(date);

		model.addAttribute("thisYear", thisYear);
	}

	@RequestMapping(value = "/home")
	public String home(Locale locale, Model model, @RequestParam String query,
			@RequestParam String startdate, @RequestParam String enddate) {
		logger.info("Welcome home! The client locale is {}.", locale);

		// read articles and save them as object

		TimesQuery queryParams = new TimesQuery(query, startdate, enddate);
		String timesApiEndpoint = buildApiQuery(queryParams);
		List<Article> articles = fetchArticles(timesApiEndpoint);

		generateYear(model);

		model.addAttribute("nytArticles", articles);
		model.addAttribute("userName", query);

		return "home";
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
