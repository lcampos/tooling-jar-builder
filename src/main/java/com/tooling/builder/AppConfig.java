package com.tooling.builder;

import java.io.*;
import java.util.Properties;
/**
 * Test Tooling API
 *
 */
public class AppConfig {
	
	private InputStream inputStream;
	private Properties prop;
	private final String propFileName = "config.properties";
 	
	public String getUsername() throws IOException {
		return getPropValue("username");
	}
	
	public String getPassword() throws IOException {
		return getPropValue("password");
	}
	
	public String getEndpoint() throws IOException {
		return getPropValue("endpoint");
	}
	
	public String getSessionId() throws IOException {
		return getPropValue("sessionId");
	}

	private String getPropValue(String param) throws IOException {
		Properties prop = getProperties();
		return prop.getProperty(param);

	}
	
	public String getFilePath() {
		File srFile = new File(getClass().getClassLoader().getResource("tooling-39.wsdl").getFile()); 
        
        return srFile.getPath();
	}
	
	private Properties getProperties() throws IOException {
		prop = new Properties();
		try {
			inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
 
			if (inputStream != null) {
				prop.load(inputStream);
			} else {
				throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
			}
 
		} catch (Exception e) {
			System.out.println("Exception: " + e);
		} finally {
			inputStream.close();
		}
		return prop;
	}
	
}