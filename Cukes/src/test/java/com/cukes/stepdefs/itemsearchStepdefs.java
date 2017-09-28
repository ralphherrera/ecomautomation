package com.cukes.stepdefs;

import com.cukes.pageobjects.HeaderPage;

import cucumber.api.java8.En;

public class itemsearchStepdefs implements En {

	private HeaderPage headerPage;

	public itemsearchStepdefs( ) {

		headerPage = new HeaderPage();
		
		Given("^I am on the homepage$", () -> {

		});

		When("^I searched for item '(.*)'", () -> {

		});

		Then("^I should see search results related to the searched keyword$", () -> {

		});
	}
}
