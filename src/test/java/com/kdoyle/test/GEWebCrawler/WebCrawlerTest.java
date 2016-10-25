package com.kdoyle.test.GEWebCrawler;

import org.junit.Assert;
import org.junit.Test;

import com.kdoyle.GEWebCrawler.WebCrawler;
import com.kdoyle.GEWebCrawler.models.InternetModel;

public class WebCrawlerTest {

	@Test
	public void wasAlreadyVisitedTest() {
		InternetModel model = new InternetModel();
		WebCrawler crawler = new WebCrawler(model);
		boolean wasVisited = crawler.wasAlreadyVisited("foo");
		Assert.assertFalse(wasVisited);
		wasVisited = crawler.wasAlreadyVisited("foo");
		Assert.assertTrue(wasVisited);
		wasVisited = crawler.wasAlreadyVisited("bar");
		Assert.assertFalse(wasVisited);
	}
	
	@Test
	public void findPageTest() {
		InternetModel model = new InternetModel();
		WebCrawler crawler = new WebCrawler(model);
		crawler.findPage("foob");
		Assert.assertTrue(crawler.getError().size() == 1);
		crawler.findPage("arrrgh");
		Assert.assertTrue(crawler.getError().size() == 2);
		crawler.findPage("foob");
		Assert.assertTrue(crawler.getError().size() == 2);
		
	}
}
