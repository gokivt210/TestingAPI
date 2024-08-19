package com.sampleproject.testcases;

import java.io.IOException;

import com.sampleproject.reports.Readproperty;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.sampleproject.commonmethods.CommonMethods;
import com.sampleproject.reports.DataLibrary;

import io.restassured.RestAssured;

public class TC01_GETRequest extends CommonMethods {
	
	@BeforeTest
	public void startTest() {
		testcaseName="Fetching the Employee details";
		testcaseDec="TC01 Getting Employee Details";
		author="API tester";
		category="Functional Test Case";
	}
	
	@Test (dataProvider ="fetchData")
	public void getEmployeeDetails(String UserId) {

		    RestAssured.baseURI= Readproperty.readProperty("Url");
		    httprequest = RestAssured.given();
		    CommonMethods.getResponseBody(UserId);
		    CommonMethods.getStatusCode(UserId);
		    CommonMethods.getStatusLine(UserId);
		    CommonMethods.verifyStatusCode(UserId,"200");
		    CommonMethods.verifyData(UserId, "data.first_name","Janet");
		    CommonMethods.verifyData(UserId, "data.last_name", "Weaver");
	}
	
	@DataProvider(name ="fetchData")
	public String[][] getdata() throws IOException{
		return DataLibrary.readdata("Url");
	}

}
