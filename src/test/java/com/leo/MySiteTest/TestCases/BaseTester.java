package com.leo.MySiteTest.TestCases;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;

public class BaseTester {
	public WebDriver driver;

	public WebDriver CreateWebDriverInstance(String type) {
		WebDriver driver;
		if ("chrome".equals(type)) {
			System.setProperty("webdriver.chrome.driver",
					"C:\\Users\\Administrator\\AppData\\Local\\Google\\Chrome\\Application\\chromedriver.exe");
			driver = new ChromeDriver();
		} else {
			System.setProperty("webdriver.firefox.bin", "C:\\Program Files\\Mozilla Firefox\\firefox.exe");
			System.setProperty("webdriver.gecko.driver", "D:\\Selenium_install\\geckodriver.exe");
			driver = new FirefoxDriver();
		}

		return driver;

	}

	@Before
	public void beforeMethod() {
		driver = CreateWebDriverInstance("firefox");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
	}

	@After
	public void afterMethod() {
		driver.quit();
	}
}
