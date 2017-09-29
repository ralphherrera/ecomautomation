package com.cukes.pageobjects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.cukes.constants.InputFields;

public class HeaderPage extends BasePage {

	private static final Logger log = LogManager.getLogger(HeaderPage.class);

	/* PAGE ELEMENTS */

	@FindBy(id = "search_query_top")
	public WebElement txt_itemSearch;

	@FindBy(name = "submit_search")
	public WebElement btn_itemSearch;

	public HeaderPage() {
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
			target = txt_itemSearch;
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
		log.exit();
	}
}
