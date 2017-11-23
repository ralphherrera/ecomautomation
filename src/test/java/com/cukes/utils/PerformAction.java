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
		WebElement element;
		for (Step step : stepList) {
			String action = step.getAction();
			String inputValue = step.getInputValue();

			if ("clickRadioButton".equalsIgnoreCase(action)) {
				element = getWebElementFromList(step.getLocatorString(), step.getLocatorType(), inputValue);
			} else {
				element = getWebElement(step.getLocatorString(), step.getLocatorType());
			}
			log.info("Executing Step #: {} : {}", step.getNumber(), action);
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
			String locType = locatorType.toLowerCase();
			switch(locType) {
			case "id" : return driver.findElement(By.id(locatorString));
			case "name" : return driver.findElement(By.name(locatorString));
			case "css" : return driver.findElement(By.cssSelector(locatorString));
			case "xpath" : return driver.findElement(By.xpath(locatorString));
			}
		} 
		log.exit();
		return element;
	}

	/**
	 * Returns a corresponding element based on the given parameters
	 * @param locatorString
	 * @param locatorType
	 * @param inputValue
	 * @return
	 */
	private WebElement getWebElementFromList(String locatorString, String locatorType, String inputValue) {
		log.entry();
		List<WebElement> elementList = getWebElementList(locatorString, locatorType);

		for (WebElement element : elementList) {
			if (element.getAttribute("value").equals(inputValue)) {
				log.exit();
				return element;
			}
		}
		log.exit();
		return null;
	}

	private List<WebElement> getWebElementList(String locatorString, String locatorType) {
		log.entry();
		String locType = locatorType.toLowerCase();
		switch(locType) {
		case "id" : return driver.findElements(By.id(locatorString));
		case "name" : return driver.findElements(By.name(locatorString));
		case "css" : return driver.findElements(By.cssSelector(locatorString));
		case "xpath" : return driver.findElements(By.xpath(locatorString));
		}
		log.exit();
		return null;
	}
}
