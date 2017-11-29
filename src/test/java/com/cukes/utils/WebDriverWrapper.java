package com.cukes.utils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.cucumber.listener.Reporter;
import com.cukes.constants.CommonConstants;

import cucumber.api.Scenario;

public class WebDriverWrapper {

	private static WebDriver driver;
	private static final String TEXT_COMPLETE = "complete";
	private static final Logger log = LogManager.getLogger(WebDriverWrapper.class);
	private final Scenario scenario;

	public WebDriverWrapper(WebDriver driver, Scenario scenario) {
		this.driver = driver;
		this.scenario = scenario;
	}

	public WebDriver getDriver() {
		return driver;
	}
	
	public Scenario getScenario() {
		return scenario;
	}

	/**
	 * Method to check if Element is present on page. Implements Fluent Wait on target
	 * for a period of time defined by WEBDRIVER_WAIT_TIMEOUT constant
	 * @param target - WebElement to be evaluated.
	 * @return true - if the element is present, <p> otherwise false
	 */
	public boolean isElementPresent(WebElement target) {
		CommonMgmtUtil.resetInterruptFlag();
		Instant now = Instant.now();
		do {
			try {
				if (target.isDisplayed()) {
					return true;
				}
			} catch (NoSuchElementException e) {
				log.error("Element is NOT displayed.", e);
			} catch (Exception e) {
				log.error("Something went wrong.", e);
			}
		} while (CommonMgmtUtil.pollThenCalculateDuration(now).toMillis()/1000 <= CommonConstants.ELEMENT_TIMEOUT);
		return false;
	}

	/**
	 * Method to check if Element is present on page. Implements Fluent Wait on target
	 * for a period of time defined by WEBDRIVER_WAIT_TIMEOUT constant
	 * @param target - WebElement to be evaluated.
	 * @param timeOut - int value of time out
	 * @return true - if the element is present, <p> otherwise false
	 */
	public boolean isElementPresent(WebElement target, int timeOut) {
		Instant now = Instant.now();
		do {
			try {
				if (target.isDisplayed()) {
					return true;
				}
			} catch (NoSuchElementException e) {
				log.error("Element is NOT displayed.", e);
			} catch (Exception e) {
				log.error("Something went wrong.", e);
			}
		} while (CommonMgmtUtil.pollThenCalculateDuration(now).toMillis()/1000 <= timeOut);
		return false;
	}

	/**
	 * Description: Wait for a target Element of Type WebElement
	 * @param target - WebElement to be evaluated
	 */
	public void waitForElementToAppear(WebElement target) {
		Instant now = Instant.now();
		do {
			try {
				if (target.isDisplayed()) {
					break;
				}
			} catch (NoSuchElementException e) {
				log.error("Element is NOT displayed.", e);
			} catch (Exception e) {
				log.error("Something went wrong.", e);
			}
		} while (CommonMgmtUtil.pollThenCalculateDuration(now).toMillis()/1000 <= CommonConstants.ELEMENT_TIMEOUT);
	}

	/**
	 * Description: Wait for a target Element of Type WebElement
	 * @param target - WebElement to be checked
	 * @param timeOut - Waiting time for element
	 */
	public void waitForElementToAppear(WebElement target, int timeOut) {
		Instant now = Instant.now();
		do {
			try {
				if (target.isDisplayed()) {
					break;
				}
			} catch (NoSuchElementException e) {
				log.error("Element is NOT displayed.", e);
			} catch (Exception e) {
				log.error("Something went wrong.", e);
			}
		} while (CommonMgmtUtil.pollThenCalculateDuration(now).toMillis()/1000 <= timeOut);
	}

	/**
	 * Method to check if Text is present on page. Implements Fluent Wait on target
	 * for a period of time defined by WEBDRIVER_WAIT_TIMEOUT constant
	 * @param element - WebElement to be evaluated.
	 * @param text - string value of text displayed
	 */
	public void waitForTextToAppear(WebElement element, String text) {
		Instant now = Instant.now();
		do {
			try {
				if (element.getText().equalsIgnoreCase(text)) {
					break;
				}
			} catch (NoSuchElementException e) {
				log.error("Unable to get the text. Element is NOT displayed.", e);
			} catch (Exception e) {
				log.error("Something went wrong.", e);
			}
		} while (CommonMgmtUtil.pollThenCalculateDuration(now).toMillis()/1000 <= CommonConstants.ELEMENT_TIMEOUT);
	}

	/**
	 * Method to check if Elements are found in page. Implements Fluent Wait on target
	 * for a period of time defined by WEBDRIVER_WAIT_TIMEOUT constant
	 * @param elements - WebElements to be evaluated.
	 */
	public void waitForElementsToAppear(List<WebElement> elements) {
		CommonMgmtUtil.resetInterruptFlag();
		Instant now = Instant.now();
		do {
			try {
				if (elements.isEmpty()) {
					break;
				}
			} catch (NoSuchElementException e) {
				log.error("Elements are NOT displayed.", e);
			} catch (Exception e) {
				log.error("Something went wrong.", e);
			}
		} while (CommonMgmtUtil.pollThenCalculateDuration(now).toMillis()/1000 <= CommonConstants.ELEMENT_TIMEOUT);
	}

	/**
	 * Method to wait for page to get loaded
	 * @return true if page is loaded within time specified <p> 
	 * the time can be modified in default.wait.for.page located at env-config folder <p>
	 * otherwise false if not loaded
	 */
	public boolean isPageLoaded() {
		Duration duration = Duration.ZERO;
		try {
			Instant now = Instant.now();
			do {
				if (((JavascriptExecutor) driver).executeScript(
						"return document.readyState").equals(TEXT_COMPLETE)) {
					return true;
				}
				duration = CommonMgmtUtil.pollThenCalculateDuration(now);
			} while (duration.toMillis() / 1000 <= CommonConstants.DEFAULT_WAIT_FOR_PAGE);
		} catch (Exception e) {
			log.error(
					"document.readyState status not completed after {} seconds: {}",
					duration.toMillis() / 1000, e);
		}
		return false;
	}

	/**
	 * Switches frame to default content then to a specific frame
	 * @param frame - iframe to switch into
	 */
	public void switchToFrame(WebElement frame) {
		driver.switchTo().defaultContent();
		log.info("Switching to frame : {}", frame);
		driver.switchTo().frame(frame);
	}

	/**
	 * Waits for the element to be clickable
	 * @param target - WebElement to be checked
	 */
	public void waitForElementToBeClickable(WebElement target) {
		log.entry();
		Instant now = Instant.now();
		do {
			try {
				if (isElementPresent(target) && target.isEnabled()) {
					break;
				}
			} catch (Exception e) {
				log.error("Element is NOT clickable.", e);
			}
		} while (CommonMgmtUtil.pollThenCalculateDuration(now).toMillis()/1000 <= CommonConstants.ELEMENT_TIMEOUT);
		log.exit();
	}

	/**
	 * Opens the webapp
	 * @param url - Url of the webapp to be tested
	 */
	public void getUrl(String url) {
		log.entry();
		try {
			if(driver != null) {
				log.info("Opening URL: {}", url);
				driver.get(url);
				isPageLoaded();
			} else {
				log.error("driver is null");
			}
		} catch (Exception e) {
			log.error("Something went wrong {}", e);
		}
		log.exit();
	}
	
	/**
     * Highlight Element
     * @param webElement
     */
	public static void highlightElement(WebElement webElement) {
		try {
			JavascriptExecutor js = (JavascriptExecutor)driver;
			js.executeScript("arguments[0].style.border='2px solid red'", webElement);
		} catch (Exception e) {
			log.error("Something went wrong {}", e);
		}
	}
	
	/**
     * Remove highlight element
     * @param webElement
     */
	public static void removeHighlightedElement(WebElement webElement) {
		try {
			JavascriptExecutor js = (JavascriptExecutor)driver;
			js.executeScript("arguments[0].style.border='none'", webElement);
		} catch (Exception e) {
			log.error("Something went wrong {}", e);
		}
	}
	
	/**
     * Embed screenshot with multiple highlight to the html report
     * @param scenario
     * @return String
     */
	public void embedScreenshotWithHighlight(WebElement element) {
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		String outPath = System.getProperty("user.dir") + "\\target\\screenshots\\" + scenario.getId().split(";")[0] + "\\" + scenario.getName().replaceAll("\\s", "_") + "_" + timeStamp + ".png";
		try {
			highlightElement(element);
			File scrFile = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(scrFile, new File(outPath).getAbsoluteFile());
			Reporter.addScreenCaptureFromPath(outPath);
			removeHighlightedElement(element);
        } catch (Throwable e) {
            e.printStackTrace();
        }
	}
}