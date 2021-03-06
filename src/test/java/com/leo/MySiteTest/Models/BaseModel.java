package com.leo.MySiteTest.Models;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class BaseModel {

	public WebDriver driver;

	public BaseModel(WebDriver driver) {
		this.driver = driver;
	}

	public BaseElement byId(String id) {
		return buildElement(By.id(id));
	}

	public BaseElement byClassName(String classname) {
		return buildElement(By.className(classname));
	}

	public BaseElement byXpath(String xpath) {
		return buildElement(By.xpath(xpath));
	}

	public BaseElement byCssSelector(String CssSelector) {
		return buildElement(By.cssSelector(CssSelector));
	}

	public BaseElement byATKey(String key) {
		return buildElement(By.cssSelector("[at-key=\"" + key + "\"]"));
	}

	public BaseElement GetTableRow(String atKey, String value) {
		return buildElement(By.xpath("//*[@at-key='" + atKey + "' and text()='" + value + "']/ancestor::tr"));
	}

	public BaseElement buildElement(By by) {
		return new BaseElement(by, this.driver);
	}

	public BaseElement getDropDownItem(String atKey, String text) {
		return byXpath("//li[@at-key='" + atKey + "']/span[contains(text(),'" + text + "')]");
	}

}
