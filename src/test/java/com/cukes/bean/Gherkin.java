package com.cukes.bean;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Gherkin {

	@JsonProperty(value = "steps")
	private List<Step> steps;
	private String name;

	public List<Step> getStepList() {
		return steps;
	}

	public void setStepList(List<Step> stepList) {
		this.steps = stepList;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
