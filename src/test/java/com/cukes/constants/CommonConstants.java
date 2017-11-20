package com.cukes.constants;

import com.cukes.utils.PropertyUtil;

public class CommonConstants {

	private CommonConstants() {
		throw new AssertionError();
	}

	// Timeout
	public static final int ELEMENT_TIMEOUT = Integer.parseInt(PropertyUtil.getConfigProp("polling.default.time"));;
	public static final int POLLING_TIME = Integer.parseInt(PropertyUtil.getConfigProp("webdriver.default.time"));
	public static final int DEFAULT_WAIT_FOR_PAGE = Integer.parseInt(PropertyUtil.getConfigProp("default.wait.for.page"));
}
