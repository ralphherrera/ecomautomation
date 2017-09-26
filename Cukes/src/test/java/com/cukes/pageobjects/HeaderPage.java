package com.cukes.pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HeaderPage {
	
	
	/* PAGE ELEMENTS */
	
	@FindBy(id = "search_query_top")
	public WebElement txt_itemSearch;
	
	@FindBy(name = "submit_search")
	public WebElement btn_itemSearch;
}
