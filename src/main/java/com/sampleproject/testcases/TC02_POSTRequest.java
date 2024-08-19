package com.sampleproject.testcases;

import com.sampleproject.commonmethods.CommonMethods;
import com.sampleproject.reports.DataLibrary;
import com.sampleproject.reports.Readproperty;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import org.json.simple.JSONObject;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import javax.swing.text.Utilities;
import java.io.IOException;

public class TC02_POSTRequest extends CommonMethods{


    @BeforeTest
    public void startTest() {
        testcaseName="Fetching the Employee details";
        testcaseDec="TC02 Getting Employee Details";
        author="API tester";
        category="Functional Test Case";
    }

    @Test(priority = 1,dataProvider ="fetchData")
    public void addEmployeeDetails(String name,String email,String gender,String status) {
        String token= Readproperty.readProperty("Token");
        RestAssured.baseURI= Readproperty.readProperty("Url");
        httprequest = RestAssured.given();
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("name", name);
        jsonObject.put("email", email+Readproperty.generateRandomNumber()+"@testing.com");
        jsonObject.put("gender", gender);
        jsonObject.put("status", status);
       httprequest.header("Content-Type","application/json").header("Authorization","Bearer "+token).
                body(jsonObject.toJSONString());
        CommonMethods.readResponsedataPost(201);
        String createdemployeeId= new CommonMethods().createdId;
        System.out.println(createdemployeeId);
        //To check response using get call
        httprequest = RestAssured.given();
        CommonMethods.getResponseBody(createdemployeeId);
        CommonMethods.getStatusCode(createdemployeeId);
        CommonMethods.getStatusLine(createdemployeeId);

    }



    @DataProvider(name ="fetchData")
    public String[][] getdata() throws IOException {
        return DataLibrary.readdata("AddUser");
    }

}
