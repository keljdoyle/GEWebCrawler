package com.kdoyle.GEWebCrawler.models;

/**
 * The InternetModel represents a set of pages that may link to each other.
 * 
 * @author keljd
 *
 */
public class InternetModel {

	private PageModel[] pages;

	public PageModel[] getPages() {
		return pages;
	}

	public void setPages(PageModel[] pages) {
		this.pages = pages;
	}
	
	
}
