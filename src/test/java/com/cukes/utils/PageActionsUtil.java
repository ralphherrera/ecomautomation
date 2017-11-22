package com.cukes.utils;

import java.time.Duration;
import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.lang.reflect.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.cukes.bean.Step;
import com.cukes.constants.CommonConstants;



public class PageActionsUtil {
	
	private static Map<String, Method> methodMap = new HashMap<>();;

	private static final Logger log = LogManager.getLogger(PageActionsUtil.class);
	private static final String MSG_ERROR = "Unable to Complete Action: ";
	private static final String CB_CHECKER_PATH = "./ancestor::table[1]";
	private static final String PATH_LIST = "li[role='option']";
	private static final String ATTRIB_VALUE = "value";
	
	private PageActionsUtil() {
		throw new AssertionError();
	}
	
	/**
	 * 
	 * @param step
	 */
	public static void returnAction(String action, String inputValue) {
		log.entry();
		
		try {
			methodMap.get(action).invoke(inputValue);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		log.exit();
	}
	
	/**
	 * 
	 * @param driverWrapper
	 * @param url
	 */
	public static void navigateToPage(WebDriverWrapper driverWrapper, String url) {
		log.entry();
		driverWrapper.getUrl(url);
		log.exit();
	} 
	
	/**
	 * 
	 * @param step
	 * @param webElement
	 */
	public static void actionMapper(Step step, WebElement webElement) {
		log.entry();
		try {
			methodMap.put("click", PageActionsUtil.class.getMethod("clickButton", WebElement.class));
		} catch (NoSuchMethodException e) {
			log.error("No such method {}", e);
		}
		log.exit();
	}
	
	/**
     * Wait for Element to disappear on screen
	  * @param webElement - element to be waited to disappear
	  */
    public static void waitForElementToDisappear(WebElement webElement) {
        log.debug("Waiting for element to  disappear");
        Instant now = Instant.now();
        Duration duration = Duration.ZERO;
		do {
			try {
				if (!webElement.isDisplayed()) {
					break;
				}
			} catch (NoSuchElementException e) {
				log.error("Element is NOT displayed.", e);
			} catch (Exception e) {
				log.error("Something went wrong.", e);
			} finally {
				Instant end = Instant.now();
				CommonMgmtUtil.implementPollTime(CommonConstants.POLLING_TIME);
				duration = Duration.between(now, end);
			}
		} while (duration.toMillis()/1000 <= CommonConstants.ELEMENT_TIMEOUT);
        log.debug("Element is no longer shown");
    }
    
    /**
   	 * Description: Click the button as specified by the button param
   	 * @param driverWrapper - driver wrapper
   	 * @param target element to be clicked
   	 */
   	public static void clickButton(WebDriverWrapper driverWrapper, WebElement target) {
   		try {
   			if (driverWrapper.isElementPresent(target)) {
   				//check if button is enabled
   				if (target.isEnabled()) {
   					log.debug("Clicking button");
   					target.click();
   				} else {
   					log.error("Button is disabled");
   				}
   			} else {
   				log.warn("Button not found");
   			}
   		} catch (Exception e) {
   			log.error(MSG_ERROR, e);
   		}
   	}
   	
   	/**
	 * Description: Inputs return search based on the given criteria
	 * @param search - String value to be entered
	 * @param target - String value of text field where search will be entered.
	 * @param driverWrapper
	 * valid field values: email, phone, firstName, lastName, zip, quickAddItem, quickAddQty
	 */
	public static void inputValueInField(WebDriverWrapper driverWrapper, String search, WebElement target) {
		log.entry();
		try {
			//input text in the text field specified 
			if (driverWrapper.isElementPresent(target)) {
				if (target.isEnabled()) {
					target.clear();
					log.info("Enter [{}] in field", search);
					target.sendKeys(search);
				} else {
					log.error("Field is Disabled");
				}
			} else {
				log.error("Unable to locate field element");
			}
		} catch (Exception e) {
			log.error(MSG_ERROR, e);
		}
	}
	
	/**
	 * Description: clicks the radio button of the specified target
	 * @param target element to be clicked
	 */
	public static void clickRadioButton(WebDriverWrapper driverWrapper, WebElement target) {
		log.entry();
		try {
			driverWrapper.waitForElementToAppear(target);
			//enable the radio button by clicking on it
			if (driverWrapper.isElementPresent(target)) {
				target.click();
			} else {
				log.error("Unable to locate radio button");
			}
			//check if radio button is selected
			if (target.isSelected() || isCheckboxSelected(target)) {
				log.debug("Radio Button is selected");
			} else {
				log.debug("Radio Button is not selected");
			}
		} catch (Exception e) {
			log.error(MSG_ERROR, e);
		}
		log.exit();
	}
	
	/**
	 * Description: Utility that checks if the selected checkbox is checked based
	 * on the class attribute of the selected button.
	 * @param target - WebElement from the calling method
	 * @return true if check box is selected otherwise false
	 */
	private static boolean isCheckboxSelected(WebElement target) {
		log.entry();
		//findElement uses an xpath method due to axes usage
		try {
			WebElement checker = target.findElement(By.xpath(CB_CHECKER_PATH));
			log.exit();
			return checker.getAttribute("class").contains("checked");
		} catch (NoSuchElementException e) {
			log.warn(e);
			log.info("Not a checkbox");
			Assert.fail("Checkbox is not present.");
			return false;
		}
	}
	
	/**
	 * Description - Brings up the dropdown list of the combobox element specified
	 * then selects the value specified. This is created as the elements in this page cannot
	 * use the Select class.
	 * @param value - String value of the list from the combobox to be selected, should match the
	 * actual UI string value.
	 * @param targetTrig - element to be clicked
	 * @param driverWrapper - driver wrapper
	 */
	public static void selectFromCombobox(WebDriverWrapper driverWrapper, String value, WebElement targetTrig) {
		log.entry();
		try {
			driverWrapper.waitForElementToBeClickable(targetTrig);
			targetTrig.click();
			List<WebElement> options = driverWrapper.getDriver().findElements(By.cssSelector(PATH_LIST));
			for (WebElement option : options) {
				if (value.equalsIgnoreCase(option.getText())) {
					option.click();
					log.info("[{}] has been selected.", value);
					break;
				} else {
					log.error("[{}] has not been selected from combobox specified", option.getText());
				}
			}
		} catch (Exception e) {
			log.error("Unable to complete Action: ", e);
		}
		log.exit();
	}
	
	/**
	 * Checks if the radio button is enabled
	 * @param target - element to be checked if enabled
	 * @return True if radio button is enabled, False if radio button is disabled
	 */
	public static boolean isRadioBtnEnabled(WebElement target) {
		log.entry();
		try {
			if(target!=null && "true".equals(target.getAttribute("disabled"))){
				log.debug("[{}] fullfilment is disabled: [{}]", target, target.getAttribute("disabled"));
				log.debug("The fulfillment option is DISABLED");
				log.exit();		
				return false;
			} else {
				log.debug("[{}] fullfilment is disabled: false", target);
				log.debug("The fulfillment option is ENABLED");
				log.exit();
				return true;			
			}
		} catch (NoSuchElementException nse) {
			log.exit();
			Assert.fail("Radio button is not present.");
			return false;
		}
	}
	
	/**
	 * Returns value if the field is empty or not
	 * @param driverWrapper - driver wrapper
	 * @param target - element to be checked if populated
	 * @return - true if field is populated otherwise false
	 */
	public static boolean isInputFieldPopulated(WebDriverWrapper driverWrapper, WebElement target) {
		log.entry();
		if (target == null) {
			log.exit();
			return false;
		}
		if (driverWrapper.isElementPresent(target)) {
			if (!target.getAttribute(ATTRIB_VALUE).isEmpty()) {
				log.info("Field value [{}]", target.getAttribute(ATTRIB_VALUE));
				log.exit();
				return true;
			} else {
				log.info("Field [{}] is empty");
				log.exit();
				return false;
			}
		} else {
			log.error("Unable to locate field [{}]");
			log.exit();
			return false;
		}
	}
	
	/**
	 * Description - Brings up the dropdown list of each combobox element specified for each modal page
	 * then selects the value specified for each page. This is created as the elements in this page cannot
	 * use the Select class.
	 * @param value - String value of the list from the combobox to be selected, should match the
	 * actual UI string value.
	 * @param driverWrapper - driver wrapper
	 * @param startIndex - int value of starting index
	 * @param endIndex - int value of ending index
	 */
	public static void selectFromComboboxMultiLine(WebDriverWrapper driverWrapper, String value, int startIndex, int endIndex) {
		log.entry();
		try {
			List<WebElement> options = driverWrapper.getDriver().findElements(By.cssSelector(PATH_LIST));
			log.info("optionsSize: [{}]", options.size());
			List<WebElement> optionsSublist = options.subList(startIndex, endIndex);
			for (WebElement option : optionsSublist) {
				if (value.equalsIgnoreCase(option.getText())) {
					Actions action = new Actions(driverWrapper.getDriver());
					action.moveToElement(option).build().perform();
					option.click();
					log.info("[{}] has been selected.", value);
					break;
				} else {
					log.error("[{}] has not been selected from combobox specified", option.getText());
				}
			}
		} catch (Exception e) {
			log.error("Unable to complete Action: ", e);
		}
		log.exit();
	}
}
