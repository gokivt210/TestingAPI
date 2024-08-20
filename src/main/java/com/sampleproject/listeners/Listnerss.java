package com.sampleproject.listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.sampleproject.reports.GenerateReports_copy;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class Listnerss implements ITestListener {

    ExtentTest extentTest;
    ExtentReports extentReports=new GenerateReports_copy().generateReport();
    public void onTestStart(ITestResult iTestResult) {
        extentTest= extentReports.createTest(iTestResult.getMethod().getMethodName());

    }


    public void onTestSuccess(ITestResult iTestResult) {

        extentTest.log(Status.PASS,iTestResult.getMethod().getMethodName());
    }


    public void onTestFailure(ITestResult iTestResult) {
        extentTest.log(Status.FAIL,iTestResult.getMethod().getMethodName());
    }


    public void onTestSkipped(ITestResult iTestResult) {
        System.out.println();
    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {

    }

    public void onStart(ITestContext iTestContext) {
        System.out.println();
    }


    public void onFinish(ITestContext iTestContext) {
        extentReports.flush();
    }
}
