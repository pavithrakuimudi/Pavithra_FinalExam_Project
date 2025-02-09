package com.utils;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.baseClass.BaseClass;

public class ExcelSheetMethods extends BaseClass {
	String dataFile = "./testData/testData.xlsx";

	XSSFWorkbook book;
	Sheet sheet;

	public void createNewBook() {
		book = new XSSFWorkbook();
	}
	public void createSheet(String sheetName) throws IOException {
		 sheet = book.createSheet(sheetName);
		//FileOutputStream fileOut = new FileOutputStream("./testData/testData.xlsx");
		
	}
	
	public void writeToSheet(String sheetName) throws InterruptedException, IOException {
				
		driver.switchTo().frame(0);
    	Thread.sleep(1000);
    	 List<WebElement> rows = driver.findElements(By.xpath("(//div[contains(@class,'hiddenOverflow margin-top-none')])[1]//table[2]/tbody/tr"));

    	 System.out.println(rows.size());
    
        // Extract table data
       
        int rowNum = 0;
        for (WebElement row : rows) {
            Row excelRow = sheet.createRow(rowNum++);
            List<WebElement> cells = row.findElements(By.tagName("td"));

            int cellNum = 0;
            for (WebElement cell : cells) {
                Cell excelCell = excelRow.createCell(cellNum++);
                System.out.println(cell.getText());
                excelCell.setCellValue(cell.getText());
            }
        }

        // Write the data to a file
        try (FileOutputStream fileOut = new FileOutputStream(dataFile)) {
            book.write(fileOut);
            System.out.println("Excel file created successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        } 
		
		
	}
}
