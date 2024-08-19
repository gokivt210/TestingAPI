package com.sampleproject.commonmethods;

import com.sampleproject.reports.Readproperty;
import com.sampleproject.testcases.TC02_POSTRequest;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;

import com.sampleproject.reports.GenerateReports;

import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class CommonMethods extends GenerateReports {
	
	public static RequestSpecification httprequest;
	//public static Response response;
	public static String createdId=null;
	static String token= Readproperty.readProperty("Token");
 public static void getResponseBody(String UserId)	{

	 Response response = httprequest.header("Authorization","Bearer "+token).request(Method.GET,"/"+UserId);
	 String responseBody = response.getBody().asString();
	 System.out.println("The Response Body is:" +responseBody);
	 if (responseBody!=null) {
	 reportStep("Response Body generated successfully","pass");
	 } else {
	 reportStep("Issue in generating the response body","fail");
	 }
	 
 }
 
 public static void getStatusCode(String UserId) {
	 Response response = httprequest.request(Method.GET,"/"+UserId+"");
	 int statusCode = response.getStatusCode();
	 System.out.println("The Status code of the response is:" +statusCode);
	
 }
 
 public static void getStatusLine(String UserId) {
	 Response response = httprequest.header("Authorization","Bearer "+token).request(Method.GET,"/"+UserId+"");
	 String statusLine = response.getStatusLine();
	 System.out.println("The Status code of the response is:" +statusLine);
	
 }
 
 public static void verifyStatusCode(String UserId,String eCode) {
	 Response response = httprequest.request(Method.GET,"/"+UserId+"");
	 int statusCode = response.getStatusCode();
	 String sCode = String.valueOf(statusCode);
	 if (sCode.contentEquals(eCode)) {
		 System.out.println("Success Response");
		 Assert.assertTrue(true);
		reportStep("Status Code Matched. Successful Response","pass");
	 } else {
		 System.out.println("Failure Response");
		 Assert.assertFalse(false);
		 reportStep("Status Code is mismatched. Failure Response","fail");
	 }
	  
     
 }
 
 public static void verifyData(String UserId,String fieldname,String expectedvalue) {
	 Response response = httprequest.request(Method.GET,"/"+UserId+"");
	 String value = response.jsonPath().getJsonObject(fieldname).toString();
	 System.out.println("The field name is:" +value);
	 if (value.contentEquals(expectedvalue)) {
		 System.out.println("Values matched successfully");
		 Assert.assertTrue(true);
		 reportStep("Actual and Expected value matches for the field:"+fieldname+" successfully" ,"pass");
	 } else {
		 System.out.println("Values mismatch");
		 Assert.assertFalse(false);
		 reportStep("Actual and Expected value doesn't match for the field:"+fieldname+" successfully" ,"fail");
	 }
	 
 }

	public static String readResponsedataPost(int expectedStatusCode) {
		Response response = httprequest.request(Method.POST);
		String responseBody = response.getBody().asString();
		int statusCode = response.getStatusCode();
		if(statusCode==expectedStatusCode){
			reportStep("User created successfully","pass");
			Assert.assertTrue(true);

		}
		else {
			reportStep("user creation failed","fail");
			Assert.assertTrue(false);
		}
		if (responseBody!=null) {
			reportStep("Response Body generated successfully","pass");
		} else {
			reportStep("Issue in generating the response body","fail");
		}

		JsonPath js=new JsonPath(responseBody);
		createdId=Integer.toString(js.getInt("id"));
		return  createdId;

	}

 
	


}
