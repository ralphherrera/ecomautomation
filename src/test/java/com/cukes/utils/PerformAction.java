package com.cukes.utils;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;

import com.cukes.bean.Gherkin;
import com.cukes.bean.Step;

import cucumber.api.Scenario;

public class PerformAction {
	
	private Scenario scenario;
	private List<Step> stepList;
	private ArrayList<Gherkin> gherkinList;
	
	private static final Logger log = LogManager.getLogger(PerformAction.class);
	
	/***
	 * 
	 * @param scenarioName
	 * @param gherkinStatement
	 */
	public void doAction(String scenarioName, String gherkinStatement) {
		int stepListSize = stepList.size();
		
		for (Gherkin gherkin : gherkinList) {
			if (scenario.getName().equals(scenarioName) && gherkin.getName().equals(gherkinStatement)) {
				stepList = gherkin.getStepList();
				for (Step step : stepList) {
					WebElement element = getWebElement(step.getLocatorString(), step.getLocatorType());
					String action = step.getAction();
					String inputValue = step.getInputValue();
					PageActionsUtil.returnAction(action, element, inputValue);
				}
			}else {
				log.error("Scenario / Gherkin does not match");
			}
		}
	}
}
