package com.testSuite;

import java.io.IOException;

import org.testng.annotations.Test;

import com.baseClass.BaseClass;


public class TC_captureTableContent  extends BaseClass {
	@Test
	public void testmethod1() throws IOException, InterruptedException {
		
		homePage.clickOnEnglishLang();
		homePage.navigateToResultsForLast90DaysBond();
		homePage.clickAndOpenEachLinkOnTable();
	}

}
