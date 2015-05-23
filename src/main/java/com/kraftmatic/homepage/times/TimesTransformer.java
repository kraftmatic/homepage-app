package com.kraftmatic.homepage.times;

import java.util.ArrayList;
import java.util.List;

import com.kraftmatic.homepage.Article;
import com.kraftmatic.homepage.model.nytimes.Docs;
import com.kraftmatic.homepage.model.nytimes.ServiceResponse;

public class TimesTransformer {

	public static List<Article> generateArticles(ServiceResponse response) {
		List<Article> articles = new ArrayList<Article>();

		for (Docs doc : response.getResponse().getDocs()) {
			Article article = new Article();
			article.setDate(doc.getPub_date());
			article.setTitle(doc.getHeadline().getMain());
			article.setSnippet(doc.getSnippet());
			article.setUrl(doc.getWeb_url());
			articles.add(article);
		}

		return articles;
	}

}
