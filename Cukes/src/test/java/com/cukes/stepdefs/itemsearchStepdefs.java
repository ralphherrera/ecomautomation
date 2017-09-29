package com.cukes.stepdefs;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.cukes.pageobjects.HeaderPage;
import com.cukes.utils.PropertyUtil;

import cucumber.api.java8.En;

public class itemsearchStepdefs implements En {

	/*private HeaderPage headerPage;
	private ScenarioHooks hooks;*/
	private static final Logger log = LogManager.getLogger(itemsearchStepdefs.class);

	public itemsearchStepdefs(ScenarioHooks hooks, HeaderPage headerPage) {

		/*headerPage = new HeaderPage();
		hooks = new ScenarioHooks();*/
		
		Given("^I am on the homepage$", () -> {
			headerPage.setDriver(hooks.getDriver());
			headerPage.openPage(PropertyUtil.getConfigProp("test.url"));
		});

		When("^I searched for item '(.*)'", (String keyWord) -> {
			log.info("keyWord");
		});

		Then("^I should see search results related to the searched keyword$", (String docStrings) -> {
			log.info("Then");
		});
	}
}
