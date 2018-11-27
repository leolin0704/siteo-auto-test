package com.leo.MySiteTest.Models;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.leo.MySiteTest.Models.BaseModel;

public class NavigationComponent extends BaseModel {

	public NavigationComponent(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	public By getSystemManageNavBy() {
		return By.xpath("//ul[@role='menubar']/li/div[contains(string(),'System Manage')]");
	}

	public WebElement getSystemManageNavEl() {
		return driver.findElement(getSystemManageNavBy());
	}

	public By getRoleNavBy() {
		return By.xpath("//ul[@role='menu']/li[contains(text(),'Role')]");
	}

	public WebElement getRoleNavEl() {
		return driver.findElement(getRoleNavBy());
	}

}
