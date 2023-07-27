package com.qa.api.gorest.tests;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.api.gorest.restclient.RestClient;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

@Epic("Epic-101: get user go rest api feature implementation.....")
@Feature("US - 1009: get user api feature....")
public class GetUserTest {

	String baseURI = "https://gorest.co.in";
	String basePath = "/public-api/users";
	String token = "_FWTKt73f0EeVrfWj7d3sKoLMnw_9dqVcs0k";

	@Description("get all user lists api test...verify all users list from get call....")
	@Severity(SeverityLevel.CRITICAL)
	@Test(priority = 1)
	public void getAllUserListAPITest() {
		Map<String, String> authTokenMap = new HashMap<String, String>();
		authTokenMap.put("Authorization", "Bearer " + token);
		Response response = RestClient.doGet("JSON", baseURI, basePath, authTokenMap, null, true);

		System.out.println(response.getStatusCode());
		System.out.println(response.prettyPrint());
		Assert.assertEquals(response.getStatusCode(), 300);
	}

	@Description("get all user lists api test with query partams...verify all users list from get call....")
	@Severity(SeverityLevel.NORMAL)
	@Test(priority = 2)
	public void getUserWithQueryParamsAPITest() {
		Map<String, String> authTokenMap = new HashMap<String, String>();
		authTokenMap.put("Authorization", "Bearer " + token);

		Map<String, String> params = new HashMap<String, String>();
		params.put("first_name", "John");
		params.put("gender", "male");

		Response response = RestClient.doGet("JSON", baseURI, basePath, authTokenMap, params, true);

		System.out.println(response.getStatusCode());
		System.out.println(response.prettyPrint());
		
		String jsonString = response.getBody().asString();
		
		JsonPath js = new JsonPath(jsonString);
		
		List<Object> resultList = js.getList("result");
		System.out.println(resultList.size());
		
		System.out.println(js.getList("result.id"));
		
		

	}

}
