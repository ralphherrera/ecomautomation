package com.cukes.stepdefs;

import com.cukes.utils.PerformAction;

import cucumber.api.java8.En;

public class accountcreationStepdefs implements En {
	
	private String scenarioName = "User Registration";

	public accountcreationStepdefs(ScenarioHooks hooks, PerformAction performAction) {

		Given("^I am in the website homepage$", () -> {
			String gherkinStep = "I am in the website homepage";
			
			performAction.doAction(scenarioName, gherkinStep);
		});

	}
}
