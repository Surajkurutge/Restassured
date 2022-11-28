package com.employeeapi.utility;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class Listeners extends TestListenerAdapter
{
	public ExtentHtmlReporter htmlreporter;
	public ExtentReports extent;
	public ExtentTest test;
	
	public void onStart(ITestContext testcontext)
	{
		htmlreporter = new ExtentHtmlReporter (System.getProperty("user.dir") + "/Ext_Reports/myReport.html");
		
		htmlreporter.config().setDocumentTitle("Employee API Automation Report");
		htmlreporter.config().setReportName("Rest API Testing Report");
		htmlreporter.config().setTheme(Theme.DARK);
		
		extent = new ExtentReports();
		extent.attachReporter(htmlreporter);
		extent.setSystemInfo("Project Name", "Employee Database API");
		extent.setSystemInfo("Host Name", "localhost");
		extent.setSystemInfo("Environemnt", "QA");
		extent.setSystemInfo("User", "Pawan");
	}

	public void onTestSucess(ITestResult result)
	{
		test = extent.createTest(result.getName());   // Create New Entry in Report
		
		test.log(Status.PASS, "Test Case PASSED IS " +result.getName());  // To add name in Extent Report
	}

	public void onTestFailure(ITestResult result)
	{
		test = extent.createTest(result.getName()); 	// Create New Entry in Report
		
		test.log(Status.FAIL, "Test Case FAILED IS " +result.getName());   // To add name in Extent Report
		test.log(Status.FAIL, "Test Case FAILED IS " +result.getThrowable());   // To add error/exception in Extent Report
	}
	
	public void onTestSkipped(ITestResult result)
	{
		test = extent.createTest(result.getName()); 	// Create New Entry in Report
		
		test.log(Status.SKIP, "Test Case SKIPED IS " +result.getName());   // To add name in Extent Report
	}

	public void onFinish(ITestContext testContext)
	{
		extent.flush();
	}

}
