package com.cukes.controller;

import java.util.List;

import com.cukes.bean.Config;
import com.cukes.bean.TestScenario;
import com.cukes.utils.CommonMgmtUtil;
import com.cukes.utils.PropertyUtil;

public class TestScenarioController {
	
	private Config conf = CommonMgmtUtil.getObjectScenario(PropertyUtil.getConfigProp("test.json"));
	private TestScenario testScenario;
	
	public List<TestScenario> getScenarioList() {
		List<TestScenario> tsList = conf.getTestScenarios();
		return tsList;
	}

	public TestScenario getTestScenario() {
		return testScenario;
	}

	public void setTestScenario(TestScenario testScenario) {
		this.testScenario = testScenario;
	}
	
}
