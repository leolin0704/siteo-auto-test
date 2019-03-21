package com.leo.MySiteTest.Models;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class BaseElement {

	public BaseElement(By by, WebDriver driver) {
		this.by = by;
		this.driver = driver;
	}

	private WebDriver driver;
	private By by;

	public By getBy() {
		return by;
	}

	public WebElement getEl() {
		return driver.findElement(this.by);
	}

}
