package com.leo.MySiteTest.TestCases;

import org.junit.Test;
import org.openqa.selenium.WebElement;
import com.leo.MySiteTest.Common.ConfigHelper;
import com.leo.MySiteTest.Models.Login.LoginPage;

public class LoginTest extends BaseChromeTester {

	String baseUrl = ConfigHelper.getBaseURL("/#/login");

	@Test
	public void LoginPage() {
		LoginPage adminUser = new LoginPage(driver);
		adminUser.loadPage();
		WebElement inputAccount = adminUser.getUseNameInput().getEl();
		inputAccount.sendKeys("admin");
		WebElement inputPassword = adminUser.getPasswordInput().getEl();
		inputPassword.sendKeys("123123");
		WebElement login = adminUser.getLoginBtn().getEl();
		login.click();
	}
}