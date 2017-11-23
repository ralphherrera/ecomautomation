package com.cukes.utils;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.cukes.bean.Gherkin;
import com.cukes.bean.Step;
import com.cukes.pageobjects.BasePage;

public class PerformAction extends BasePage{
	
	private List<Step> stepList;
	
	private static final Logger log = LogManager.getLogger(PerformAction.class);
	
	/***
	 * 
	 * @param scenarioName
	 * @param gherkinStatement
	 */
	public void doAction(String scenarioName, Gherkin gherkin) {
		log.entry();
		stepList = gherkin.getStepList();
		log.info("Executing Step: {}", gherkin.getName());
		
		for (Step step : stepList) {
			log.info("Executing Step #: {}", step.getNumber());
			WebElement element = getWebElement(step.getLocatorString(), step.getLocatorType());
			String action = step.getAction();
			String inputValue = step.getInputValue();
			CommonActionsUtil.executeAction(action, driverWrapper, element, inputValue);
		}
		log.exit();
	}
	
	/**
	 * Returns a corresponding element based on the given parameters
	 * @param locatorString
	 * @param locatorType
	 * @return
	 */
	private WebElement getWebElement(String locatorString, String locatorType) {
		log.entry();
		WebElement element = null;
		if (!(locatorString == null && locatorType == null)) {
			String test = locatorType.toLowerCase();
			log.info("RALPH {}", test);
			switch(test) {
			case "id" : return driver.findElement(By.id(locatorString));
			case "name" : return driver.findElement(By.name(locatorString));
			case "css" : return driver.findElement(By.cssSelector(locatorString));
			case "xpath" : return driver.findElement(By.xpath(locatorString));
			}
		} 
		log.exit();
		return element;
	}
}
