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

		queryAddRole(addRole, commonCom, waiter);

		deleteAddRole(addRole, commonCom, waiter);
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
		WebElement title = navigation.getSystemManageNav().getEl();
		title.click();
		System.out.println("System Manage click!");

		waiter.until(ExpectedConditions.presenceOfElementLocated(navigation.getRoleNav().getBy()));
		waiter.until(ExpectedConditions.visibilityOfElementLocated(navigation.getRoleNav().getBy()));

		WebElement role = navigation.getRoleNav().getEl();
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

		waiter.until(ExpectedConditions.presenceOfElementLocated(navigation.getSystemManageNav().getBy()));
		waiter.until(ExpectedConditions.visibilityOfElementLocated(navigation.getSystemManageNav().getBy()));
		waiter.until(ExpectedConditions.invisibilityOfElementLocated(commonCom.getMessage().getBy()));
	}

	private void queryAddRole(RoleMainPage addRole, CommonComponents commonCom, WebDriverWait waiter) {
		waiter.until(ExpectedConditions.presenceOfElementLocated(addRole.getQueryInput().getBy()));
		WebElement ResetBtn = addRole.getResetBtn().getEl();
		ResetBtn.click();
		WebElement QueryInput = addRole.getQueryInput().getEl();
		System.out.println("Find QueryInput!");
		QueryInput.sendKeys("Customer Service");
		WebElement QueryBtn = addRole.getQueryBtn().getEl();
		QueryBtn.click();

		waiter.until(ExpectedConditions.invisibilityOfElementLocated(commonCom.getLoading().getBy()));

		WebElement RoleName = addRole.byATKey("Name").getEl();
		Assert.assertEquals("Customer Service", RoleName.getText());
		System.out.println("query successfully!");

		waiter.until(ExpectedConditions.invisibilityOfElementLocated(commonCom.getLoading().getBy()));
	}

	private void deleteAddRole(RoleMainPage addRole, CommonComponents commonCom, WebDriverWait waiter) {
		WebElement DeleteBtn = addRole.getDeleteBtn().getEl();
		System.out.println("find DeleteBtn successfully!");
		DeleteBtn.click();
		waiter.until(ExpectedConditions.presenceOfElementLocated(addRole.getPromp().getBy()));
		waiter.until(ExpectedConditions.visibilityOfElementLocated(addRole.getPromp().getBy()));
		WebElement PrompOkBtn = addRole.getPrompOkBtn().getEl();
		System.out.println("find PrompOkBtn successfully!");
		PrompOkBtn.click();
		waiter.until(ExpectedConditions.invisibilityOfElementLocated(commonCom.getLoading().getBy()));
		WebElement ResetBtn = addRole.getResetBtn().getEl();
		System.out.println("find ResetBtn successfully!");
		ResetBtn.click();
		waiter.until(ExpectedConditions.invisibilityOfElementLocated(commonCom.getLoading().getBy()));
		WebElement QueryInput = addRole.getQueryInput().getEl();
		System.out.println("find QueryInput successfully!");
		QueryInput.sendKeys("Customer Service");
		WebElement QueryBtn = addRole.getQueryBtn().getEl();
		System.out.println("find QueryBtn successfully!");
		QueryBtn.click();
		waiter.until(ExpectedConditions.invisibilityOfElementLocated(commonCom.getLoading().getBy()));
		WebElement NoDataSection = addRole.getNoDataSection().getEl();
		Assert.assertTrue(NoDataSection.isDisplayed());
	}
}
