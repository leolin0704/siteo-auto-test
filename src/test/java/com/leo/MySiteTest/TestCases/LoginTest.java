package com.leo.MySiteTest.TestCases;

import org.junit.Test;
import com.leo.MySiteTest.Common.ConfigHelper;
import com.leo.MySiteTest.Models.Login.LoginPage;

public class LoginTest extends BaseChromeTester {

	String baseUrl = ConfigHelper.getBaseURL("/#/login");

	@Test
	public void LoginPage() {
		LoginPage adminUser = new LoginPage(driver);
		adminUser.UserLogin("admin", "123123");
	}
}