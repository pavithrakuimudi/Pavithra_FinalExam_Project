package com.baseClass;


import java.io.File;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.pages.HomePage;
import com.utils.ExcelSheetMethods;
import com.utils.Screenshot;


public class BaseClass {
	public static WebDriver driver;
	public static ExtentSparkReporter extentSparkReporter;
	public static ExtentReports report;
	public static ExtentTest test;
	public static Screenshot screenShot;

	public HomePage homePage;
	public static ExcelSheetMethods excelSheetMethods;
	@BeforeClass
	public void initializePageClass() {
		homePage = new HomePage();
		excelSheetMethods = new ExcelSheetMethods();
		screenShot = new Screenshot();	
	}

	@BeforeTest
	public void setupBrowser() {
		//initializing extent reports
		initializeExtentReport();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.finmun.finances.gouv.qc.ca/finmun/f?p=100:3000::RESLT");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}

	@BeforeMethod
	public void beforeMethod(Method testMethod) {
		test = report.createTest(testMethod.getName());

	}

	@AfterMethod
	public void afterMethod(ITestResult result) {
		if(result.getStatus() == ITestResult.FAILURE) {
			test.log(Status.FAIL, "Test Case Failed is "+result.getName());
			test.log(Status.FAIL, "Test case failed reason: "+ result.getThrowable().getMessage());
			Screenshot.takeScreenshot( driver);
		}if(result.getStatus()==ITestResult.SUCCESS) {
			test.log(Status.PASS, "Test Case Passed is "+result.getName());
			Screenshot.takeScreenshot(driver);
		}else if(result.getStatus()==ITestResult.SKIP) {
			test.log(Status.SKIP, "Test case Skipped is: "+ result.getName());
		}

	}

	@AfterTest
	public void afterTest() {
		report.flush();
		driver.quit();
	}

	public void initializeExtentReport() {

		SimpleDateFormat sdf = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
		String date = sdf.format(new Date());
		date.replace(":", "_").replace(" ", "_");
		String extntreportFileName = System.getProperty("user.dir")+ File.separator+"Reports"+File.separator+"extentReport_"+date+".html";
		extentSparkReporter = new ExtentSparkReporter(extntreportFileName);
		extentSparkReporter.config().setDocumentTitle("Pavithra_finalExam");
		extentSparkReporter.config().setReportName("Regression testsuite");

		report = new ExtentReports();
		report.attachReporter(extentSparkReporter);
	}
}
