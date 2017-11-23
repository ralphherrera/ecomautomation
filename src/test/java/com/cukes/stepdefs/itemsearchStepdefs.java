package com.cukes.stepdefs;

import com.cukes.controller.TestScenarioController;
import com.cukes.utils.CommonMgmtUtil;
import com.cukes.utils.PerformAction;

import cucumber.api.java8.En;

public class itemsearchStepdefs implements En {
	
	public itemsearchStepdefs(ScenarioHooks hooks, PerformAction performAction,
			TestScenarioController testScenarioController) {

		When("^I searched for an item$", () -> {
			String gherkinStep = "When I searched for an item";
			CommonMgmtUtil.executeSteps(gherkinStep, testScenarioController, performAction);
		});

		Then("^I should see relevant items displayed$", () -> {
			String gherkinStep = "Then I should see relevant items displayed";
			CommonMgmtUtil.executeSteps(gherkinStep, testScenarioController, performAction);
		});
	}
}
