package com.leo.MySiteTest.Models.Role;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.leo.MySiteTest.Models.BaseElement;
import com.leo.MySiteTest.Models.BaseModel;

public class RoleMainPage extends BaseModel {
	public RoleMainPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	public BaseElement getAddNav() {
		return byATKey("btnAdd");

	}

	public BaseElement getInputBox() {
		return byId("txtRoleName");
	}

	public BaseElement getCustomerCheckbox() {
		return byXpath("//span[contains(text(),'CUSTOMER')]");
	}

	public BaseElement getBasic_InfoCheckbox() {
		return byXpath("//span[contains(text(),'BASIC_INFO')]");
	}

	public BaseElement getSaveBtn() {
		return byXpath(
				"//button[@class='el-button el-button--primary el-button--small']/span[contains(text(),'SAVE')]");
	}

	public BaseElement getQueryInput() {
		return byXpath("//input[@class='el-input__inner']");
	}

	public BaseElement getQueryBtn() {
		return byXpath(
				"//button[@class='el-button el-button--primary el-button--small']/span[contains(text(),'QUERY')]");
	}

	public BaseElement getResetBtn() {
		return byCssSelector("[at-key=\"btnBaseQueryReset\"]");
	}

	public BaseElement getListBody() {
		return byId("tblRole");
	}

	public BaseElement getRoleName() {
		return byClassName("el-table_2_column_8  ");
	}

}
