package com.cukes.stepdefs;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import org.junit.Assert;

import com.cukes.constants.Buttons;
import com.cukes.constants.InputFields;
import com.cukes.pageobjects.SamplePage;
import com.cukes.utils.PropertyUtil;
import com.cukes.webservices.SampleApi;

import cucumber.api.java8.En;

public class sampleStepdefs implements En {

	public sampleStepdefs(ScenarioHooks hooks, SamplePage samplePage, SampleApi sampleApi) {

		Given("^I am on the homepage$", () -> {
			samplePage.setDriver(hooks.getDriver());
			samplePage.openPage(PropertyUtil.getConfigProp("test.url"));
			sampleApi.testApi();
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
