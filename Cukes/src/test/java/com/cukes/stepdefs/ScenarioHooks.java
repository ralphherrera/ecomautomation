package com.cukes.stepdefs;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

import com.cukes.utils.WebDriverWrapper;

import cucumber.api.Scenario;
import cucumber.api.java8.En;

public class ScenarioHooks implements En {

	private WebDriverWrapper driverWrapper;
	private long startTime;
	private static final Logger log = LogManager.getLogger(ScenarioHooks.class);

	public ScenarioHooks() {

		Before(new String[] {"@scenarios"},(Scenario scenario) -> {
			if (driverWrapper == null) { 
				try {
					WebDriver driver = BrowserFactory.getBrowser(scenario);
					driver.manage().deleteAllCookies();
					driver.manage().window().maximize();
					startTime = System.currentTimeMillis();
					driverWrapper = new WebDriverWrapper(driver);
				} catch (Exception e) {
					log.error("WebDriver initialization failed :: {}", e);
				}
			}
		});
	}
}
