package com.cukes.stepdefs;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import org.junit.Assert;

import com.cukes.constants.Buttons;
import com.cukes.constants.InputFields;
import com.cukes.pageobjects.SamplePage;
import com.cukes.utils.PerformAction;
import com.cukes.utils.PropertyUtil;

import cucumber.api.java8.En;

public class accountcreationStepdefs implements En {
	
	private String scenarioName = "User Registration";

	public accountcreationStepdefs(ScenarioHooks hooks, SamplePage samplePage, PerformAction performAction) {

		Given("^I am in the website homepage$", () -> {
			samplePage.setDriver(hooks.getDriver());
			samplePage.openPage(PropertyUtil.getConfigProp("test.url"));
			
			String gherkinStatement = "I am in the website homepage";
			performAction.doAction(scenarioName, gherkinStatement);
		});

		When("^I searched for item '(.*)'", (String keyWord) -> {
			samplePage.inputValueInField(keyWord, InputFields.SEARCH);
			samplePage.clickButton(Buttons.SEARCH);
		});

		Then("^I should see search results related to the searched keyword$", (String docStrings) -> 
			Assert.assertThat("Verify if search results is displayed", 
					samplePage.isSearchResultsDisplayed(), is(equalTo(true))));
	}
}
