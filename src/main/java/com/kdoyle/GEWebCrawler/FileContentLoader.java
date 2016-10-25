package com.kdoyle.GEWebCrawler;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.commons.io.FileUtils;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

public class FileContentLoader {

	/**
	 * Loads the of all files of a given type.
	 * 
	 * @return	An arraylist of file contents in string format.
	 * @throws IOException 
	 */
	public ArrayList<String> loadResourceFileContents(String extension) throws IOException {
		ClassLoader loader = this.getClass().getClassLoader(); 
		ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver(loader);
		Resource[] resources = resolver.getResources("classpath*:/*." + extension) ;
		ArrayList<String> fileContents = new ArrayList<String>();
		
		for (Resource resource: resources){
		    File file = resource.getFile();
		    String json = FileUtils.readFileToString(file, "UTF-8");
		    fileContents.add(json);
		}
		
		return fileContents;
	}
}
