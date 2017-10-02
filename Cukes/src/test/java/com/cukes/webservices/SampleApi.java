package com.cukes.webservices;

import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.cukes.utils.PropertyUtil;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class SampleApi {
	
	private RequestSpecification requestSpecification;
	private Response response;
	
	private static final Logger log = LogManager.getLogger(SampleApi.class);
	
	public String testApi() {
		log.entry();
		RestAssured.useRelaxedHTTPSValidation();
		/*Map<String, String> tokenParamMap = new HashMap<>();
		tokenParamMap.put("grant_type", PropertyUtil.getTestDataProp("token.grant.type"));
		tokenParamMap.put("client_id", PropertyUtil.getTestDataProp("token.client.id"));
		tokenParamMap.put("client_secret", PropertyUtil.getTestDataProp("token.client.secret"));
		tokenParamMap.put("username", PropertyUtil.getTestDataProp("token.username"));
		tokenParamMap.put("password", PropertyUtil.getTestDataProp("token.password"));
		
		String accessToken;

		requestSpecification = RestAssured.given().formParams(tokenParamMap).contentType(ContentType.URLENC);
		log.info("Request body {}", requestSpecification);
		
		response = requestSpecification.when().post(PropertyUtil.getTestDataProp("token.generator.uri"));*/
		String grantTypeValue = PropertyUtil.getTestDataProp("token.grant.type");
		String clientIDValue = PropertyUtil.getTestDataProp("token.client.id");
		String clientSecretValue = PropertyUtil.getTestDataProp("token.client.secret");
		String usernameValue = PropertyUtil.getTestDataProp("token.username");
		String passwordValue = PropertyUtil.getTestDataProp("token.password");

		requestSpecification = RestAssured.given().formParam("grant_type", grantTypeValue)
				.formParam("client_id", clientIDValue).formParam("client_secret", clientSecretValue)
				.formParam("username", usernameValue).formParam("password", passwordValue).contentType(ContentType.URLENC);
		log.info(requestSpecification);

		response = requestSpecification.when().post(PropertyUtil.getTestDataProp("token.generator.uri"));
		log.exit();
		return "";
	}
	
	
	public void main (String [] args) {
		log.info(testApi());
	}
}