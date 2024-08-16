package com.sampleproject.testcases;

import com.sampleproject.commonmethods.CommonMethods;
import com.sampleproject.reports.DataLibrary;
import io.restassured.RestAssured;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;

public class TC02_POSTRequest extends CommonMethods{
    @BeforeTest
    public void startTest() {
        testcaseName="Fetching the Employee details";
        testcaseDec="TC01 Getting Employee Details";
        author="API tester";
        category="Functional Test Case";
    }

    @Test(dataProvider ="fetchData")
    public void addEmployeeDetails(String baseURI, String UserId) {

        RestAssured.baseURI=baseURI;
        httprequest = RestAssured.given();
        CommonMethods.getResponseBody(UserId);
        CommonMethods.getStatusCode(UserId);
        CommonMethods.getStatusLine(UserId);
        CommonMethods.verifyStatusCode(UserId,"200");
        CommonMethods.verifyData(UserId, "data.first_name","Janet");
        CommonMethods.verifyData(UserId, "data.last_name", "Weaver");
    }

    @DataProvider(name ="fetchData")
    public String[][] getdata() throws IOException {
        return DataLibrary.readdata();
    }

}
