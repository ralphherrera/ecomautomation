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
	
	public static void executeAction(String action, WebDriverWrapper driverWrapper, WebElement element, String inputValue) {
		log.entry();
		
		switch (action) {
		case "navigateToPage":
			navigateToPage(driverWrapper, inputValue);
			break;
		case "findText":
			isFieldValueExact(driverWrapper, element, inputValue);
			break;
		case "click":
			clickButton(driverWrapper, element);
			break;
		case "sendKeys":
			inputValueInField(driverWrapper, element, inputValue);
			break;
		case "getAltText":
			getAltText(driverWrapper, element, inputValue);
			break;
		case "selectRadioButton":
			selectRadioButton(driverWrapper, element);
			break;
		case "selectValueDropdown":
			selectOptionOnDropdown(driverWrapper, element, inputValue);
			break;
		case "getValueText":
			getValueText(driverWrapper, element, inputValue);
		default:
			break;
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
   					driverWrapper.embedScreenshotWithHighlight(target);
   					target.click();
   				} else {
   					log.error("Button is disabled");
   				}
   			} else {
   				log.warn("Button not found");
   			}
   		} catch (Exception e) {
   			embedScreenshotOnError(driverWrapper, target, e);
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
//    		embedScreenshotOnError(driverWrapper, target, e);
    	}
    }
   	
    /**
     * Description: Method to select and compare value for dropdown list
     * @param valueToBeChosen
     * @param element
     */
    public static void selectOptionOnDropdown(WebDriverWrapper driverWrapper, WebElement webElement, String valueToBeChosen) {
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
        			return;
        		}
        	}
        } catch (Exception e) {
        	log.warn("Option to be selected is not available!");
        	embedScreenshotOnError(driverWrapper, webElement, e);
        	log.exit();
        }

    }
    
    /**
	 * Description: Verify the exact value of the element.
	 * @param element
	 * @param expectedValue
	 * @return boolean
	 */
	public static boolean isFieldValueExact(WebDriverWrapper driverWrapper, WebElement webElement, String expectedValue) {
		log.entry();
		log.info("Verify the value");
		try {
			String actual = webElement.getText();
			log.info("Expected {}", expectedValue);
			log.info("Actual {}", actual);
			if (actual.equalsIgnoreCase(expectedValue)) {
				driverWrapper.embedScreenshotWithHighlight(webElement);
				log.info("{} matches the expected {} value.", actual, expectedValue);
				return true;
			} else {
				log.info("{} value is NOT equal to the expected {} value.", actual, expectedValue);
				return false;
			}
		} catch (Exception e) {
			embedScreenshotOnError(driverWrapper, webElement, e);
		}
		return false;
	}
	
   	/**
	 * Description: Inputs value in fields
	 * @param inputValue - String value to be entered
	 * @param webElement - String value of text field where search will be entered.
	 * @param driverWrapper
	 */
	public static void inputValueInField(WebDriverWrapper driverWrapper, WebElement webElement, String inputValue) {
		log.entry();
		try {
			//input text in the text field specified 
			if (driverWrapper.isElementPresent(webElement)) {
				if (webElement.isEnabled()) {
					webElement.clear();
					log.info("Enter [{}] in field", inputValue);
					webElement.sendKeys(inputValue);
					driverWrapper.embedScreenshotWithHighlight(webElement);
				} else {
					log.error("Field is Disabled");
				}
			} else {
				log.error("Unable to locate field element");
			}
		} catch (Exception e) {
			embedScreenshotOnError(driverWrapper, webElement, e);
		}
	}
	
	 /**
   	 * Description: Tick the checkbox
   	 * @param driverWrapper - driver wrapper
   	 * @param target element to be clicked
   	 */
   	public static void tickCheckbox(WebDriverWrapper driverWrapper, WebElement webElement) {
   		try {
   			if (driverWrapper.isElementPresent(webElement)) {
   				webElement.click();
   				//check if Checkbox is selected
   				if (webElement.isSelected()) {
   					driverWrapper.embedScreenshotWithHighlight(webElement);
   					log.debug("Checkbox is selected");
   				} else {
   					log.error("Checkbox is not selected");
   				}
   			} else {
   				log.warn("Checkbox not found");
   			}
   		} catch (Exception e) {
   			embedScreenshotOnError(driverWrapper, webElement, e);
   		}
   	}
   	
    /**
   	 * Description: Select radio button
   	 * @param driverWrapper - driver wrapper
   	 * @param target element to be clicked
   	 */
   	public static void selectRadioButton(WebDriverWrapper driverWrapper, WebElement webElement) {
   		try {
   			if (driverWrapper.isElementPresent(webElement)) {
   				log.debug("Select Radio button");
   				webElement.click();
   				//check if Radio button is enabled
   				if (webElement.isEnabled() || webElement.isSelected()) {
   					driverWrapper.embedScreenshotWithHighlight(webElement);
   					log.debug("Radio Button is selected.");
   				} else {
   					log.error("Radio button is not selected");
   				}
   			} else {
   				log.warn("Radio button is not found");
   			}
   		} catch (Exception e) {
   			embedScreenshotOnError(driverWrapper, webElement, e);
   		}
   	}
   	
    /**
   	 * Description: Get text value of attribute "alt"
   	 * @param driverWrapper - driver wrapper
   	 * @param webElement element to be clicked
   	 * @param inputValue
   	 */
   	public static void getAltText(WebDriverWrapper driverWrapper, WebElement webElement, String inputValue) {
   		try {
   			if(driverWrapper.isElementPresent(webElement)) {
   				String altText = webElement.getAttribute("alt");
   				if(altText.equalsIgnoreCase(inputValue)) {
   					driverWrapper.embedScreenshotWithHighlight(webElement);
   					log.debug("Image found.");
   				}else {
   					log.error("Image not found.");
   				}
   			}else {
   				log.error("Element not found.");
   			}
   		} catch (Exception e) {
   			embedScreenshotOnError(driverWrapper, webElement, e);
   		}
   	}
   	
    /**
   	 * Description: Get text value of attribute "value"
   	 * @param driverWrapper - driver wrapper
   	 * @param webElement element to be clicked
   	 * @param inputValue
   	 */
   	public static void getValueText(WebDriverWrapper driverWrapper, WebElement webElement, String inputValue) {
   		try {
   			if(driverWrapper.isElementPresent(webElement)) {
   				String altText = webElement.getAttribute("value");
   				if(altText.equalsIgnoreCase(inputValue)) {
   					driverWrapper.embedScreenshotWithHighlight(webElement);
   					log.debug("Text found.");
   				}else {
   					log.error("Text not found.");
   				}
   			}else {
   				log.error("Element not found.");
   			}
   		} catch (Exception e) {
   			embedScreenshotOnError(driverWrapper, webElement, e);
   		}
   	}
   	
   	/**
   	 * Take a screenshot when automation encounters an error
   	 * @param driverWrapper
   	 * @param webElement
   	 */
   	private static void embedScreenshotOnError(WebDriverWrapper driverWrapper, WebElement webElement, Exception e) {
   		driverWrapper.embedScreenshotWithHighlight(webElement);
   		log.error("Unable to Complete Action: ", e);
   	}
}
