package com.cukes.pageobjects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HeaderPage extends BasePage {
	
	private static final Logger log = LogManager.getLogger(HeaderPage.class);
	
	/* PAGE ELEMENTS */
	
	@FindBy(id = "search_query_top")
	public WebElement txt_itemSearch;
	
	@FindBy(name = "submit_search")
	public WebElement btn_itemSearch;
	
	
	public void openPage(String url) {
		log.entry();
		driverWrapper.getUrl(url);
		log.exit();
	}
}
