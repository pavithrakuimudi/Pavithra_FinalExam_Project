package com.pages;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.Status;
import com.baseClass.BaseClass;
import com.utils.Screenshot;

public class HomePage extends BaseClass {
	
	
	public HomePage() {
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css = "li.menu__listeItem a[title='English']")
	WebElement link_English;
	
	@FindBy(css = "li#OBLIGATIONS_tab a")
	WebElement link_ResultsForTheLast90DaysBond;
	
	@FindBy(css= "#OBLIGATIONS_heading")
	WebElement heading_ResultsForTheLast90DaysBond ;
	
	@FindBy (xpath = "(//table[@class='t-Report-report'])[2]/tbody[2]/tr/td/a")
	List<WebElement> table1_rowLinks;
	
	@FindBy (xpath = "(//table[@class='t-Report-report'])[2]/tbody[3]/tr/td/a")
	List<WebElement> table2_rowLinks;
	
	@FindBy (xpath = "(//table[@class='t-Report-report'])[2]/tbody[4]/tr/td/a")
	List<WebElement> table3_rowLinks;
	
	@FindBy (xpath = "(//table[@class='t-Report-report'])[2]/tbody[5]/tr/td/a")
	List<WebElement> table4_rowLinks;
	
	@FindBy (xpath = "(//table[@class='t-Report-report'])[2]/tbody[6]/tr/td/a")
	List<WebElement> table5_rowLinks;
	
	@FindBy (xpath="(//button[@type='button'])[3]")
	WebElement button_closeBondDetailWindow;
	
	
	public void clickOnEnglishLang() {
		link_English.click();
	}
	
	public String navigateToResultsForLast90DaysBond() {
		
		link_ResultsForTheLast90DaysBond.click();
		String heading_resultsForLast90DaysBond = heading_ResultsForTheLast90DaysBond.getText();
		return heading_resultsForLast90DaysBond;
	}
	
	public void clickAndOpenEachLinkOnTable() throws IOException, InterruptedException {
		List<WebElement>AllElementsForSheetsToBeCreated =  Stream.of(table1_rowLinks, table2_rowLinks, table3_rowLinks, table4_rowLinks, table5_rowLinks)
                .flatMap(List::stream)
                .collect(Collectors.toList());
		String rowLinkText = "" ;
		System.out.println("ele size "+AllElementsForSheetsToBeCreated.size());
		excelSheetMethods.createNewBook();
		for(WebElement ele:AllElementsForSheetsToBeCreated) {
			 rowLinkText = ele.getText();
			 test.log(Status.INFO, "tableName -"+ele.getText());
			 excelSheetMethods.createSheet(rowLinkText);
			
			 test.addScreenCaptureFromPath(Screenshot.takeScreenshot(driver));
			ele.click();
			excelSheetMethods.writeToSheet(rowLinkText);
			
			 test.addScreenCaptureFromPath(Screenshot.takeScreenshot(driver));
			 
			driver.switchTo().defaultContent();
			Thread.sleep(1000);
			button_closeBondDetailWindow.click();
			
		}
		
	}

}
