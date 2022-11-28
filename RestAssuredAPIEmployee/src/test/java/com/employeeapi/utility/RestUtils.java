package com.employeeapi.utility;

import org.apache.commons.lang3.RandomStringUtils;

public class RestUtils {

	// Below three are java method which generated random data of employee in the form of name, salary & age
	// All three method we describe static to direct access through its name, no need to create object.
	// RandomStringUtils is predefined method which generate random string but whenever it generate any string it is unique.
	// randomAlphabetic() is method which accept parameter in number which describe how many words are in string.
	
	public static String empName()
	{
		String generatedString = RandomStringUtils.randomAlphabetic(1);		
		return ("John" +generatedString);
	}
	
	public static String empSalary()
	{
		String generatedString = RandomStringUtils.randomAlphabetic(5);		
		return (generatedString);
	}
	
	public static String empAge()
	{
		String generatedString = RandomStringUtils.randomAlphabetic(2);		
		return (generatedString);
	}
	
}
