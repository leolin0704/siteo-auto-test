package com.leo.MySiteTest.TestCases;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.leo.MySiteTest.Common.ConfigHelper;
import com.leo.MySiteTest.Models.CommonComponents;
import com.leo.MySiteTest.Models.NavigationComponent;
import com.leo.MySiteTest.Models.Login.LoginPage;
import com.leo.MySiteTest.Models.Role.RoleMainPage;
import com.leo.MySiteTest.consts.PasswordConsts;
import com.leo.MySiteTest.consts.PermissionConsts;
import com.leo.MySiteTest.service.RoleService;
import com.leo.MySiteTest.service.UserService;

public class LoginTest extends BaseChromeTester {

	RoleService roleService = new RoleService();

	UserService userService = new UserService();

	String baseUrl = ConfigHelper.getBaseURL("/#/login");

	@Test
	public void Login() throws Exception {
		WebDriverWait waiter = new WebDriverWait(driver, 20);
		NavigationComponent navigation = new NavigationComponent(driver);
		CommonComponents commonCom = new CommonComponents(driver);

		ArrayList<String> permissionList = new ArrayList<String>();
		permissionList.add(PermissionConsts.CUSTOMER);
		permissionList.add(PermissionConsts.SYSTEM);

		userService.InitUser("Andy", "DB Manager", PasswordConsts.PW_123123, permissionList);

		LoginPage adminUser = new LoginPage(driver);
		adminUser.UserLogin("Andy", "123123", commonCom, waiter);
		waiter.until(ExpectedConditions.invisibilityOfElementLocated(commonCom.getLoading().getBy()));
		WebElement Systemtitle = navigation.getSystemManageNav().getEl();
		Assert.assertTrue(Systemtitle.isDisplayed());
		WebElement Customertitle = navigation.getCustomerNav().getEl();
		Assert.assertTrue(Customertitle.isDisplayed());
		try {
			WebElement Basic_InfoTitle = navigation.getBasic_InfoNav().getEl();

			System.out.println("Basic_InfoTitle exists");
		} catch (Exception e) {
			System.out.println("Basic_InfoTitle doesn't exist!");

		}
		try {
			WebElement ContentTitle = navigation.getContentNav().getEl();

			System.out.println("ContentTitle exists");
		} catch (Exception e) {
			System.out.println("ContentTitle doesn't exist!");

		}
	}

	@Test
	public void LoginWithNoAcconut() {
		WebDriverWait waiter = new WebDriverWait(driver, 20);
		CommonComponents commonCom = new CommonComponents(driver);
		LoginPage adminUser = new LoginPage(driver);
		adminUser.UserLogin("", "123123", commonCom, waiter);
		WebElement NoAccountPrompt = adminUser.UserNameWrong().getEl();
		Assert.assertTrue(NoAccountPrompt.isDisplayed());
	}

	@Test
	public void LoginWithNoPassword() {
		WebDriverWait waiter = new WebDriverWait(driver, 20);
		CommonComponents commonCom = new CommonComponents(driver);
		LoginPage adminUser = new LoginPage(driver);
		adminUser.UserLogin("admin", "", commonCom, waiter);
		WebElement NoPasswordPrompt = adminUser.PasswordWrong().getEl();
		Assert.assertTrue(NoPasswordPrompt.isDisplayed());
	}

	@Test
	public void LoginWithWrongPassword() {
		WebDriverWait waiter = new WebDriverWait(driver, 20);
		CommonComponents commonCom = new CommonComponents(driver);
		LoginPage adminUser = new LoginPage(driver);
		adminUser.UserLogin("admin", "456", commonCom, waiter);
		waiter.until(ExpectedConditions.invisibilityOfElementLocated(commonCom.getLoading().getBy()));
		WebElement WrongPasswordPrompt = adminUser.PasswordOrUsernameWrong().getEl();
		Assert.assertTrue(WrongPasswordPrompt.isDisplayed());
	}

}