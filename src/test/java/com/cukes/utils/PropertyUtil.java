package com.cukes.utils;

import java.io.File;

import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class PropertyUtil {
	
	private static PropertiesConfiguration prop = null;
	private static final Logger log = LogManager.getLogger(PropertyUtil.class);

	/*
	 * Private constructor
	 */
	private PropertyUtil() {
	}

	public static String getConfigProp(String propKey) {
		String propertyValue = null;
		try {
			File file = new File("src/test/resources/config.properties");
			String propsFileName = file.getAbsolutePath();
			prop = new PropertiesConfiguration(propsFileName);
			propertyValue = prop.getProperty(propKey).toString();
		} catch (Exception e) {
			log.error("Cannot get property file {}", e);
		}
		
		return propertyValue;
	}
	
	public static String getTestDataProp(String propKey) {
		String propertyValue = null;
		try {
			File file = new File("src/test/resources/data/static/test-data.properties");
			String propsFileName = file.getAbsolutePath();
			prop = new PropertiesConfiguration(propsFileName);
			propertyValue = prop.getProperty(propKey).toString();
		} catch (Exception e) {
			log.error("Cannot get property file {}", e);
		}
		return propertyValue;
	}
	
}
