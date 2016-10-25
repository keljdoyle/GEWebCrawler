package com.kdoyle.GEWebCrawler.models;

/**
 * This represents a Page, which consists of an address and a set of links.
 * 
 * @author keljd
 *
 */
public class PageModel {

	private String address;
	private String[] links;
	
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String[] getLinks() {
		return links;
	}

	public void setLinks(String[] links) {
		this.links = links;
	}
	
	
}
