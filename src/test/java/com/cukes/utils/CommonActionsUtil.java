package com.cukes.utils;

import java.util.Iterator;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class CommonActionsUtil {
	
	private static final Logger log = LogManager.getLogger(CommonActionsUtil.class);

	/**
	 * Private constructor
	 */
	private CommonActionsUtil(){}
	
	public static boolean executeAction(String action, WebDriverWrapper driverWrapper, WebElement element, String inputValue) {
		log.entry();
		
		switch (action) {
		case "navigateToPage":
			return navigateToPage(driverWrapper, inputValue);
		case "findText":
			return isFieldValueExact(driverWrapper, element, inputValue);
		case "click":
			return clickButton(driverWrapper, element);
		case "sendKeys":
			return inputValueInField(driverWrapper, element, inputValue);
		case "getAltText":
			return getAltText(driverWrapper, element, inputValue);
		case "selectRadioButton":
			return selectRadioButton(driverWrapper, element);
		case "selectValueDropdown":
			return selectOptionOnDropdown(driverWrapper, element, inputValue);
		case "getValueText":
			return getValueText(driverWrapper, element, inputValue);
		default:
			return false;
		}
	}
	
	/**
	 * 
	 * @param driverWrapper
	 * @param url
	 */
	public static boolean navigateToPage(WebDriverWrapper driverWrapper, String url) {
		log.entry();
		try {
			driverWrapper.getUrl(url);
			return true;
		} catch (Exception e) {
			log.error ("Unable to navigate to url: ", e);
			return false;
		}
	} 

    /**
   	 * Description: Click the button as specified by the button param
   	 * @param driverWrapper - driver wrapper
   	 * @param target element to be clicked
   	 */
   	public static boolean clickButton(WebDriverWrapper driverWrapper, WebElement target) {
   		try {
   			if (driverWrapper.isElementPresent(target)) {
   				//check if button is enabled
   				if (target.isEnabled()) {
   					log.debug("Clicking button");
   					driverWrapper.embedScreenshotWithHighlight(target);
   					target.click();
   					return true;
   				} else {
   					log.error("Button is disabled");
   					return false;
   				}
   			} else {
   				log.warn("Button not found");
   				return false;
   			}
   		} catch (Exception e) {
   			log.info("Calling Exception Method in click button");
   			embedScreenshotOnError(driverWrapper, target, e);
   			return false;
   		}
   	}
   	
    /**
     * Description: Method to select value for dropdown list
     * @param webElement
     * @param label
     */
    public static void selectValue(WebElement webElement, String valueToBeChosen) {
    	log.info("Select by visible text: {}", valueToBeChosen);
    	try {
    		Select actualSelect = new Select(webElement);
    		actualSelect.selectByVisibleText(valueToBeChosen);
    	} catch (Exception e) {
    		//TODO: insert proper statement here
    		log.error("Unable to select value from dropdown.", e);
    	}
    }
   	
    /**
     * Description: Method to select and compare value for dropdown list
     * @param valueToBeChosen
     * @param element
     */
    public static boolean selectOptionOnDropdown(WebDriverWrapper driverWrapper, WebElement webElement, String valueToBeChosen) {
        log.entry();
        log.info("Select : {}", valueToBeChosen);
        String value;
        try {
        	Select select = new Select(webElement);
        	List<WebElement> options = select.getOptions();
        	Iterator<WebElement> optionsCount = options.iterator();
        	while (optionsCount.hasNext()) {
        		value = optionsCount.next().getText().trim();
        		if (value.equals(valueToBeChosen.trim())) {
        			selectValue(webElement, value);
        			driverWrapper.embedScreenshotWithHighlight(webElement);
        			log.info("{} is selected", valueToBeChosen);
        			log.exit();
        			return true;
        		}
        	}
        } catch (Exception e) {
        	log.warn("Option to be selected is not available!");
        	embedScreenshotOnError(driverWrapper, webElement, e);
        	log.exit();
        }
        return false;

    }
    
    /**
	 * Description: Verify the exact value of the element.
	 * @param element
	 * @param expectedValue
	 * @return boolean
	 */
	public static boolean isFieldValueExact(WebDriverWrapper driverWrapper, WebElement webElement, String expectedValue) {
		log.entry();
		log.info("Verifying Text");
		try {
			String actual = webElement.getText();
			log.info("Expected {}", expectedValue);
			log.info("Actual {}", actual);
			driverWrapper.embedScreenshotWithHighlight(webElement);
			if (containsIgnoreCase(driverWrapper, actual, expectedValue)) {
				log.info("{} matches the expected {} value.", actual, expectedValue);
				return true;
			} else {
				log.info("{} value is NOT equal to the expected {} value.", actual, expectedValue);
				return false;
			}
		} catch (Exception e) {
			log.info("Unable to verify text: ", e);
   			driverWrapper.embedScreenshotWithHighlight(null);
		}
		return false;
	}
	
   	/**
	 * Description: Inputs value in fields
	 * @param inputValue - String value to be entered
	 * @param webElement - String value of text field where search will be entered.
	 * @param driverWrapper
	 */
	public static boolean inputValueInField(WebDriverWrapper driverWrapper, WebElement webElement, String inputValue) {
		log.entry();
		try {
			//input text in the text field specified 
			if (driverWrapper.isElementPresent(webElement)) {
				if (webElement.isEnabled()) {
					webElement.clear();
					log.info("Enter [{}] in field", inputValue);
					webElement.sendKeys(inputValue);
					driverWrapper.embedScreenshotWithHighlight(webElement);
					return true;
				} else {
					log.error("Field is Disabled");
					return false;
				}
			} else {
				log.error("Unable to locate field element");
				return false;
			}
		} catch (Exception e) {
			embedScreenshotOnError(driverWrapper, webElement, e);
			return false;
		}
	}
	
	 /**
   	 * Description: Tick the checkbox
   	 * @param driverWrapper - driver wrapper
   	 * @param target element to be clicked
   	 */
   	public static boolean tickCheckbox(WebDriverWrapper driverWrapper, WebElement webElement) {
   		try {
   			if (driverWrapper.isElementPresent(webElement)) {
   				webElement.click();
   				//check if Checkbox is selected
   				if (webElement.isSelected()) {
   					driverWrapper.embedScreenshotWithHighlight(webElement);
   					log.debug("Checkbox is selected");
   					return true;
   				} else {
   					log.error("Checkbox is not selected");
   					return false;
   				}
   			} else {
   				log.warn("Checkbox not found");
   				return false;
   			}
   		} catch (Exception e) {
   			embedScreenshotOnError(driverWrapper, webElement, e);
   			return false;
   		}
   	}
   	
    /**
   	 * Description: Select radio button
   	 * @param driverWrapper - driver wrapper
   	 * @param target element to be clicked
   	 */
   	public static boolean selectRadioButton(WebDriverWrapper driverWrapper, WebElement webElement) {
   		try {
   			if (driverWrapper.isElementPresent(webElement)) {
   				log.debug("Select Radio button");
   				webElement.click();
   				//check if Radio button is enabled
   				if (webElement.isEnabled() || webElement.isSelected()) {
   					driverWrapper.embedScreenshotWithHighlight(webElement);
   					log.debug("Radio Button is selected.");
   					return true;
   				} else {
   					log.error("Radio button is not selected");
   					return false;
   				}
   			} else {
   				log.warn("Radio button is not found");
   				return false;
   			}
   		} catch (Exception e) {
   			embedScreenshotOnError(driverWrapper, webElement, e);
   			return false;
   		}
   	}
   	
    /**
   	 * Description: Get text value of attribute "alt"
   	 * @param driverWrapper - driver wrapper
   	 * @param webElement element to be clicked
   	 * @param inputValue
   	 */
   	public static boolean getAltText(WebDriverWrapper driverWrapper, WebElement webElement, String inputValue) {
   		try {
   			if(driverWrapper.isElementPresent(webElement)) {
   				String altText = webElement.getAttribute("alt");
   				if(containsIgnoreCase(driverWrapper, altText, inputValue)) {
   					driverWrapper.embedScreenshotWithHighlight(webElement);
   					log.debug("Image found.");
   					return true;
   				}else {
   					log.error("Image not found.");
   					return false;
   				}
   			}else {
   				log.error("Element not found.");
   				return false;
   			}
   		} catch (Exception e) {
   			embedScreenshotOnError(driverWrapper, webElement, e);
   			return false;
   		}
   	}
   	
    /**
   	 * Description: Get text value of attribute "value"
   	 * @param driverWrapper - driver wrapper
   	 * @param webElement element to be clicked
   	 * @param inputValue
   	 */
   	public static boolean getValueText(WebDriverWrapper driverWrapper, WebElement webElement, String inputValue) {
   		try {
   			if(driverWrapper.isElementPresent(webElement)) {
   				String valueText = webElement.getAttribute("value");
   				if(containsIgnoreCase(driverWrapper, valueText, inputValue)) {
   					driverWrapper.embedScreenshotWithHighlight(webElement);
   					log.debug("Text found.");
   					return true;
   				}else {
   					log.error("Text not found.");
   					return false;
   				}
   			}else {
   				log.error("Element not found.");
   				return false;
   			}
   		} catch (Exception e) {
   			log.info("Unable to verify text: ", e);
   			driverWrapper.embedScreenshotWithHighlight(null);
   			return false;
   		}
   	}
   	
   	/**
   	 * Take a screenshot when automation encounters an error
   	 * @param driverWrapper
   	 * @param webElement
   	 */
   	private static void embedScreenshotOnError(WebDriverWrapper driverWrapper, WebElement webElement, Exception e) {
   		log.info("Unable to Complete Action: ", e);
   		driverWrapper.embedScreenshotWithHighlight(webElement);
   	}
   	
   	/**
   	 * Compare strings if the first value contains second value
   	 * @param driverWrapper
   	 * @param actualValue
   	 * @param inputValue
   	 * @return
   	 */
   	private static boolean containsIgnoreCase(WebDriverWrapper driverWrapper, String actualValue, String inputValue) {
   		log.entry();
   		try {
			String actualValueLowerCase = actualValue.toLowerCase().replaceAll("[^\\w\\d]", "");
			String inputValueLowerCase = inputValue.toLowerCase().replaceAll("[^\\w\\d]", "");
			
			if(actualValueLowerCase.contains(inputValueLowerCase)) {
				log.info("{} matches the expected {} value.", actualValueLowerCase, inputValueLowerCase);
				return true;
			} else {
				log.info("{} value is NOT equal to the expected {} value.", actualValueLowerCase, inputValueLowerCase);
				return false;
			}
		} catch (Exception e) {
			log.info("Unable to verify text: ", e);
		}
   		return false;
   	}

}
