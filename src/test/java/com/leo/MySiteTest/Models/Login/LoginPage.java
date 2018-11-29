package com.leo.MySiteTest.Models.Login;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.leo.MySiteTest.Common.ConfigHelper;
import com.leo.MySiteTest.Models.BaseElement;
import com.leo.MySiteTest.Models.BaseModel;

public class LoginPage extends BaseModel {

	public LoginPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	String baseUrl = ConfigHelper.getBaseURL("/#/login");

	public void loadPage() {
		driver.get(baseUrl + "/");
	}

	public BaseElement getUseNameInput() {
		return byId("txtAccount");
	}

	public BaseElement getPasswordInput() {
		return byId("txtPassword");
	}

	public BaseElement getLoginBtn() {
		return byId("btnLogin");
	}

	public BaseElement getResetBtn() {
		return byId("btnReset");
	}

	public BaseElement UserNameWrong() {
		return buildElement(By.xpath("//div[contains(text(), \"Account is required.\")]"));
	}

	public BaseElement PasswordWrong() {
		return buildElement(By.xpath("//div[contains(text(), \"Password is required.\")]"));
	}

	public BaseElement PasswordOrUsernameWrong() {
		return buildElement(By.xpath("//p[contains(text(), \"Account or password is not correct.\")]"));
	}

	public BaseElement loading() {
		return byClassName("el-loading-mask");
	}

	public BaseElement getImages() {
		return byClassName("avatar");
	}

}
