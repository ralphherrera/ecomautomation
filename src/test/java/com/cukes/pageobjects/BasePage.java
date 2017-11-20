package com.cukes.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import com.cukes.utils.WebDriverWrapper;

public class BasePage {

	protected WebDriver driver;
	protected WebDriverWrapper driverWrapper;
	
	
	public BasePage() {
		// Default Constructor
	}
	
	public void setDriver(WebDriverWrapper driverWrapper) {
		this.driverWrapper = driverWrapper;
		this.driver = driverWrapper.getDriver();
		PageFactory.initElements(driver, this);
	}
	
}
