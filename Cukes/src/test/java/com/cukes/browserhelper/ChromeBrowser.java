package com.cukes.browserhelper;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.cukes.utils.PropertyUtil;


public class ChromeBrowser extends ChromeDriver {

	private ChromeBrowser() {
		super();
	}

	public static WebDriver buildChromeBrowser(){
		System.setProperty("webdriver.chrome.driver", PropertyUtil.getConfigProp("local.chrome.driver.path"));
		ChromeBrowser browser = new ChromeBrowser();
		return browser;
	}
}
