package com.utils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;



public class Screenshot {
	
	
	public static String takeScreenshot( WebDriver driver) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
		 String date = sdf.format(new Date());
		String filename = System.getProperty("user.dir")+ File.separator+"Reports"+File.separator+"screenshots"+ File.separator+ "Screenshot_"+date;
		System.out.println(filename);  
		File srcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		File destFile = new File(filename+".png");
		  try {
			FileUtils.copyFile(srcFile, destFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		  String absPath = destFile.getAbsolutePath();
			//System.out.println("Absolute path is:"+absPath);
			return absPath;
	}
}
