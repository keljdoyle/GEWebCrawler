package com.kdoyle.GEWebCrawler;

import java.io.IOException;
import java.util.ArrayList;

import com.kdoyle.GEWebCrawler.models.InternetModel;

/**
 * This runs the WebCrawler app.
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	FileContentLoader loader = new FileContentLoader();
    	ArrayList<String> jsons = null;
    	
    	// Load JSON from resource folder.
    	try {
    		jsons = loader.loadResourceFileContents("json");
    	} catch (IOException ex) {
    		System.out.println("Error loading resource files.");
    		return;
    	}
    	
    	// Create models from the json.
    	InternetFactory factory = new InternetFactory();
    	
    	ArrayList<InternetModel> models = null;
    	try {
    		models = factory.getInternetModels(jsons.toArray(new String[jsons.size()]));
    	} catch (Exception ex) {
    		System.out.println("An error occurred while parsing JSON.");
    		return;
    	}
		
		// Crawl each of the models
		for (InternetModel model : models) {
			crawlAndPrint(model);
		}
    }
    
    private static void crawlAndPrint(InternetModel model) {
		WebCrawler crawler = new WebCrawler(model);
		crawler.crawl();
		
		// Display success
		String successOut = "Success:\n [";
		for (int i=0; i < crawler.getSuccess().size(); i++) {
			successOut += "\"" + crawler.getSuccess().get(i) + "\"";
			if (i < crawler.getSuccess().size()-1) {
				successOut += ",";
			}
		}
		successOut += "]";
		System.out.println(successOut);
		
		// Display skipped
		String skippedOut = "Skipped:\n [";
		for (int i=0; i < crawler.getSkipped().size(); i++) {
			skippedOut += "\"" + crawler.getSkipped().get(i) + "\"";
			if (i < crawler.getSkipped().size()-1) {
				skippedOut += ",";
			}
		}
		skippedOut += "]";
		System.out.println(skippedOut);
		
		// Display error
		String errorOut = "Error:\n [";
		for (int i=0; i < crawler.getError().size(); i++) {
			errorOut += "\"" + crawler.getError().get(i) + "\"";
			if (i < crawler.getError().size()-1) {
				errorOut += ",";
			}
		}
		errorOut += "]";
		System.out.println(errorOut);
    }
}
