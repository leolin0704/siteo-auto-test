package com.leo.MySiteTest.Models;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.leo.MySiteTest.Models.BaseModel;

public class NavigationComponent extends BaseModel {

	public NavigationComponent(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	public BaseElement getTitleNav(String keywords) {
		return buildElement(By.xpath("//ul[@role='menubar']/li/div[contains(string(),'" + keywords + "')]"));
	}

	public BaseElement getDetailNav(String keywords) {
		return buildElement(By.xpath("//ul[@role='menu']/li[contains(text(),'" + keywords + "')]"));
	}

}
