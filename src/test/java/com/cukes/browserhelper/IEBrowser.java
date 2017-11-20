package com.cukes.browserhelper;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import com.cukes.utils.PropertyUtil;

public class IEBrowser extends InternetExplorerDriver {
	
	public IEBrowser() {
		super();
	}

	public static WebDriver buildInternetExplorerBrowser(){
		System.setProperty("webdriver.chrome.driver", PropertyUtil.getConfigProp("local.ie.driver.path"));
		InternetExplorerDriver browser = new InternetExplorerDriver();
		return browser;
	}
}
