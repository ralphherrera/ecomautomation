package com.cukes.stepdefs;

import com.cukes.controller.TestScenarioController;
import com.cukes.utils.CommonMgmtUtil;
import com.cukes.utils.PerformAction;

import cucumber.api.java8.En;

public class accountcreationStepdefs implements En {
	
	public accountcreationStepdefs(ScenarioHooks hooks, PerformAction performAction,
			TestScenarioController testScenarioController) {

		Given("^I am in the website homepage$", () -> {
			String gherkinStep = "Given I am in the website homepage";
			performAction.setDriver(hooks.getDriver());
			CommonMgmtUtil.executeSteps(gherkinStep, testScenarioController, performAction);
		});
		
		And("^I navigate on Account Creation Page$", () -> {
			String gherkinStep = "And I navigate on Account Creation Page";
			CommonMgmtUtil.executeSteps(gherkinStep, testScenarioController, performAction);
		});

		When("^I fill up the account creation form$", () -> {
			String gherkinStep = "When I fill up the account creation form";
			CommonMgmtUtil.executeSteps(gherkinStep, testScenarioController, performAction);
		});

//		And("^I submit the account creation form$", () -> {
//			String gherkinStep = "And I submit the account creation form";
//			CommonMgmtUtil.executeSteps(gherkinStep, testScenarioController, performAction);
//		});
//		
//		Then("^I should see the account is logged in$", () -> {
//			String gherkinStep = "Then I should see the account is logged in";
//			CommonMgmtUtil.executeSteps(gherkinStep, testScenarioController, performAction);
//		});

	}
}
