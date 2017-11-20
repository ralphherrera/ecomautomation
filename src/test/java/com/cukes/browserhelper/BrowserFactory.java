package com.cukes.browserhelper;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

import com.cukes.utils.PropertyUtil;

import cucumber.api.Scenario;

public class BrowserFactory {
	
	private static final String EXECUTION_MODE = PropertyUtil.getConfigProp("execution.mode");
	private static final Logger log = LogManager.getLogger(BrowserFactory.class);
	
	
	public BrowserFactory() {
		// Default Constructor
	}
	
	public static WebDriver getWebDriver(Scenario scenario) {
		String desiredBrowser = PropertyUtil.getConfigProp("capabilities.browserName");
		WebDriver driver = null;
		try {
			if("Local".equalsIgnoreCase(EXECUTION_MODE)) {
				switch (desiredBrowser) {
				case "internet explorer":
//                 driver = IEBrowser.buildIEBrowser();
					break;
				case "Chrome":
					driver = ChromeBrowser.buildChromeBrowser();
					break;
				case "FireFox":
//                 driver = FirefoxBrowser.buildFirefoxBrowser();
					break;
				default: driver = null;
					// work out what to do when a browser isn't needed
					break;
				}
			}
		} catch (Exception e) {
			log.error("Execution mode is null {}", e);
		}
		return driver;
	}
}
