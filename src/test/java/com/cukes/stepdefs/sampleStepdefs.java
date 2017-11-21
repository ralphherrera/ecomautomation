package com.cukes.stepdefs;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import java.util.List;

import org.junit.Assert;

import com.cukes.bean.Config;
import com.cukes.bean.TestScenario;
import com.cukes.constants.Buttons;
import com.cukes.constants.InputFields;
import com.cukes.pageobjects.SamplePage;
import com.cukes.utils.CommonMgmtUtil;
import com.cukes.utils.PropertyUtil;
import com.cukes.webservices.SampleApi;

import cucumber.api.java8.En;

public class sampleStepdefs implements En {

	public sampleStepdefs(ScenarioHooks hooks, SamplePage samplePage, SampleApi sampleApi) {

		Given("^I am on the homepage$", () -> {
			Config config = CommonMgmtUtil.getObjectScenario("data/static/amazon.json");
			List<TestScenario> tsList = config.getTestScenarios();
			for (TestScenario ts : tsList) {
				System.out.println("TestScenario: " + ts.getName() +
						" execute: " + ts.isExecute());
				if (ts.isExecute()) {
					samplePage.setDriver(hooks.getDriver());
					samplePage.openPage(PropertyUtil.getConfigProp("test.url"));
					sampleApi.testApi();
					break;
				}
			}
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
