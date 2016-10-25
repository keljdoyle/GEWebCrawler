package com.kdoyle.GEWebCrawler;

import java.util.ArrayList;

import com.google.gson.Gson;
import com.kdoyle.GEWebCrawler.models.InternetModel;

public class InternetFactory {

	/**
	 * Given an array of JSON strings, attempts to convert content to InternetModel objects.
	 * 
	 * @param jsons	An arrayList of InternetModel objects.
	 * @return
	 */
	public ArrayList<InternetModel> getInternetModels(String[] jsons)  {
		Gson gson = new Gson();
		ArrayList<InternetModel> models = new ArrayList<InternetModel>();
		
		if (jsons != null) {
			for (String json : jsons) {
				InternetModel model = gson.fromJson(json, InternetModel.class);
				models.add(model);
			}	
		}

		return models;
	}
}
