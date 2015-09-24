package com.kraftmatic.homepage;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.kraftmatic.homepage.service.TimesArticleService;

@Controller
public class HomeController {

	private static final Logger logger = LoggerFactory
			.getLogger(HomeController.class);

	@Autowired
	private TimesArticleService articleService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {

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

		List<Article> articles = articleService.getArticles(query, startdate,
				enddate);

		generateYear(model);
		model.addAttribute("nytArticles", articles);
		model.addAttribute("userName", query);

		return "home";
	}
}
