package com.cukes.bean;

import java.util.List;

public class TestScenario {

	private boolean execute;
	private String name;
	private List<Gherkin> gherkins;
	
	public boolean isExecute() {
		return execute;
	}
	public void setExecute(boolean execute) {
		this.execute = execute;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Gherkin> getGherkin() {
		return gherkins;
	}
	public void setGherkin(List<Gherkin> gherkin) {
		this.gherkins = gherkin;
	}
	
}
