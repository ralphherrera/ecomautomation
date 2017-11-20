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

public class accountcreationStepdefs implements En {

	public accountcreationStepdefs(ScenarioHooks hooks, SamplePage samplePage, SampleApi sampleApi) {

		Given("^I am in the website homepage$", () -> {
			String gherkinStep = "I am in the website homepage";
		});

	}
}
