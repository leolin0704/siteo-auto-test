package com.leo.MySiteTest.TestCases;

import org.apache.ibatis.session.SqlSession;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.leo.MySiteTest.Common.ConfigHelper;
import com.leo.MySiteTest.Models.CommonComponents;
import com.leo.MySiteTest.Models.NavigationComponent;
import com.leo.MySiteTest.Models.Login.LoginPage;
import com.leo.MySiteTest.Models.Role.RoleMainPage;
import com.leo.MySiteTest.TestCases.BaseChromeTester;
import com.leo.MySiteTest.tool.DBTools;

public class RolePageTest extends BaseChromeTester {

	@Test
	public void AddRole() throws InterruptedException {

//		synchronized (driver) {
//
//			SqlSession session = DBTools.getSession();
//			SiteoDao mapper = session.getMapper(SiteoDao.class);
//			try {
//				mapper.deleteCustomerService();
//				session.commit();
//			} catch (Exception e) {
//				e.printStackTrace();
//				session.rollback();
//				Assert.fail();
//			}
//
//			try {
//				mapper.EditDataManager();
//				session.commit();
//			} catch (Exception e) {
//				e.printStackTrace();
//				session.rollback();
//				Assert.fail();
//			}
//		}

		WebDriverWait waiter = new WebDriverWait(driver, 20);
		NavigationComponent navigation = new NavigationComponent(driver);
		RoleMainPage addRole = new RoleMainPage(driver);
		CommonComponents commonCom = new CommonComponents(driver);

		adminLogin(navigation, commonCom, waiter);

		navToRolePage(navigation, commonCom, waiter);

		addRole(addRole, commonCom, waiter);

		cancelAddRole(addRole, commonCom, waiter);

		addRoleFailed(addRole, commonCom, waiter);

		addRoleSucceed(addRole, commonCom, waiter);

		queryAddedRole(addRole, commonCom, waiter);

		addDuplicateRole(addRole, commonCom, waiter);

		queryALLRole(addRole, commonCom, waiter);

		deleteRoleWithUser(addRole, commonCom, waiter);

		viewSuperAdmin(addRole, commonCom, waiter);

		editSuperAdmin(addRole, commonCom, waiter);

		editRoleFailed(addRole, commonCom, waiter);

//		editRoleSuccessed(addRole, commonCom, waiter, navigation);

		deleteAddedRole(addRole, commonCom, waiter);

		queryDeletedRole(addRole, commonCom, waiter);
	}

	private void adminLogin(NavigationComponent navigation, CommonComponents commonCom, WebDriverWait waiter) {
		LoginPage adminUser = new LoginPage(driver);
		adminUser.UserLogin("admin", "123123");

		waiter.until(ExpectedConditions.presenceOfElementLocated(navigation.getSystemManageNav().getBy()));
		waiter.until(ExpectedConditions.visibilityOfElementLocated(navigation.getSystemManageNav().getBy()));
		waiter.until(ExpectedConditions.invisibilityOfElementLocated(commonCom.getMessage().getBy()));
	}

	private void navToRolePage(NavigationComponent navigation, CommonComponents commonCom, WebDriverWait waiter) {
		WebElement title = navigation.getSystemManageNav().getEl();
		title.click();
		System.out.println("System Manage click!");

		waiter.until(ExpectedConditions.presenceOfElementLocated(navigation.getRoleNav().getBy()));
		waiter.until(ExpectedConditions.visibilityOfElementLocated(navigation.getRoleNav().getBy()));

		WebElement role = navigation.getRoleNav().getEl();
		role.click();
		waiter.until(ExpectedConditions.invisibilityOfElementLocated(commonCom.getLoading().getBy()));
	}

	private void addRole(RoleMainPage addRole, CommonComponents commonCom, WebDriverWait waiter) {
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
		waiter.until(ExpectedConditions.invisibilityOfElementLocated(commonCom.getLoading().getBy()));
	}

	private void cancelAddRole(RoleMainPage addRole, CommonComponents commonCom, WebDriverWait waiter) {
		waiter.until(ExpectedConditions.visibilityOfElementLocated(addRole.getCancelBtn().getBy()));
		WebElement CancelBtn = addRole.getCancelBtn().getEl();
		CancelBtn.click();
		waiter.until(ExpectedConditions.invisibilityOfElementLocated(commonCom.getLoading().getBy()));
		WebElement RoleName = addRole.byATKey("Name").getEl();
		Assert.assertNotEquals("Customer Service", RoleName.getText());
	}

	private void addRoleFailed(RoleMainPage addRole, CommonComponents commonCom, WebDriverWait waiter) {
		WebElement addBtn = addRole.getAddNav().getEl();
		System.out.println("Find AddButton!");
		addBtn.click();

		waiter.until(ExpectedConditions.invisibilityOfElementLocated(commonCom.getLoading().getBy()));

		WebElement saveBtn = addRole.getSaveBtn().getEl();
		saveBtn.click();
		waiter.until(ExpectedConditions.invisibilityOfElementLocated(commonCom.getLoading().getBy()));
		WebElement permissionErrorPrompt = commonCom.GetErrorPrompt("Permission is required.").getEl();
		System.out.println("Find permissionErrorPrompt!");
		Assert.assertTrue(permissionErrorPrompt.isDisplayed());
		WebElement nameErrorPrompt = commonCom.GetErrorPrompt("Name is required.").getEl();
		System.out.println("Find nameErrorPrompt!");
		Assert.assertTrue(nameErrorPrompt.isDisplayed());
		WebElement inputBox = addRole.getInputBox().getEl();
		System.out.println("Find inputBox!");
		inputBox.sendKeys("1");
		WebElement dialogeHead = driver.findElement(By.className("el-dialog__header"));
		dialogeHead.click();
		WebElement StringLimitPrompt = commonCom.GetErrorPrompt("Name length should between 2 to 50.").getEl();
		System.out.println("Find permissionErrorPrompt!");
		Assert.assertTrue(StringLimitPrompt.isDisplayed());
		inputBox = addRole.getInputBox().getEl();
		System.out.println("Find inputBox!");
		for (int i = 1; i <= 50; i++) {
			inputBox.sendKeys("1");
		}
		dialogeHead = driver.findElement(By.className("el-dialog__header"));
		dialogeHead.click();
		StringLimitPrompt = commonCom.GetErrorPrompt("Name length should between 2 to 50.").getEl();
		System.out.println("Find permissionErrorPrompt!");
		Assert.assertTrue(StringLimitPrompt.isDisplayed());
		inputBox = addRole.getInputBox().getEl();
		System.out.println("Find inputBox!");
		inputBox.sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.DELETE);
		inputBox.sendKeys("/()*&^%$#@!+<>?|{}");
		dialogeHead = driver.findElement(By.className("el-dialog__header"));
		dialogeHead.click();
		WebElement StringValidationPrompt = commonCom
				.GetErrorPrompt("Name may only contain alphanumeric characters, underline or single hyphens.").getEl();
		System.out.println("Find StringValidationPrompt!");
		Assert.assertTrue(StringValidationPrompt.isDisplayed());
		inputBox = addRole.getInputBox().getEl();
		System.out.println("Find inputBox!");
		inputBox.sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.DELETE);
		inputBox.sendKeys("汉字");
		dialogeHead = driver.findElement(By.className("el-dialog__header"));
		dialogeHead.click();
		StringValidationPrompt = commonCom
				.GetErrorPrompt("Name may only contain alphanumeric characters, underline or single hyphens.").getEl();
		System.out.println("Find StringValidationPrompt!");
		Assert.assertTrue(StringValidationPrompt.isDisplayed());
		inputBox = addRole.getInputBox().getEl();
		System.out.println("Find inputBox!");
		inputBox.sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.DELETE);
		inputBox.sendKeys("-_");
		dialogeHead = driver.findElement(By.className("el-dialog__header"));
		dialogeHead.click();
		try {
			StringValidationPrompt = commonCom
					.GetErrorPrompt("Name may only contain alphanumeric characters, underline or single hyphens.")
					.getEl();
			System.out.println("Find StringValidationPrompt!");
		} catch (Exception e) {
			System.out.println("'" + StringValidationPrompt + "' doesn't exist!");
		}
	}

	private RoleMainPage addRoleSucceed(RoleMainPage addRole, CommonComponents commonCom, WebDriverWait waiter) {
		WebElement inputBox = addRole.getInputBox().getEl();
		waiter.until(ExpectedConditions.presenceOfElementLocated(addRole.getInputBox().getBy()));
		inputBox.sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.DELETE);
		inputBox.sendKeys("Customer Service");
		WebElement CustomerCheckbox = addRole.getCustomerCheckbox().getEl();
		System.out.println("Find CustomerCheckbox!");
		CustomerCheckbox.click();
		WebElement Basic_InfoCheckbox = addRole.getBasic_InfoCheckbox().getEl();
		System.out.println("Find Basic_InfoCheckbox!");
		Basic_InfoCheckbox.click();
		waiter.until(ExpectedConditions.invisibilityOfElementLocated(commonCom.getLoading().getBy()));
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
		waiter.until(ExpectedConditions.presenceOfElementLocated(addRole.getSaveBtn().getBy()));
		waiter.until(ExpectedConditions.visibilityOfElementLocated(addRole.getSaveBtn().getBy()));
		WebElement saveBtn = commonCom.getDialogSave().getEl();
		System.out.println("find SaveBtn!");
		waiter.until(ExpectedConditions.invisibilityOfElementLocated(commonCom.getLoading().getBy()));
		saveBtn.click();
		waiter.until(ExpectedConditions.presenceOfElementLocated(commonCom.getAlertWindow().getBy()));
		waiter.until(ExpectedConditions.visibilityOfElementLocated(commonCom.getAlertWindow().getBy()));
		WebElement AlertWindow = commonCom.getAlertWindow().getEl();
		System.out.println("find AlertWindow!");
		Assert.assertTrue(AlertWindow.isDisplayed());
		WebElement CancelBtn = commonCom.getDialogCancel().getEl();
		waiter.until(ExpectedConditions.invisibilityOfElementLocated(commonCom.getLoading().getBy()));
		CancelBtn.click();
		waiter.until(ExpectedConditions.invisibilityOfElementLocated(commonCom.getLoading().getBy()));
	}

	private void addDuplicateRole(RoleMainPage addRole, CommonComponents commonCom, WebDriverWait waiter) {
		addRole(addRole, commonCom, waiter);
		dealWithAlert(addRole, commonCom, waiter);
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
		WebElement dataManagerRole = addRole.GetTableRow("Name", "Customer Service").getEl();
		Assert.assertTrue(dataManagerRole.isDisplayed());
	}

	private void deleteRoleWithUser(RoleMainPage addRole, CommonComponents commonCom, WebDriverWait waiter) {
		WebElement superAdminRole = addRole.GetTableRow("Name", "Super Admin").getEl();
		WebElement getCheckBox = superAdminRole.findElement(By.className("el-checkbox__inner"));
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
		WebElement superAdminRole = addRole.GetTableRow("Name", "Super Admin").getEl();
		WebElement viewBtn = superAdminRole.findElement(By.cssSelector("[at-key=\"view\"]"));
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
		WebElement CancelBtn = commonCom.getDialogCancel().getEl();
		CancelBtn.click();
		System.out.println("click CancelBtn!");
		waiter.until(ExpectedConditions.invisibilityOfElementLocated(commonCom.getLoading().getBy()));
	}

	private void editSuperAdmin(RoleMainPage addRole, CommonComponents commonCom, WebDriverWait waiter) {
		WebElement superAdminRole = addRole.GetTableRow("Name", "Super Admin").getEl();
		WebElement editBtn = superAdminRole.findElement(By.cssSelector("[at-key=\"edit\"]"));
		editBtn.click();
		WebElement inputBox = addRole.getInputBox().getEl();
		waiter.until(ExpectedConditions.presenceOfElementLocated(addRole.getInputBox().getBy()));
		System.out.println("Find InputBox!");
		inputBox.clear();
		inputBox.sendKeys("Customer Service");
		dealWithAlert(addRole, commonCom, waiter);
	}

	public void editRoleFailed(RoleMainPage addRole, CommonComponents commonCom, WebDriverWait waiter) {
		WebElement customerServiceRole = addRole.GetTableRow("Name", "Customer Service").getEl();
		WebElement editBtn = customerServiceRole.findElement(By.cssSelector("[at-key=\"edit\"]"));
		editBtn.click();
		waiter.until(ExpectedConditions.invisibilityOfElementLocated(commonCom.getLoading().getBy()));
		WebElement inputBox = addRole.getInputBox().getEl();
		System.out.println("Find inputBox!");
		inputBox.sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.DELETE);
		WebElement CustomerCheckbox = addRole.getCustomerCheckbox().getEl();
		System.out.println("Find CustomerCheckbo!");
		CustomerCheckbox.click();
		WebElement Basic_InfoCheckbox = addRole.getBasic_InfoCheckbox().getEl();
		System.out.println("Find Basic_InfoCheckbox!");
		Basic_InfoCheckbox.click();
		WebElement saveBtn = addRole.getSaveBtn().getEl();
		saveBtn.click();
		waiter.until(ExpectedConditions.invisibilityOfElementLocated(commonCom.getLoading().getBy()));
		WebElement permissionErrorPrompt = commonCom.GetErrorPrompt("Permission is required.").getEl();
		System.out.println("Find permissionErrorPrompt!");
		Assert.assertTrue(permissionErrorPrompt.isDisplayed());
		WebElement nameErrorPrompt = commonCom.GetErrorPrompt("Name is required.").getEl();
		System.out.println("Find nameErrorPrompt!");
		Assert.assertTrue(nameErrorPrompt.isDisplayed());
		inputBox = addRole.getInputBox().getEl();
		System.out.println("Find inputBox!");
		inputBox.sendKeys("1");
		WebElement windowHead = driver.findElement(By.className("el-dialog__header"));
		windowHead.click();
		WebElement StringLimitPrompt = commonCom.GetErrorPrompt("Name length should between 2 to 50.").getEl();
		System.out.println("Find permissionErrorPrompt!");
		Assert.assertTrue(StringLimitPrompt.isDisplayed());
		inputBox = addRole.getInputBox().getEl();
		System.out.println("Find inputBox!");
		for (int i = 1; i <= 50; i++) {
			inputBox.sendKeys("1");
		}
		windowHead = driver.findElement(By.className("el-dialog__header"));
		windowHead.click();
		StringLimitPrompt = commonCom.GetErrorPrompt("Name length should between 2 to 50.").getEl();
		System.out.println("Find permissionErrorPrompt!");
		Assert.assertTrue(StringLimitPrompt.isDisplayed());
		inputBox = addRole.getInputBox().getEl();
		System.out.println("Find inputBox!");
		inputBox.sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.DELETE);
		inputBox.sendKeys("汉字");
		windowHead = driver.findElement(By.className("el-dialog__header"));
		windowHead.click();
		StringLimitPrompt = commonCom
				.GetErrorPrompt("Name may only contain alphanumeric characters, underline or single hyphens").getEl();
		System.out.println("Find permissionErrorPrompt!");
		Assert.assertTrue(StringLimitPrompt.isDisplayed());
		inputBox = addRole.getInputBox().getEl();
		System.out.println("Find inputBox!");
		inputBox.sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.DELETE);
		inputBox.sendKeys("~！@#￥%……&*（）+=？<>{}:");
		windowHead = driver.findElement(By.className("el-dialog__header"));
		windowHead.click();
		StringLimitPrompt = commonCom
				.GetErrorPrompt("Name may only contain alphanumeric characters, underline or single hyphens").getEl();
		System.out.println("Find permissionErrorPrompt!");
		Assert.assertTrue(StringLimitPrompt.isDisplayed());
		inputBox = addRole.getInputBox().getEl();
		System.out.println("Find inputBox!");
		inputBox.sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.DELETE);
		inputBox.sendKeys("~！@#￥%……&*（）+=？<>{}:");
		windowHead = driver.findElement(By.className("el-dialog__header"));
		windowHead.click();
		StringLimitPrompt = commonCom
				.GetErrorPrompt("Name may only contain alphanumeric characters, underline or single hyphens").getEl();
		System.out.println("Find permissionErrorPrompt!");
		Assert.assertTrue(StringLimitPrompt.isDisplayed());
		inputBox = addRole.getInputBox().getEl();
		System.out.println("Find inputBox!");
		inputBox.sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.DELETE);
		inputBox.sendKeys("-_");
		windowHead = driver.findElement(By.className("el-dialog__header"));
		windowHead.click();

		try {
			StringLimitPrompt = commonCom
					.GetErrorPrompt("Name may only contain alphanumeric characters, underline or single hyphens")
					.getEl();
			System.out.println("Find StringLimitPrompt!");
		} catch (Exception e) {
			System.out.println("No Such An Element!");
		}
		WebElement CancelBtn = commonCom.getDialogCancel().getEl();
		CancelBtn.click();
		waiter.until(ExpectedConditions.invisibilityOfElementLocated(commonCom.getLoading().getBy()));
	}

//	public void editRoleSuccessed(RoleMainPage addRole, CommonComponents commonCom, WebDriverWait waiter,
//			NavigationComponent navigation) {
//		WebElement inputBox = addRole.getInputBox().getEl();
//		waiter.until(ExpectedConditions.presenceOfElementLocated(addRole.getInputBox().getBy()));
//		inputBox.sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.DELETE);
//		inputBox.sendKeys("Customer Service");
//		WebElement saveBtn = addRole.getSaveBtn().getEl();
//		saveBtn.click();
//		waiter.until(ExpectedConditions.invisibilityOfElementLocated(commonCom.getLoading().getBy()));
//		String baseUr2 = ConfigHelper.getBaseURL("/#/user");
//		driver.get(baseUr2 + "/");
//		waiter.until(ExpectedConditions.invisibilityOfElementLocated(commonCom.getLoading().getBy()));
//		WebElement WangliliRow = addRole.GetTableRow("Account", "Wanglili").getEl();
//		WebElement RoleName = WangliliRow.findElement(By.cssSelector("[at-key=\"RoleName\"]"));
//		Assert.assertEquals("Customer Service", RoleName.getText());
//		LoginPage adminUser = new LoginPage(driver);
//		adminUser.UserLogin("Wanglili", "1991325");
//		waiter.until(ExpectedConditions.invisibilityOfElementLocated(commonCom.getLoading().getBy()));
//		WebElement Systemtitle = navigation.getSystemManageNav().getEl();
//		Assert.assertTrue(Systemtitle.isDisplayed());
//		WebElement Customertitle = navigation.getCustomerNav().getEl();
//		Assert.assertTrue(Customertitle.isDisplayed());
//	}

	private void deleteAddedRole(RoleMainPage addRole, CommonComponents commonCom, WebDriverWait waiter) {
		WebElement customerServiceRole = addRole.GetTableRow("Name", "Customer Service").getEl();
		WebElement deleteBtn = customerServiceRole.findElement(By.cssSelector("[at-key=\"delete\"]"));
		deleteBtn.click();
		waiter.until(ExpectedConditions.presenceOfElementLocated(addRole.getPromp().getBy()));
		waiter.until(ExpectedConditions.visibilityOfElementLocated(addRole.getPromp().getBy()));
		WebElement PrompOkBtn = addRole.getPrompOkBtn().getEl();
		System.out.println("find PrompOkBtn successfully!");
		PrompOkBtn.click();
		waiter.until(ExpectedConditions.invisibilityOfElementLocated(commonCom.getLoading().getBy()));
	}

	private void queryDeletedRole(RoleMainPage addRole, CommonComponents commonCom, WebDriverWait waiter) {
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
