package com.employeeapi.testCases;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.employeeapi.base.TestBase;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC005_Delete_Employee_Record extends TestBase
{
	RequestSpecification httpRequest;
	Response response;
	
	@BeforeClass
	public void createEmployee() throws InterruptedException
	{
		logger.info("******** Started TC005_Delete_Employee_Record **********");
		
		RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1";
		httpRequest = RestAssured.given();
		
		response = httpRequest.request(Method.GET, "/employees");
		
		//First get JsonPath object instance from response interface
		JsonPath jsonPathEvaluator = response.jsonPath();

		// Capture ID
		String empID1 = jsonPathEvaluator.get("[0].id");
		
		httpRequest.header("Content-Type", "application/json");
		
		// Pass ID to delete the record		
		response = httpRequest.request(Method.DELETE, "/delete/" +empID1);
		
		Thread.sleep(5000);				
	}
	
	@Test
	public void checkResponseBody()
	{
		String responseBody = response.getBody().asString();
		
		Assert.assertEquals(responseBody.contains("Successfully! Record has been deleted"), true);
	}
	
	@Test
	public void checkStatusCode()
	{
		logger.info("************ Checking Status Code ************");
		
		int statusCode = response.getStatusCode();
		logger.info("Status Code is ==> " + statusCode);
		
		Assert.assertEquals(statusCode, 200);
	}
	
	@Test
	void checkStatusLine()
	{
		logger.info("************ Checking Status Line ************");
		
		String statusLine = response.getStatusLine();
		logger.info("Status Line is ==> " +statusLine);
		
		Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");
	}
	

	@Test
	void checkContentType()
	{
		logger.info("************ Checking Content Type ************");
		
		String contentType = response.header("Conetent-Type");
		logger.info("Content Type is ==> " +contentType);
		
		Assert.assertEquals(contentType, "text/html; charset=UTF-8");
	}
	
	@Test
	void checkServerType()
	{
		logger.info("************ Check Server Type ************");
		
		String serverType = response.header("Server");
		logger.info("Server Type is ==> " +serverType);
		
		Assert.assertEquals(serverType, "nginx");
	}
	
	@Test 
	void checkContentEncoding()
	{
		logger.info("************ Check Content Encoding ************");
		
		String contentEncoding = response.header("Content-Encoding");
		logger.info("Content Encoding is ==> " +contentEncoding);
		
		Assert.assertEquals(contentEncoding, "br");
	}
	
	@AfterClass
	void tearDown()
	{
		logger.info("************ Finished TC005_Delete_Employee_Record ************");
	}
	
}
