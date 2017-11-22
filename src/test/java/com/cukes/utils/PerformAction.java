package com.cukes.utils;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.cukes.bean.Gherkin;
import com.cukes.bean.Step;
import com.cukes.pageobjects.BasePage;

import cucumber.api.Scenario;

public class PerformAction extends BasePage{
	
	private Scenario scenario;
	private List<Step> stepList;
	private ArrayList<Gherkin> gherkinList;
	
	private static final Logger log = LogManager.getLogger(PerformAction.class);
	
	/***
	 * 
	 * @param scenarioName
	 * @param gherkinStatement
	 */
	public void doAction(String scenarioName, String gherkinStatement) {
		for (Gherkin gherkin : gherkinList) {
			if (scenario.getName().equals(scenarioName) && gherkin.getName().equals(gherkinStatement)) {
				stepList = gherkin.getStepList();
				for (Step step : stepList) {
					WebElement element = getWebElement(step.getLocatorString(), step.getLocatorType());
					String action = step.getAction();
					String inputValue = step.getInputValue();
					PageActionsUtil.returnAction(action, element, inputValue);
				}
			}else {
				log.error("Scenario / Gherkin does not match");
			}
		}
	}
	
	/**
	 * Returns a corresponding element based on the given parameters
	 * @param locatorString
	 * @param locatorType
	 * @return
	 */
	private WebElement getWebElement(String locatorString, String locatorType) {
		WebElement element = null;
		switch(locatorType.toLowerCase()) {
		case "id" : return driver.findElement(By.id(locatorString));
		case "name" : return driver.findElement(By.name(locatorString));
		case "css" : return driver.findElement(By.cssSelector(locatorString));
		case "xpath" : return driver.findElement(By.xpath(locatorString));
		}
		return element;
	}
}
