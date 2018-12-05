package com.leo.MySiteTest.TestCases;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
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

	@Test
	public void AddRole() {
		String baseUrl = ConfigHelper.getBaseURL("/#/home");
		WebDriverWait waiter = new WebDriverWait(driver, 20);
		NavigationComponent navigation = new NavigationComponent(driver);
		RoleMainPage addRole = new RoleMainPage(driver);
		CommonComponents commonCom = new CommonComponents(driver);
		AdminLogin(navigation, commonCom, waiter);

		NavToRolePage(navigation, commonCom, waiter);

		AddRoleFailed(addRole, commonCom, waiter);

		AddRoleSucceed(addRole, commonCom, waiter);

		queryAddedRole(addRole, commonCom, waiter);

		addDuplicateRole(addRole, commonCom, waiter);

		deleteAddedRole(addRole, commonCom, waiter);

		queryDeletedRole(addRole, commonCom, waiter);

		queryALLRole(addRole, commonCom, waiter);

		deleteRoleWithUser(addRole, commonCom, waiter);

		viewSuperAdmin(addRole, commonCom, waiter);

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

	private void AddRole(RoleMainPage addRole, CommonComponents commonCom, WebDriverWait waiter) {
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

	}

	private void AddRoleFailed(RoleMainPage addRole, CommonComponents commonCom, WebDriverWait waiter) {
		AddRole(addRole, commonCom, waiter);
		WebElement CancelBtn = addRole.getCancelBtn().getEl();
		CancelBtn.click();
		WebElement RoleName = addRole.byATKey("Name").getEl();
		Assert.assertNotEquals("Customer Service", RoleName.getText());
		waiter.until(ExpectedConditions.invisibilityOfElementLocated(commonCom.getLoading().getBy()));
	}

	private RoleMainPage AddRoleSucceed(RoleMainPage addRole, CommonComponents commonCom, WebDriverWait waiter) {
		AddRole(addRole, commonCom, waiter);
		WebElement SaveBtn = addRole.getSaveBtn().getEl();
		SaveBtn.click();
		waiter.until(ExpectedConditions.invisibilityOfElementLocated(commonCom.getDialog().getBy()));
		System.out.println("Add Role Successfully!");
		return addRole;
	}

	private void queryAddedRole(RoleMainPage addRole, CommonComponents commonCom, WebDriverWait waiter) {
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

	private void dealWithAlert(RoleMainPage addRole, CommonComponents commonCom, WebDriverWait waiter) {
		WebElement SaveBtn = commonCom.getDialogSave().getEl();
		System.out.println("find SaveBtn!");
		waiter.until(ExpectedConditions.invisibilityOfElementLocated(commonCom.getLoading().getBy()));
		SaveBtn.click();
		waiter.until(ExpectedConditions.presenceOfElementLocated(commonCom.getAlertWindow().getBy()));
		waiter.until(ExpectedConditions.visibilityOfElementLocated(commonCom.getAlertWindow().getBy()));
		WebElement AlertWindow = commonCom.getAlertWindow().getEl();
		System.out.println("find AlertWindow!");
		Assert.assertTrue(AlertWindow.isDisplayed());
		WebElement CancelBtn = commonCom.getDialogCancel().getEl();
		System.out.println("find CancelBtn!");
		waiter.until(ExpectedConditions.presenceOfElementLocated(commonCom.getDialogCancel().getBy()));
		waiter.until(ExpectedConditions.visibilityOfElementLocated(commonCom.getDialogCancel().getBy()));
		waiter.until(ExpectedConditions.invisibilityOfElementLocated(commonCom.getLoading().getBy()));
		CancelBtn.click();
		waiter.until(ExpectedConditions.invisibilityOfElementLocated(commonCom.getLoading().getBy()));
	}

	private void addDuplicateRole(RoleMainPage addRole, CommonComponents commonCom, WebDriverWait waiter) {
		AddRole(addRole, commonCom, waiter);
		dealWithAlert(addRole, commonCom, waiter);
	}

	private void deleteAddedRole(RoleMainPage addRole, CommonComponents commonCom, WebDriverWait waiter) {
		WebElement DeleteBtn = addRole.getDeleteBtn().getEl();
		System.out.println("find DeleteBtn successfully!");
		DeleteBtn.click();
		waiter.until(ExpectedConditions.presenceOfElementLocated(addRole.getPromp().getBy()));
		waiter.until(ExpectedConditions.visibilityOfElementLocated(addRole.getPromp().getBy()));
		WebElement PrompOkBtn = addRole.getPrompOkBtn().getEl();
		System.out.println("find PrompOkBtn successfully!");
		PrompOkBtn.click();
		waiter.until(ExpectedConditions.invisibilityOfElementLocated(commonCom.getLoading().getBy()));
	}

	private void queryDeletedRole(RoleMainPage addRole, CommonComponents commonCom, WebDriverWait waiter) {
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

	private void queryALLRole(RoleMainPage addRole, CommonComponents commonCom, WebDriverWait waiter) {
		WebElement ResetBtn = addRole.getResetBtn().getEl();
		System.out.println("find ResetBtn successfully!");
		ResetBtn.click();
		waiter.until(ExpectedConditions.invisibilityOfElementLocated(commonCom.getLoading().getBy()));
		WebElement QueryBtn = addRole.getQueryBtn().getEl();
		System.out.println("find QueryBtn successfully!");
		QueryBtn.click();
		waiter.until(ExpectedConditions.invisibilityOfElementLocated(commonCom.getLoading().getBy()));
		WebElement superAdminRole = addRole.GetTableRow("Name", "Super Admin").getEl();
		Assert.assertTrue(superAdminRole.isDisplayed());
		WebElement dataManagerRole = addRole.GetTableRow("Name", "Data Manager").getEl();
		Assert.assertTrue(dataManagerRole.isDisplayed());
	}

	private void deleteRoleWithUser(RoleMainPage addRole, CommonComponents commonCom, WebDriverWait waiter) {
		WebElement getCheckBox = addRole.getCheckBox().getEl();
		getCheckBox.click();
		WebElement MultiDelete = addRole.getMultiDelete().getEl();
		MultiDelete.click();
		waiter.until(ExpectedConditions.presenceOfElementLocated(addRole.getPromp().getBy()));
		waiter.until(ExpectedConditions.visibilityOfElementLocated(addRole.getPromp().getBy()));
		WebElement PrompOkBtn = addRole.getPrompOkBtn().getEl();
		PrompOkBtn.click();
		waiter.until(ExpectedConditions.presenceOfElementLocated(commonCom.getAlertWindow().getBy()));
		waiter.until(ExpectedConditions.visibilityOfElementLocated(commonCom.getAlertWindow().getBy()));
		WebElement operationFailedAlert = commonCom.getAlertWindow().getEl();
		Assert.assertTrue(operationFailedAlert.isDisplayed());
		System.out.println("delete RoleWithUser failed!");
		waiter.until(ExpectedConditions.invisibilityOfElementLocated(commonCom.getLoading().getBy()));
	}

	private void viewSuperAdmin(RoleMainPage addRole, CommonComponents commonCom, WebDriverWait waiter) {
		WebElement viewBtn = addRole.getViewBtn().getEl();
		viewBtn.click();
		waiter.until(ExpectedConditions.presenceOfElementLocated(commonCom.getDialog().getBy()));
		waiter.until(ExpectedConditions.visibilityOfElementLocated(commonCom.getDialog().getBy()));

		try {
			synchronized (waiter) {
				waiter.wait(1000L);
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		waiter.until(ExpectedConditions.invisibilityOfElementLocated(commonCom.getLoading().getBy()));

		WebElement DisabledInput = addRole.getInputBox().getEl();
		System.out.println("find DisabledInput!");
		Assert.assertFalse(DisabledInput.isEnabled());
		WebElement DisabledCheckbox = addRole.getPermissionCheckBoxList().getEl();
		Assert.assertTrue(DisabledCheckbox.getAttribute("class").contains("is-disabled"));
		System.out.println("find DisabledCheckbox!");

		WebElement CancelBtn = commonCom.getDialogCancel().getEl(); // addRole.getCancelBtn().getEl();
		CancelBtn.click();

		System.out.println("click CancelBtn!");
		waiter.until(ExpectedConditions.invisibilityOfElementLocated(commonCom.getLoading().getBy()));
		WebElement superAdminRole = addRole.GetTableRow("Name", "Super Admin").getEl();
		WebElement editBtn = superAdminRole.findElement(By.cssSelector("[at-key=\"edit\"]"));
		editBtn.click();
		WebElement inputBox = addRole.getInputBox().getEl();
		waiter.until(ExpectedConditions.presenceOfElementLocated(addRole.getInputBox().getBy()));
		System.out.println("Find InputBox!");
		inputBox.clear();
		inputBox.sendKeys("Customer Service");
		dealWithAlert(addRole, commonCom, waiter);
		queryDeletedRole(addRole, commonCom, waiter);
	}
}
