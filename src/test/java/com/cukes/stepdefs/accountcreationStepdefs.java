package com.cukes.stepdefs;

import java.util.List;

import com.cukes.bean.Config;
import com.cukes.bean.TestScenario;
import com.cukes.utils.CommonMgmtUtil;
import com.cukes.utils.PerformAction;
import com.cukes.utils.PropertyUtil;

import cucumber.api.java8.En;

public class accountcreationStepdefs implements En {
	
	private String scenarioName = "User Registration";

	public accountcreationStepdefs(ScenarioHooks hooks, PerformAction performAction,
			TestScenario testScenario) {

		Given("^I am in the website homepage$", () -> {
			Config config = CommonMgmtUtil.getObjectScenario("data/static/amazon.json");
			List<TestScenario> tsList = config.getTestScenarios();
			for (TestScenario ts : tsList) {
				System.out.println("TestScenario: " + ts.getName() +
						" execute: " + ts.isExecute());
				if (ts.isExecute()) {
					performAction.setDriver(hooks.getDriver());
					String gherkinStep = "Given I am in the website homepage";
					performAction.doAction(scenarioName, gherkinStep);
					break;
				}
			}
		});
		
		And("^I navigate on Account Creation Page$", () -> {
			String gherkinStep = "And I navigate on Account Creation Page";
			
			performAction.doAction(scenarioName, gherkinStep);
		});
		
		When("^I fill up the account creation form$", () -> {
			String gherkinStep = "When I fill up the account creation form";
			performAction.doAction(scenarioName, gherkinStep);
		});
		
		And("^I submit the account creation form$", () -> {
			String gherkinStep = "And I submit the account creation form";
			performAction.doAction(scenarioName, gherkinStep);
		});
		
		Then("^I should see the account is logged in$", () -> {
			String gherkinStep = "Then I should see the account is logged in";
			performAction.doAction(scenarioName, gherkinStep);
		});

	}
}
