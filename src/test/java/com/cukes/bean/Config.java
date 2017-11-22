package com.cukes.bean;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Config {

	@JsonProperty(value = "testScenarios")
	private List<TestScenario> testScenarios;

	public List<TestScenario> getTestScenarios() {
		return testScenarios;
	}

	public void setTestScenarios(List<TestScenario> testScenarios) {
		this.testScenarios = testScenarios;
	}
	
}
