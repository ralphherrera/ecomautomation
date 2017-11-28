package com.cukes.browserhelper;

import java.net.MalformedURLException;
import java.net.URL;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

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
			} else if("Grid".equalsIgnoreCase(EXECUTION_MODE)) {
				DesiredCapabilities capabilities = null;
				
				switch (desiredBrowser) {
				case "internet explorer":
					capabilities = DesiredCapabilities.internetExplorer();
					break;
				case "Chrome":
					capabilities = DesiredCapabilities.chrome();
					break;
				case "FireFox":
					capabilities = DesiredCapabilities.firefox();
				default: driver = null;
					// work out what to do when a browser isn't needed
					break;
				}
				
	        	try {
					driver = new RemoteWebDriver(new URL("http://172.16.220.108:3023/wd/hub"), capabilities);
				} catch (MalformedURLException e) {
					e.printStackTrace();
				}
			}
		} catch (Exception e) {
			log.error("Execution mode is null {}", e);
		}
		return driver;
	}
}
