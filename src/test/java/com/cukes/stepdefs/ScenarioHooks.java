package com.cukes.stepdefs;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

import com.cukes.bean.TestScenario;
import com.cukes.browserhelper.BrowserFactory;
import com.cukes.utils.CommonMgmtUtil;
import com.cukes.utils.WebDriverWrapper;

import cucumber.api.Scenario;
import cucumber.api.java8.En;

public class ScenarioHooks implements En {

	private WebDriverWrapper driverWrapper;
	private static final Logger log = LogManager.getLogger(ScenarioHooks.class);

	public ScenarioHooks() {
		Before(new String[] {"@web"},(Scenario scenario) -> {
			System.out.println("Test Scenario Name: " + scenario.getName());
			TestScenario ts = CommonMgmtUtil.getObjectScenario("amazon.json");
			if (driverWrapper == null) { 
				log.info("Starting Scenario: " + scenario.getName());
				try {
					WebDriver driver = BrowserFactory.getWebDriver(scenario);
					driver.manage().deleteAllCookies();
					driver.manage().window().maximize();
					driverWrapper = new WebDriverWrapper(driver);
				} catch (Exception e) {
					log.error("WebDriver initialization failed :: {}", e);
				}
			}
		});

		After(new String[] {"@web"},(Scenario scenario) -> {
			try {
				driverWrapper.getDriver().quit();
				driverWrapper = null;
				log.info("Ending Scenario: " + scenario.getName());
			} catch (Exception e2) {
				log.error("Cannot close WebDriver instance: {}", e2);
			}
		});
	}

	public WebDriverWrapper getDriver(){
		return driverWrapper;
	}
}
