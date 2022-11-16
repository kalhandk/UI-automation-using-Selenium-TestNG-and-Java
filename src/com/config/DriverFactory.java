package com.config;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class DriverFactory {

	private WebDriver driver;
	
	private static DriverFactory instanceOfDriverFactory = null;
	
	//Create the constructor 
	//http://makeseleniumeasy.com/2018/01/11/singleton-design-pattern-in-selenium-webdriver/
	public DriverFactory() {
		
		String driverPath = PropertyFile.readProperty("chrome.driver.path");
		String baseUrl = PropertyFile.readProperty("base.url");
		System.setProperty("webdriver.chrome.driver", driverPath);
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get(baseUrl);
	}
	
	// TO create instance of class
	// Declare a static reference variable of class. Static is needed to make it available globally.	
	public static DriverFactory getInstanceOfDriverFactory() {
		if(instanceOfDriverFactory == null) {
			instanceOfDriverFactory = new DriverFactory();
		}
		return instanceOfDriverFactory;
	}
	
	// To get driver
	public WebDriver getDriverFactory() {
		return driver;
	}
	
	
}
