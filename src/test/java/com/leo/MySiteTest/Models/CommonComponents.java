package com.leo.MySiteTest.Models;

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

}
