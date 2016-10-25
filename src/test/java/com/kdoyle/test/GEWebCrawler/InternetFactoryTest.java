package com.kdoyle.test.GEWebCrawler;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Test;

import com.kdoyle.GEWebCrawler.InternetFactory;
import com.kdoyle.GEWebCrawler.models.InternetModel;

public class InternetFactoryTest {
	
	@Test
	public void getInternetModelsTest() {
		try {
			InternetFactory factory = new InternetFactory();
			
			String[] jsons = { "{\"pages\": [{\"address\":\"http://foo.bar.com/p1\", \"links\": [\"http://foo.bar.com/p2\"]}]}"  };
			
			ArrayList<InternetModel> models = factory.getInternetModels(jsons);
			Assert.assertTrue(models.size() == 1);
			Assert.assertTrue(models.get(0).getPages().length == 1);
			
		} catch (Exception ex) {
			Assert.fail("Exception encountered.");
		}
	}
}