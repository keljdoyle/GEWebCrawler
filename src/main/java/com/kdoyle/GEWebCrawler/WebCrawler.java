package com.kdoyle.GEWebCrawler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import com.kdoyle.GEWebCrawler.models.InternetModel;
import com.kdoyle.GEWebCrawler.models.PageModel;

public class WebCrawler {

	private InternetModel internet;
	private ArrayList<String> visited;
	private ArrayList<String> success;
	private ArrayList<String> skipped;
	private ArrayList<String> error;

	public WebCrawler(InternetModel internet) {
		if (internet == null) {
			throw new IllegalArgumentException("InternetModel cannot be null");
		}
		this.internet = internet;
		reset();
	}
	
	private void reset() {
		visited = new ArrayList<String>();
		success = new ArrayList<String>();
		skipped = new ArrayList<String>();
		error = new ArrayList<String>();
	}
	
	/**
	 * Crawls a specified internet model and updates the statistics generated.
	 * @param internet
	 */
	public void crawl() {
		reset();
		
		if (internet.getPages() == null || internet.getPages().length == 0) {
			return;
		}
		
		// Start crawling at first page.
		crawl(internet.getPages()[0]);
	}
	
	/**
	 * Crawls a page recursively.
	 * 
	 * Parallelism: I used a simple Java parallelStream here to load links in multi-threaded manner.
	 * @param page
	 */
	private void crawl(PageModel page) {
		if (!wasAlreadyVisited(page.getAddress())) {
			success.add(page.getAddress());
			
			Set<String> links = new HashSet<String>(Arrays.asList(page.getLinks()));
			links.parallelStream().forEach((link) -> {
				PageModel foundPage = findPage(link);
				if (foundPage != null) {
					crawl(foundPage);
				}
		    });
			
			/* Single threaded version for reference
			for (String link : page.getLinks()) {
				Set<String> links = new Set<String>(page.getLinks());
				PageModel foundPage = findPage(link);
				if (foundPage != null) {
					crawl(foundPage);
				}
			}
			*/
		}
	}
	
	/**
	 * Determines if an address was already visited, updated visited and skipped arrays.
	 * This method is synchronized to prevet race conditions with the visited and skipped arrays.
	 * 
	 * @param address	Address of the page.
	 * @return	TRUE if page was already visited. Else false;
	 */
	public synchronized boolean wasAlreadyVisited(String address) {
		if (visited.contains(address)) {
			if (!skipped.contains(address)) {
				skipped.add(address);
			}
			return true;
		}
		
		visited.add(address);
		return false;
	}
	
	/**
	 * Attempts to find a page references by an address.
	 * @param address
	 * @return
	 */
	public PageModel findPage(String address) {
		if (this.internet.getPages() != null) {
			for (PageModel page : this.internet.getPages()) {
				if (page.getAddress() != null && page.getAddress().equals(address)) {
					return page;
				}
			}
		}

		// Page was not found.
		logErrorPage(address);
		return null;
	}
	
	public ArrayList<String> getVisited() {
		return visited;
	}

	public ArrayList<String> getSuccess() {
		return success;
	}

	public ArrayList<String> getSkipped() {
		return skipped;
	}

	public ArrayList<String> getError() {
		return error;
	}

	public void setError(ArrayList<String> error) {
		this.error = error;
	}
	
	private synchronized void logErrorPage(String address) {
		if (!error.contains(address)) {
			error.add(address);
		}
	}
	

	

}
