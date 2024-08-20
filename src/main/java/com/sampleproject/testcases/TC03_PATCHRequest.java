package com.sampleproject.testcases;

import com.sampleproject.commonmethods.CommonMethods;
import com.sampleproject.reports.DataLibrary;
import com.sampleproject.reports.Readproperty;
import io.restassured.RestAssured;
import org.json.simple.JSONObject;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;

public class TC03_PATCHRequest extends CommonMethods{


    @BeforeTest
    public void startTest() {
        testcaseName="Updating Employee details";
        testcaseDec="TC03 Updating Employee Details";
        author="API tester";
        category="Functional Test Case";
    }

    @Test(priority = 1,dataProvider ="fetchData")
    public void updateEmployeeDetails(String name,String email,String userId) {
        String token= Readproperty.readProperty("Token");
        RestAssured.baseURI= Readproperty.readProperty("Url");
        httprequest = RestAssured.given();
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("name", name);
        jsonObject.put("email", email+Readproperty.generateRandomNumber()+"@testing.com");

       httprequest.header("Content-Type","application/json").header("Authorization","Bearer "+token).
                body(jsonObject.toJSONString());
       new CommonMethods().readPatchResponseData(userId);
        httprequest = RestAssured.given();
        CommonMethods.getResponseBody(userId);
        CommonMethods.getStatusCode(userId);
        CommonMethods.getStatusLine(userId);

    }



    @DataProvider(name ="fetchData")
    public String[][] getdata() throws IOException {
        return DataLibrary.readdata("UpdateUser");
    }

}
