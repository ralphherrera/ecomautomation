package com.cukes.utils;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class PropertyUtil {
	
	private static Properties prop = null;
	private static final Logger log = LogManager.getLogger(PropertyUtil.class);

	/*
	 * Private constructor
	 */
	private PropertyUtil() {
	}

	public static String getConfigProp(String propKey) {
		String propertyValue = null;
		try (FileInputStream inputStream = new FileInputStream(new File("src/test/resources/config.properties"))) {
			prop = new Properties();
			prop.load(inputStream);
			propertyValue = prop.getProperty(propKey);
		} catch (Exception e) {
			log.error("Cannot get property file {}", e);
		}
		return propertyValue;
	}
	
	public static String getTestDataProp(String propKey) {
		String propertyValue = null;
		try (FileInputStream inputStream = new FileInputStream(new File("src/test/resources/data/static/config.properties"))) {
			prop = new Properties();
			prop.load(inputStream);
			propertyValue = prop.getProperty(propKey);
		} catch (Exception e) {
			log.error("Cannot get property file {}", e);
		}
		return propertyValue;
	}
}
