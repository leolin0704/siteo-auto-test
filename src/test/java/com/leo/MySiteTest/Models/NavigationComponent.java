package com.leo.MySiteTest.Models;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.leo.MySiteTest.Models.BaseModel;

public class NavigationComponent extends BaseModel {

	public NavigationComponent(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	public BaseElement getSystemManageNav() {
		return buildElement(By.xpath("//ul[@role='menubar']/li/div[contains(string(),'System Manage')]"));
	}

	public BaseElement getContentNav() {
		return buildElement(By.xpath("//ul[@role='menubar']/li/div[contains(string(),'Content')]"));
	}

	public BaseElement getBasic_InfoNav() {
		return buildElement(By.xpath("//ul[@role='menubar']/li/div[contains(string(),'Basic Info')]"));
	}

	public BaseElement getHomeNav() {
		return buildElement(By.xpath("//ul[@role='menubar']/li/div[contains(string(),'Home')]"));
	}

	public BaseElement getCustomerNav() {
		return buildElement(By.xpath("//ul[@role='menubar']/li/div[contains(string(),'Customer')]"));
	}

	public BaseElement getRoleNav() {
		return buildElement(By.xpath("//ul[@role='menu']/li[contains(text(),'Role')]"));
	}

	public BaseElement getUserNav() {
		return buildElement(By.xpath("//ul[@role='menu']/li[contains(text(),'User')]"));
	}

}
