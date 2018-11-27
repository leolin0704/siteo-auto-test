package com.leo.MySiteTest.TestCases;

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
import com.leo.MySiteTest.TestCases.BaseChromeTester;

public class RolePageTest extends BaseChromeTester {

	String baseUrl = ConfigHelper.getBaseURL("/#/home");

	@Test
	public void AddRoles() {
		NavigationComponent navigation = new NavigationComponent(driver);
		CommonComponents commonCom = new CommonComponents(driver);
//		driver.get(baseUrl + "/");
		WebDriverWait waiter = new WebDriverWait(driver, 20);

		AdminLogin(navigation, commonCom, waiter);

		NavToRolePage(navigation, commonCom, waiter);

		RoleMainPage addRole = new RoleMainPage(driver);

		AddRole(addRole, commonCom, waiter);

		waiter.until(ExpectedConditions.presenceOfElementLocated(addRole.getQueryInput().getBy()));
		WebElement ResetBtn = addRole.getResetBtn().getEl();
		ResetBtn.click();
		WebElement QueryInput = addRole.getQueryInput().getEl();
		System.out.println("Find QueryInput!");
		QueryInput.sendKeys("Customer Service");
		WebElement QueryBtn = addRole.getQueryBtn().getEl();
		QueryBtn.click();

		WebElement RoleName = addRole.byATKey("Name").getEl();
		Assert.assertTrue("".equals(RoleName.getText()));

	}

	private RoleMainPage AddRole(RoleMainPage addRole, CommonComponents commonCom, WebDriverWait waiter) {
		WebElement addBtn = addRole.getAddNav().getEl();
		System.out.println("Find AddButton!");
		addBtn.click();

		waiter.until(ExpectedConditions.invisibilityOfElementLocated(commonCom.getLoading().getBy()));

		WebElement inputBox = addRole.getInputBox().getEl();
		waiter.until(ExpectedConditions.presenceOfElementLocated(addRole.getInputBox().getBy()));
		System.out.println("Find InputBox!");
		inputBox.sendKeys("Customer Service");

		WebElement CustomerCheckbox = addRole.getCustomerCheckbox().getEl();
		System.out.println("Find CustomerCheckbox!");
		CustomerCheckbox.click();

		WebElement Basic_InfoCheckbox = addRole.getBasic_InfoCheckbox().getEl();
		System.out.println("Find Basic_InfoCheckbox!");
		Basic_InfoCheckbox.click();

		WebElement SaveBtn = addRole.getSaveBtn().getEl();
		SaveBtn.click();
		waiter.until(ExpectedConditions.invisibilityOfElementLocated(commonCom.getDialog().getBy()));
		System.out.println("Add Role Successfully!");
		return addRole;
	}

	private void NavToRolePage(NavigationComponent navigation, CommonComponents commonCom, WebDriverWait waiter) {
		WebElement title = navigation.getSystemManageNavEl();
		title.click();
		System.out.println("System Manage click!");

		waiter.until(ExpectedConditions.presenceOfElementLocated(navigation.getRoleNavBy()));
		waiter.until(ExpectedConditions.visibilityOfElementLocated(navigation.getRoleNavBy()));

		WebElement role = navigation.getRoleNavEl();
		role.click();
		waiter.until(ExpectedConditions.invisibilityOfElementLocated(commonCom.getLoading().getBy()));
	}

	private void AdminLogin(NavigationComponent navigation, CommonComponents commonCom, WebDriverWait waiter) {
		LoginPage adminUser = new LoginPage(driver);
		adminUser.loadPage();
		WebElement inputAccount = adminUser.getUseNameInput().getEl();
		inputAccount.sendKeys("admin");
		WebElement inputPassword = adminUser.getPasswordInput().getEl();
		inputPassword.sendKeys("123123");
		WebElement login = adminUser.getLoginBtn().getEl();
		login.click();

		waiter.until(ExpectedConditions.presenceOfElementLocated(navigation.getSystemManageNavBy()));
		waiter.until(ExpectedConditions.visibilityOfElementLocated(navigation.getSystemManageNavBy()));
		waiter.until(ExpectedConditions.invisibilityOfElementLocated(commonCom.getMessage().getBy()));
	}
}
