package com.cukes.stepdefs;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.cukes.bean.Gherkin;
import com.cukes.bean.TestScenario;
import com.cukes.controller.TestScenarioController;
import com.cukes.utils.CommonMgmtUtil;
import com.cukes.utils.PerformAction;

import cucumber.api.java8.En;

public class itemsearchStepdefs implements En {
	
	private static final Logger log = LogManager.getLogger(itemsearchStepdefs.class);
	
	public itemsearchStepdefs(ScenarioHooks hooks, PerformAction performAction,
			TestScenarioController testScenarioController) {

		Given("^I am in the website homepage$", () -> {
			String gherkinStep = "Given I am in the website homepage";
			performAction.setDriver(hooks.getDriver());

			TestScenario testScenario = testScenarioController.getTestScenario();
			Gherkin gherkin = CommonMgmtUtil.getGherkin(testScenario.getGherkin(), gherkinStep);
			String scenarioName = testScenario.getName();
			
			log.info("TestScenario: {}", scenarioName);
			log.info("Will execute?: {}", testScenario.isExecute());
			
			if (testScenario.isExecute()) {
				performAction.doAction(scenarioName, gherkin);
			}
		});
		
		And("^I navigate on Account Creation Page$", () -> {
			String gherkinStep = "And I navigate on Account Creation Page";
			performAction.setDriver(hooks.getDriver());

			TestScenario testScenario = testScenarioController.getTestScenario();
			Gherkin gherkin = CommonMgmtUtil.getGherkin(testScenario.getGherkin(), gherkinStep);
			String scenarioName = testScenario.getName();
			
			log.info("TestScenario: {}", scenarioName);
			log.info("Will execute?: {}", testScenario.isExecute());
			
			if (testScenario.isExecute()) {
				performAction.doAction(scenarioName, gherkin);
			}
		});

		When("^I fill up the account creation form$", () -> {
			String gherkinStep = "When I fill up the account creation form";
			performAction.setDriver(hooks.getDriver());

			TestScenario testScenario = testScenarioController.getTestScenario();
			Gherkin gherkin = CommonMgmtUtil.getGherkin(testScenario.getGherkin(), gherkinStep);
			String scenarioName = testScenario.getName();
			
			log.info("TestScenario: {}", scenarioName);
			log.info("Will execute?: {}", testScenario.isExecute());
			
			if (testScenario.isExecute()) {
				performAction.doAction(scenarioName, gherkin);
			}
		});

//		And("^I submit the account creation form$", () -> {
//			String gherkinStep = "And I submit the account creation form";
//			performAction.doAction(scenarioName, gherkinStep);
//		});
//		
//		Then("^I should see the account is logged in$", () -> {
//			String gherkinStep = "Then I should see the account is logged in";
//			performAction.doAction(scenarioName, gherkinStep);
//		});

	}
}
