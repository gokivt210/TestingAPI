package com.sampleproject.reports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public  class GenerateReports_copy {

	public String generateDateTime(){
		LocalDateTime dateTime=LocalDateTime.now();
		DateTimeFormatter dateTimeFormatter=DateTimeFormatter.ofPattern("dd_mm_yy_hh_mm_ss");
		String datetime=dateTimeFormatter.format(dateTime);
		return datetime;
	}

	public ExtentReports generateReport() {
		ExtentHtmlReporter reporter = new ExtentHtmlReporter("./reports/result"+generateDateTime()+".html");
		reporter.setAppendExisting(false);
		ExtentReports extentReports = new ExtentReports();
		extentReports.attachReporter(reporter);
		return extentReports;

	}


}
