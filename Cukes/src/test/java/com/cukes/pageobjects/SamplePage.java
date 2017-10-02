package com.cukes.pageobjects;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.cukes.constants.Buttons;
import com.cukes.constants.InputFields;
import com.cukes.utils.CommonMgmtUtil;
import com.cukes.utils.PageActionsUtil;

public class SamplePage extends BasePage {

	private static final Logger log = LogManager.getLogger(SamplePage.class);

	/* PAGE ELEMENTS */

	@FindBy(id = "search_query_top")
	public WebElement txt_ItemSearch;

	@FindBy(name = "submit_search")
	public WebElement btn_ItemSearch;
	
	@FindBy(css = "ul[class*='product_list grid row'] div[class*='product-container'] a[class*='product-name']")
	private List<WebElement> lbl_ItemName;
	
	@FindBy(className = "heading-counter")
	public WebElement lbl_ResultCounter;

	public SamplePage() {
		// Default Constructor
	}

	/* PAGE METHODS */

	/**
	 * Initializes the WebDriver instance and opens the webapp
	 * @param url - URL of the webapp
	 */
	public void openPage(String url) {
		log.entry();
		driverWrapper.getUrl(url);
		log.exit();
	}

	/***
	 * Returns a WebElement based on the given criteria
	 * @param criteria - which search type should be used
	 * @return target - Page Element to be return
	 */
	private WebElement returnInputField(InputFields criteria) {
		WebElement target;
		switch(criteria) {
		case SEARCH: 
			target = txt_ItemSearch;
			break;
		default: target = null;
		}
		return target;
	}
	
	/***
	 * Returns a WebElement based on the given criteria
	 * @param criteria - which search type should be used
	 * @return target - Page Element to be return
	 */
	private WebElement returnButton(Buttons button) {
		WebElement target;
		switch(button) {
		case SEARCH: 
			target = btn_ItemSearch;
			break;
		default: target = null;
		}
		return target;
	}
	
	/**
	 * Inputs return search based on the given criteria
	 * @param search - String value to be entered
	 * @param field - String value of text field where search will be entered.
	 */
	public void inputValueInField(String search, InputFields field) {
		log.entry();
		WebElement target = returnInputField(field);
		PageActionsUtil.inputValueInField(driverWrapper, search, target);
		PageActionsUtil.isInputFieldPopulated(driverWrapper, target);
		log.exit();
	}
	
	
	/**
	 * Click the button as specified by the button param
	 * @param button - button type
	 */
	public void clickButton(Buttons button) {
		log.entry();
		WebElement target = returnButton(button);
		PageActionsUtil.clickButton(driverWrapper, target);
		log.exit();
	}
	
	/**
	 * Verify if there are item displayed in the Search Results section of the page
	 * @return
	 */
	public boolean isSearchResultsDisplayed() {
		log.entry();
		String[] resultCounterSplit = CommonMgmtUtil.splitString(lbl_ResultCounter.getText(), "\\s+");
		
		if(!"0".contains(resultCounterSplit[0]) && lbl_ItemName.size() > 1) {
			log.info("{} Search results found", lbl_ItemName.size());
			log.exit();
			return true;
		}
		log.info("{} Search results found", lbl_ItemName.size());
		log.exit();
		return false;
	}
}
