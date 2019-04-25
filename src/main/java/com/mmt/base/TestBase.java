package com.mmt.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import com.mmt.common.BrowserLaunch;

/**
 * @author Aamir Mohammed Suhail
 * @email  aamirsuhail01@yahoo.com
 */
public class TestBase {
	
	protected WebDriver driver;
	public Logger logger;
	Properties prop;
	
	
	@BeforeTest
	public void init() throws InterruptedException, FileNotFoundException, IOException {
		logger = Logger.getLogger(this.getClass()); 
		initPropertyFile();	
		String browser = prop.getProperty("browser");	
		String testUrl = prop.getProperty("testWebsiteUrl");
		driver = BrowserLaunch.openBrowser(browser);
		logger.info("Browser launched");
		driver.get(testUrl);
		logger.info("Navigated to url");
		Thread.sleep(2000);
	}
	
	@BeforeMethod
	public void beforeMethod(ITestResult res) {
		logger.info("====== "+res.getMethod().getMethodName()+" Started=====");
	}
	
	@AfterMethod
	public void afterMethod(ITestResult res) {
		logger.info("====== "+res.getMethod().getMethodName()+" Finished=====");
	}
	
	
	
	
	public void initPropertyFile() throws FileNotFoundException, IOException {
		//Load properties file and access the values from file.
		prop = new Properties();
		prop.load(new FileInputStream(".\\src\\main\\java\\com\\mmt\\config\\config.properties"));
	}

}
