package com.leo.MySiteTest.Models.Login;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.leo.MySiteTest.Common.ConfigHelper;
import com.leo.MySiteTest.Models.BaseElement;
import com.leo.MySiteTest.Models.BaseModel;
import com.leo.MySiteTest.Models.CommonComponents;

public class LoginPage extends BaseModel {

	public LoginPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
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
		return buildElement(By.xpath(
				"//div[@class='el-notification__content']/p[contains(text(), \"Account and password do not match.\")]"));
	}

	public BaseElement loading() {
		return byClassName("el-loading-mask");
	}

	public BaseElement getImages() {
		return byClassName("avatar");
	}

	public void UserLogin(String Name, String Password, CommonComponents commonCom, WebDriverWait waiter) {
		LoginPage adminUser = new LoginPage(driver);
		WebElement inputAccount = adminUser.getUseNameInput().getEl();
		inputAccount.sendKeys(Name);
		WebElement inputPassword = adminUser.getPasswordInput().getEl();
		inputPassword.sendKeys(Password);
		WebElement login = adminUser.getLoginBtn().getEl();
		login.click();
		waiter.until(ExpectedConditions.invisibilityOfElementLocated(commonCom.getLoading().getBy()));
	}

}
