package com.leo.MySiteTest.TestCases;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.leo.MySiteTest.Common.ConfigHelper;
import com.leo.MySiteTest.Models.CommonComponents;
import com.leo.MySiteTest.Models.NavigationComponent;
import com.leo.MySiteTest.Models.Login.LoginPage;
import com.leo.MySiteTest.Models.MainPage.MainPage;
import com.leo.MySiteTest.consts.PasswordConsts;
import com.leo.MySiteTest.consts.PermissionConsts;
import com.leo.MySiteTest.service.RoleService;
import com.leo.MySiteTest.service.UserService;

public class LoginTest extends BaseTester {

	RoleService roleService = new RoleService();

	UserService userService = new UserService();

	String baseUrl = ConfigHelper.getBaseURL("/#/login");

	final String finalPassword = "4297F44B13955235245B2497399D7A93";

	@Test
	public void Login() throws Exception {
		WebDriverWait waiter = new WebDriverWait(driver, 20);
		NavigationComponent navigation = new NavigationComponent(driver);
		CommonComponents commonCom = new CommonComponents(driver);

		ArrayList<String> permissionList = new ArrayList<String>();
		permissionList.add(PermissionConsts.CUSTOMER);
		permissionList.add(PermissionConsts.SYSTEM);

		userService.InitUser("Andy", "DB Manager", PasswordConsts.PW_123123, permissionList);
		driver.get(baseUrl + "/");
		LoginPage adminUser = new LoginPage(driver);
		adminUser.UserLogin("Andy", "123123", commonCom, waiter);
		waiter.until(ExpectedConditions.invisibilityOfElementLocated(commonCom.getLoading().getBy()));
		WebElement Systemtitle = navigation.getTitleNav("System Manage").getEl();
		Assert.assertTrue(Systemtitle.isDisplayed());
		WebElement Customertitle = navigation.getTitleNav("Customer").getEl();
		Assert.assertTrue(Customertitle.isDisplayed());
		try {
			WebElement Basic_InfoTitle = navigation.getTitleNav("Basic Info").getEl();

			System.out.println("Basic_InfoTitle exists");
		} catch (Exception e) {
			System.out.println("Basic_InfoTitle doesn't exist!");

		}
		try {
			WebElement ContentTitle = navigation.getTitleNav("Content").getEl();

			System.out.println("ContentTitle exists");
		} catch (Exception e) {
			System.out.println("ContentTitle doesn't exist!");

		}
	}

	@Test
	public void LoginWithNoAcconut() {
		WebDriverWait waiter = new WebDriverWait(driver, 20);
		CommonComponents commonCom = new CommonComponents(driver);
		driver.get(baseUrl + "/");
		LoginPage adminUser = new LoginPage(driver);
		adminUser.UserLogin("", "123123", commonCom, waiter);
		WebElement NoAccountPrompt = adminUser.UserNameWrong().getEl();
		Assert.assertTrue(NoAccountPrompt.isDisplayed());
	}

	@Test
	public void LoginWithNoPassword() {
		WebDriverWait waiter = new WebDriverWait(driver, 20);
		CommonComponents commonCom = new CommonComponents(driver);
		driver.get(baseUrl + "/");
		LoginPage adminUser = new LoginPage(driver);
		adminUser.UserLogin("admin", "", commonCom, waiter);
		WebElement NoPasswordPrompt = adminUser.PasswordWrong().getEl();
		Assert.assertTrue(NoPasswordPrompt.isDisplayed());
	}

	@Test
	public void LoginWithWrongPassword() {
		WebDriverWait waiter = new WebDriverWait(driver, 20);
		CommonComponents commonCom = new CommonComponents(driver);
		driver.get(baseUrl + "/");
		LoginPage adminUser = new LoginPage(driver);
		adminUser.UserLogin("admin", "456", commonCom, waiter);
		waiter.until(ExpectedConditions.invisibilityOfElementLocated(commonCom.getLoading().getBy()));
		WebElement WrongPasswordPrompt = adminUser.PasswordOrUsernameWrong().getEl();
		Assert.assertTrue(WrongPasswordPrompt.isDisplayed());
	}

	@Test

	public void checkMessageBox() {
		ArrayList<String> PermissionList = new ArrayList<String>();
		PermissionList.add(PermissionConsts.CUSTOMER);
		PermissionList.add(PermissionConsts.BASIC_INFO);

		userService.InitUser("test", "Manager", finalPassword, PermissionList);

		WebDriverWait waiter = new WebDriverWait(driver, 20);
		CommonComponents commonCom = new CommonComponents(driver);
		driver.get(baseUrl + "/");
		LoginPage adminUser = new LoginPage(driver);
		adminUser.UserLogin("test", "123123", commonCom, waiter);
		waiter.until(ExpectedConditions.invisibilityOfElementLocated(commonCom.getLoading().getBy()));
		MainPage mainPage = new MainPage(driver);
		WebElement messageBoxAccount = mainPage.getMessageBoxAccount("test").getEl();
		System.out.println("Find messageBoxAccount!");
		Assert.assertTrue(messageBoxAccount.isDisplayed());
		WebElement messageBoxRole = mainPage.getMessageBoxRole("Manager").getEl();
		System.out.println("Find messageBoxAccount!");
		Assert.assertTrue(messageBoxRole.isDisplayed());
		String systemTime = driver
				.findElement(
						By.xpath("//div[@class='el-row']/div[contains(text(),'Login Date')]/following-sibling::div"))
				.getText();
		System.out.println("系统时间：" + systemTime);

		try {
			SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date systemDate = dateformat.parse(systemTime);
			System.out.println(systemDate);
			Date realDate = new Date();
			System.out.println(realDate);
			long diff = realDate.getTime() - systemDate.getTime();
			long minutes = diff / 1000 / 60;
			Assert.assertTrue(minutes <= 2);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Something Wrong");
		}

		WebElement accountBtn = mainPage.getAccountBtn("test").getEl();
		System.out.println("Find accountBtn!");
		Assert.assertTrue(accountBtn.isDisplayed());
	}
}