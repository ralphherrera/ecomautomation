package com.cukes.bean;

public class Step {

	private String action;
	private String inputValue;
	private String locatorString;
	private String locatorType;
	private int number;
	
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public String getInputValue() {
		return inputValue;
	}
	public void setInputValue(String inputValue) {
		this.inputValue = inputValue;
	}
	public String getLocatorString() {
		return locatorString;
	}
	public void setLocatorString(String locatorString) {
		this.locatorString = locatorString;
	}
	public String getLocatorType() {
		return locatorType;
	}
	public void setLocatorType(String locatorType) {
		this.locatorType = locatorType;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	
}
