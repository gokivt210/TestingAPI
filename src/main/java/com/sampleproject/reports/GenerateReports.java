package com.sampleproject.reports;

import java.io.IOException;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.MediaEntityModelProvider;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;



public abstract class GenerateReports {
	public static ExtentHtmlReporter reporter;
	public static ExtentReports extentReports;
	public static ExtentTest test, node;
	
	public String testcaseName, testcaseDec, author, category;

	@BeforeSuite
	public void startReport() {
		reporter = new ExtentHtmlReporter("./reports/result.html");
		reporter.setAppendExisting(false);
		extentReports = new ExtentReports();
		extentReports.attachReporter(reporter);
	}
	
    @BeforeClass
	public void report() throws IOException {
 		test = extentReports.createTest(testcaseName, testcaseDec);
 		test.assignAuthor(author);
		test.assignCategory(category);  
	}
    
    //public abstract long takeSnap();
    
    public  static void reportStep(String dec, String status ) {
    	if(status.equalsIgnoreCase("pass")) {
    	  test.log(Status.PASS, dec);
    	}
		else if (status.equalsIgnoreCase ("fail")) {
    		test.log(Status.FAIL, dec);
    	}
	
	}
    
    
    @AfterSuite
    public void stopReport() {

		extentReports.flush();
    }

}
