package com.leo.Practise;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class TodoIndex {

	public WebDriver driver;
	String baseUrl = "http://localhost:8080/todo";

	public TodoIndex(WebDriver driver) {
		this.driver = driver;
	}

	public void loadPage() {
		driver.get(baseUrl + "/");
	}

	public WebElement getQueryName() {
		return driver.findElement(By.id("queryName"));
	}

	public WebElement getQueryBtn() {
		return driver.findElement(By.id("btnQuery"));
	}

	public WebElement getListBody() {
		return driver.findElement(By.id("todo"));
	}

	public List<WebElement> getTrList(WebElement tbody) {
		return tbody.findElements(By.tagName("tr"));
	}
}
