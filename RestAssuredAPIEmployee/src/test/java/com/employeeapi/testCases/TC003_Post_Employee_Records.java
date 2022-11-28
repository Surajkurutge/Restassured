package com.employeeapi.testCases;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.employeeapi.base.TestBase;
import com.employeeapi.utility.RestUtils;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC003_Post_Employee_Records extends TestBase{
	
	RequestSpecification httpRequest;
	Response response;
	
	String empName = RestUtils.empName();
	String empSalary =RestUtils.empSalary();
	String empAge =RestUtils.empAge();
	
	@BeforeClass
	public void createEmployee() throws InterruptedException
	{
		logger.info("******** Started TC003_Post_Employee_Records **********");
		
		RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1";
		httpRequest = RestAssured.given();
		
		// JSONObject is class which represents a simple JSON.
		// We can add Key-value pairs by using put method
		// {"name":"Pawan", "salary":"25000", "age":"29"}
		
		JSONObject requestParms = new JSONObject();
		requestParms.put("name", empName);
		requestParms.put("salary", empSalary);
		requestParms.put("age", empAge);
		
		// Add Header that stating Request body is in JSON		
		httpRequest.header("Content-Type", "application/json");
		
		// Add the JSON to the body of request
		httpRequest.body(requestParms.toJSONString());
				
		response = httpRequest.request(Method.POST, "/create");
	
		// After sending request it take some time to provide response that why we are using Thread.sleep() here
		// we can not used ImplicitlyWait()/ExplicitelyWait() here because it is Selenium webdriver method.
		
		Thread.sleep(5000);				
	}
	
	@Test
	public void checkResponseBody()
	{
		String responseBody = response.getBody().asString();
		
		Assert.assertEquals(responseBody.contains(empName), true);
		Assert.assertEquals(responseBody.contains(empSalary), true);
		Assert.assertEquals(responseBody.contains(empAge), true);
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
		logger.info("************ Finished TC003 Post_Employee_Records ************");
	}
	
}
