package com.cukes.utils;

import java.time.Instant;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.cukes.bean.Config;
import com.cukes.bean.Gherkin;
import com.cukes.bean.Step;
import com.cukes.bean.TestScenario;

public class WebActionsUtil {

	private static final Logger log = LogManager.getLogger(WebActionsUtil.class);
	
	
	private WebActionsUtil() {
		throw new AssertionError();
	}

	/**
	 * Scroll until the specified element is visible on screen
	 * @param driver - driver
	 * @param webElement - element to be scrolled to
	 */
	public static void scrollIntoView(WebDriver driver, WebElement webElement) {
		try {
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].scrollIntoView();", webElement);
		} catch (Exception e) {
			log.error("JavascriptExecutor ScrollIntoView failed: ", e);
		}
	}

	/**
	 * Scroll until the specified element is visible on screen
	 * @param driver - driver
	 * @param elementBy - element to be visible
	 */
	public static void scrollIntoView(WebDriver driver, By elementBy) {
		try {
			WebElement objElement = driver.findElement(elementBy);
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].scrollIntoView();", objElement);
		} catch (Exception e) {
			log.error("JavascriptExecutor ScrollIntoView failed: ", e);
		}
	}

	/**
	 * Method to click an element using JavascriptExecutor
	 * @param driver - driver
	 * @param webElement - element to be clicked
	 */
	public static void jsClick(WebDriver driver, WebElement webElement) {
		try {
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", webElement);
		} catch (Exception e) {
			log.error("JavascriptExecutor Click failed: ", e);
		}
	}

	/**
	 * Method to double click on element using actions
	 * @param driver - driver
	 * @param webElement - element to be double clicked
	 */
	public static void doubleClickUsingAction(WebDriver driver, WebElement webElement) {
		try {
			Actions actions = new Actions(driver);
			actions.doubleClick(webElement).build().perform();
		} catch (Exception e) {
			log.error("Double click action failed: ", e);
		}
	}

	/**
	 * Method to highlight element
	 * @param driver - driver
	 * @param webElement - element to be higlighted
	 */
	public static void elementHighlight(WebDriver driver, WebElement webElement) {
		try {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].style.border='3px solid red'", webElement);

		} catch (Exception e) {
			log.error("Check element '{}': ", webElement, e);
		}
	}

	/**
	 * Method to remove highlight
	 * @param driver - driver
	 * @param webElement - element to remove highlight
	 */
	public static void removeHighlightElement(WebDriver driver, WebElement webElement) {
		try {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].style.border=''", webElement);
		} catch (Exception e) {
			log.error("Check element '{}': ", webElement, e);
		}
	}

	/**
 	 * Method to handle alert box
 	 * @param driver - driver
 	 */
 	public static void handleAlert(WebDriver driver) {
 		CommonMgmtUtil.resetInterruptFlag();
 		int alertTimeOut = 3;
		Instant now = Instant.now();
		do {
			try {
				Alert alert = driver.switchTo().alert();
 				log.debug("Alert Message: [{}]", alert.getText());
 				alert.accept();
 				// will execute this code if NoAlertPresentException is not thrown
 				now = Instant.now();
 				log.info("Clicked okay to close alert pop-up");
			} catch (NoAlertPresentException e) {
				log.error("Alert is NOT displayed", e);
			} catch (Exception e) {
				log.error("Error handling alert", e);
				Assert.fail("Exception encountered when trying to handle alert");
			}
		} while (WebActionsUtil.isAlertPresent(driver)
				&& CommonMgmtUtil.pollThenCalculateDuration(now).toMillis()/1000 <= alertTimeOut);
	}

	/**
	 * Check if alert pop-up is present
	 * @param driver  - driver
	 * @return true if alert pop-up is present
	 */
	public static boolean isAlertPresent(WebDriver driver) {

		try {
			driver.switchTo().alert(); 
			log.info("Alert pop-up is displayed");
			return true; 
		} catch (NoAlertPresentException e) {
			log.warn("No alert present! {}", e);
			return false; 
		}
	}
	
	/*
	// dummy main method used to test json deserializer
	 
	public static void main(String[] args) {
		Config conf = CommonMgmtUtil.getObjectScenario("data/static/amazon.json");
		List<TestScenario> tsList = conf.getTestScenarios();
		for (TestScenario ts : tsList) {
			System.out.println("ts name: " + ts.getName());
			System.out.println("gherkin: " + ts.getGherkin());
			System.out.println("isExecute: " + ts.isExecute());
			List<Gherkin> ghList = ts.getGherkin();
			for (Gherkin gh : ghList) {
				System.out.println("Gherkin Name: " + gh.getName());
				List<Step> steps = gh.getStepList();
				for (Step step : steps) {
					System.out.println("Step Number: " + step.getNumber());
				}
			}
		}
	}
	*/
	
}
