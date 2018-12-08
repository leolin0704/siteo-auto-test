package com.leo.MySiteTest.Models;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.leo.MySiteTest.Models.BaseModel;

public class CommonComponents extends BaseModel {

	public CommonComponents(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	public BaseElement getMessage() {
		return byClassName("el-message");
	}

	public BaseElement getLoading() {
		return byClassName("el-loading");
	}

	public BaseElement getDialog() {
		return byClassName("el-dialog");
	}

	public BaseElement getDialogSave() {
		return byATKey("btnDialogSave");
	}

	public BaseElement getDialogCancel() {
		return byATKey("btnDialogCancel");
	}

	public BaseElement getAlertWindow() {
		return byCssSelector("[role=\"alert\"]");
	}

	public BaseElement GetErrorPrompt(String value) {
		return buildElement(By.xpath("//div[@class='el-form-item__error' and contains(.,'" + value + "')]"));
	}

}
