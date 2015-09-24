package com.kraftmatic.homepage.service;

import java.util.List;

import com.kraftmatic.homepage.Article;

public interface TimesArticleService {

	public List<Article> getArticles(String keyword, String startDate,
			String endDate);

}
