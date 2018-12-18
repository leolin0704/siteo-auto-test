package com.leo.MySiteTest.TestCases;

import java.util.ArrayList;

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
import com.leo.MySiteTest.consts.PasswordConsts;
import com.leo.MySiteTest.consts.PermissionConsts;
import com.leo.MySiteTest.service.RoleService;
import com.leo.MySiteTest.service.UserService;

public class RolePageTest extends BaseChromeTester {

	RoleService roleService = new RoleService();

	UserService userService = new UserService();

	final String finalPassword = "4297F44B13955235245B2497399D7A93";

	@Test
	public void navToRolePage() {
		WebDriverWait waiter = new WebDriverWait(driver, 20);
		NavigationComponent navigation = new NavigationComponent(driver);
		CommonComponents commonCom = new CommonComponents(driver);

		adminLogin(navigation, commonCom, waiter);

		navToRolePage(navigation, commonCom, waiter);
	}

	@Test
	public void cancelAddRole() throws InterruptedException {

		WebDriverWait waiter = new WebDriverWait(driver, 20);
		NavigationComponent navigation = new NavigationComponent(driver);
		RoleMainPage addRole = new RoleMainPage(driver);
		CommonComponents commonCom = new CommonComponents(driver);

		adminLogin(navigation, commonCom, waiter);

		String baseUr2 = ConfigHelper.getBaseURL("/#/role");
		driver.get(baseUr2 + "/");

		addRole(addRole, commonCom, waiter, "Customer Service");
		cancelAddRole(addRole, commonCom, waiter);
	}

	@Test
	public void addRoleFailed() {
		WebDriverWait waiter = new WebDriverWait(driver, 20);
		NavigationComponent navigation = new NavigationComponent(driver);
		RoleMainPage addRole = new RoleMainPage(driver);
		CommonComponents commonCom = new CommonComponents(driver);

		adminLogin(navigation, commonCom, waiter);

		String baseUr2 = ConfigHelper.getBaseURL("/#/role");
		driver.get(baseUr2 + "/");

		addRoleFailed(addRole, commonCom, waiter);

	}

	@Test
	public void addRoleSucceed() {

		ArrayList<String> permissionList = new ArrayList<String>();
		permissionList.add(PermissionConsts.CUSTOMER);

		userService.InitUser("Andy", "Customer Service", PasswordConsts.PW_123123, permissionList);

		WebDriverWait waiter = new WebDriverWait(driver, 20);
		NavigationComponent navigation = new NavigationComponent(driver);
		RoleMainPage addRole = new RoleMainPage(driver);
		CommonComponents commonCom = new CommonComponents(driver);

		adminLogin(navigation, commonCom, waiter);

		String baseUr2 = ConfigHelper.getBaseURL("/#/role");
		driver.get(baseUr2 + "/");

		addRole(addRole, commonCom, waiter, "Customer Service");
		addRoleSucceed(addRole, commonCom, waiter);
	}

	@Test
	public void queryExistRole() {

		ArrayList<String> permissionList = new ArrayList<String>();
		permissionList.add(PermissionConsts.SYSTEM);
		permissionList.add(PermissionConsts.CONTENT);

		userService.InitUser("Lili", "Boss", PasswordConsts.PW_123123, permissionList);

		WebDriverWait waiter = new WebDriverWait(driver, 20);
		NavigationComponent navigation = new NavigationComponent(driver);
		RoleMainPage addRole = new RoleMainPage(driver);
		CommonComponents commonCom = new CommonComponents(driver);

		adminLogin(navigation, commonCom, waiter);
		String baseUr2 = ConfigHelper.getBaseURL("/#/role");
		driver.get(baseUr2 + "/");
		queryExistRole(addRole, commonCom, waiter, "Boss");
	}

	@Test

	public void addDuplicate() {

		ArrayList<String> permissionList = new ArrayList<String>();
		permissionList.add(PermissionConsts.BASIC_INFO);
		permissionList.add(PermissionConsts.CONTENT);

		userService.InitUser("Lili", "Market Analysis", PasswordConsts.PW_123123, permissionList);

		WebDriverWait waiter = new WebDriverWait(driver, 20);
		NavigationComponent navigation = new NavigationComponent(driver);
		RoleMainPage addRole = new RoleMainPage(driver);
		CommonComponents commonCom = new CommonComponents(driver);

		adminLogin(navigation, commonCom, waiter);
		String baseUr2 = ConfigHelper.getBaseURL("/#/role");
		driver.get(baseUr2 + "/");
		addDuplicateRole(addRole, commonCom, waiter);
	}

	@Test
	public void deleteRoleWithUser() {
		ArrayList<String> permissionList = new ArrayList<String>();
		permissionList.add(PermissionConsts.CUSTOMER);

		userService.InitUser("Andy", "Market Manager", PasswordConsts.PW_123123, permissionList);

		WebDriverWait waiter = new WebDriverWait(driver, 20);
		NavigationComponent navigation = new NavigationComponent(driver);
		RoleMainPage addRole = new RoleMainPage(driver);
		CommonComponents commonCom = new CommonComponents(driver);

		adminLogin(navigation, commonCom, waiter);
		String baseUr2 = ConfigHelper.getBaseURL("/#/role");
		driver.get(baseUr2 + "/");
		deleteRoleWithUser(addRole, commonCom, waiter, "Market Manager");
	}

	@Test
	public void viewSuperAdmin() {
		WebDriverWait waiter = new WebDriverWait(driver, 20);
		NavigationComponent navigation = new NavigationComponent(driver);
		RoleMainPage addRole = new RoleMainPage(driver);
		CommonComponents commonCom = new CommonComponents(driver);

		adminLogin(navigation, commonCom, waiter);
		String baseUr2 = ConfigHelper.getBaseURL("/#/role");
		driver.get(baseUr2 + "/");
		viewSuperAdmin(addRole, commonCom, waiter);
	}

	@Test
	public void editSuperAdmin() {
		WebDriverWait waiter = new WebDriverWait(driver, 20);
		NavigationComponent navigation = new NavigationComponent(driver);
		RoleMainPage addRole = new RoleMainPage(driver);
		CommonComponents commonCom = new CommonComponents(driver);

		adminLogin(navigation, commonCom, waiter);
		String baseUr2 = ConfigHelper.getBaseURL("/#/role");
		driver.get(baseUr2 + "/");
		editSuperAdmin(addRole, commonCom, waiter);
	}

	@Test
	public void editRoleFailed() {
		ArrayList<String> permissionList = new ArrayList<String>();
		permissionList.add(PermissionConsts.CUSTOMER);
		permissionList.add(PermissionConsts.BASIC_INFO);
		userService.InitUser("Andy", "DB Manager", PasswordConsts.PW_123123, permissionList);

		WebDriverWait waiter = new WebDriverWait(driver, 20);
		NavigationComponent navigation = new NavigationComponent(driver);
		RoleMainPage addRole = new RoleMainPage(driver);
		CommonComponents commonCom = new CommonComponents(driver);

		adminLogin(navigation, commonCom, waiter);
		String baseUr2 = ConfigHelper.getBaseURL("/#/role");
		driver.get(baseUr2 + "/");
		editRoleFailed(addRole, commonCom, waiter, "DB Manager");
	}

	@Test
	public void editRoleSuccessed() {

		ArrayList<String> permissionList = new ArrayList<String>();
		permissionList.add(PermissionConsts.CUSTOMER);
		permissionList.add(PermissionConsts.BASIC_INFO);

		userService.InitUser("Wanglili", "Background Maintain", PasswordConsts.PW_123123, permissionList);
		roleService.deleteRole("Leo", "Data Master");

		WebDriverWait waiter = new WebDriverWait(driver, 20);
		NavigationComponent navigation = new NavigationComponent(driver);
		RoleMainPage addRole = new RoleMainPage(driver);
		CommonComponents commonCom = new CommonComponents(driver);

		adminLogin(navigation, commonCom, waiter);
		String baseUr2 = ConfigHelper.getBaseURL("/#/role");
		driver.get(baseUr2 + "/");
		editRoleSuccessed(addRole, commonCom, waiter, navigation, "Background Maintain", "Data Master");
	}

	@Test
	public void deleteRole() {
		ArrayList<String> permissionList = new ArrayList<String>();
		permissionList.add(PermissionConsts.CUSTOMER);
		permissionList.add(PermissionConsts.BASIC_INFO);
		roleService.deleteRole("Leo", "Supervisor");
		roleService.initRole("Supervisor", permissionList);

		WebDriverWait waiter = new WebDriverWait(driver, 20);
		NavigationComponent navigation = new NavigationComponent(driver);
		RoleMainPage addRole = new RoleMainPage(driver);
		CommonComponents commonCom = new CommonComponents(driver);

		adminLogin(navigation, commonCom, waiter);
		String baseUr2 = ConfigHelper.getBaseURL("/#/role");
		driver.get(baseUr2 + "/");

		deleteRole(addRole, commonCom, waiter, "Supervisor");
	}

	private void adminLogin(NavigationComponent navigation, CommonComponents commonCom, WebDriverWait waiter) {
		LoginPage adminUser = new LoginPage(driver);
		adminUser.UserLogin("admin", "123123", commonCom, waiter);

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

	private void addRole(RoleMainPage addRole, CommonComponents commonCom, WebDriverWait waiter, String name) {
		waiter.until(ExpectedConditions.invisibilityOfElementLocated(commonCom.getLoading().getBy()));
		WebElement addBtn = addRole.getAddNav().getEl();
		System.out.println("Find AddButton!");
		addBtn.click();

		waiter.until(ExpectedConditions.invisibilityOfElementLocated(commonCom.getLoading().getBy()));

		WebElement inputBox = addRole.getInputBox().getEl();
		waiter.until(ExpectedConditions.presenceOfElementLocated(addRole.getInputBox().getBy()));
		System.out.println("Find InputBox!");
		inputBox.sendKeys(name);

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
		waiter.until(ExpectedConditions.invisibilityOfElementLocated(commonCom.getLoading().getBy()));
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
		WebElement SaveBtn = addRole.getSaveBtn().getEl();
		SaveBtn.click();
		System.out.println("Add Role Successfully!");
		return addRole;
	}

	private void queryExistRole(RoleMainPage addRole, CommonComponents commonCom, WebDriverWait waiter, String name) {
		waiter.until(ExpectedConditions.invisibilityOfElementLocated(commonCom.getLoading().getBy()));
		WebElement QueryInput = addRole.getQueryInput().getEl();
		System.out.println("Find QueryInput!");
		QueryInput.sendKeys(name);
		WebElement QueryBtn = addRole.getQueryBtn().getEl();
		QueryBtn.click();

		waiter.until(ExpectedConditions.invisibilityOfElementLocated(commonCom.getLoading().getBy()));

		WebElement RoleName = addRole.byATKey("Name").getEl();
		Assert.assertEquals(name, RoleName.getText());
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
		addRole(addRole, commonCom, waiter, "Market Analysis");
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

	private void deleteRoleWithUser(RoleMainPage addRole, CommonComponents commonCom, WebDriverWait waiter,
			String roleName) {
		waiter.until(ExpectedConditions.invisibilityOfElementLocated(commonCom.getLoading().getBy()));
		WebElement TableRole = addRole.GetTableRow("Name", roleName).getEl();
		WebElement getCheckBox = TableRole.findElement(By.className("el-checkbox__inner"));
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
	}

	private void viewSuperAdmin(RoleMainPage addRole, CommonComponents commonCom, WebDriverWait waiter) {
		waiter.until(ExpectedConditions.invisibilityOfElementLocated(commonCom.getLoading().getBy()));
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
	}

	private void editSuperAdmin(RoleMainPage addRole, CommonComponents commonCom, WebDriverWait waiter) {
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
	}

	public void editRoleFailed(RoleMainPage addRole, CommonComponents commonCom, WebDriverWait waiter,
			String roleName) {
		waiter.until(ExpectedConditions.invisibilityOfElementLocated(commonCom.getLoading().getBy()));
		WebElement RoleTable = addRole.GetTableRow("Name", roleName).getEl();
		WebElement editBtn = RoleTable.findElement(By.cssSelector("[at-key=\"edit\"]"));
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

	public void editRoleSuccessed(RoleMainPage addRole, CommonComponents commonCom, WebDriverWait waiter,
			NavigationComponent navigation, String roleName, String editRoleName) {
		waiter.until(ExpectedConditions.invisibilityOfElementLocated(commonCom.getLoading().getBy()));
		WebElement RoleTable = addRole.GetTableRow("Name", roleName).getEl();
		WebElement editBtn = RoleTable.findElement(By.cssSelector("[at-key=\"edit\"]"));
		editBtn.click();
		waiter.until(ExpectedConditions.invisibilityOfElementLocated(commonCom.getLoading().getBy()));
		WebElement inputBox = addRole.getInputBox().getEl();
		waiter.until(ExpectedConditions.presenceOfElementLocated(addRole.getInputBox().getBy()));
		inputBox.sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.DELETE);
		inputBox = addRole.getInputBox().getEl();
		inputBox.sendKeys(editRoleName);
		WebElement SystemCheckbox = addRole.getSystemCheckbox().getEl();
		System.out.println("Find SystemCheckbox!");
		SystemCheckbox.click();
		WebElement Basic_InfoCheckbox = addRole.getBasic_InfoCheckbox().getEl();
		System.out.println("Find Basic_InfoCheckbox!");
		Basic_InfoCheckbox.click();
		WebElement saveBtn = addRole.getSaveBtn().getEl();
		saveBtn.click();
		waiter.until(ExpectedConditions.invisibilityOfElementLocated(commonCom.getLoading().getBy()));
		String baseUr2 = ConfigHelper.getBaseURL("/#/user");
		driver.get(baseUr2 + "/");
		waiter.until(ExpectedConditions.invisibilityOfElementLocated(commonCom.getLoading().getBy()));
		WebElement WangliliRow = addRole.GetTableRow("Account", "Wanglili").getEl();
		WebElement RoleName = WangliliRow.findElement(By.cssSelector("[at-key=\"RoleName\"]"));
		Assert.assertEquals(editRoleName, RoleName.getText());
		LoginPage adminUser = new LoginPage(driver);
		adminUser.UserLogin("Wanglili", "123123", commonCom, waiter);
		WebElement Systemtitle = navigation.getSystemManageNav().getEl();
		Assert.assertTrue(Systemtitle.isDisplayed());
		WebElement Customertitle = navigation.getCustomerNav().getEl();
		Assert.assertTrue(Customertitle.isDisplayed());
	}

	private void deleteRole(RoleMainPage addRole, CommonComponents commonCom, WebDriverWait waiter, String roleName) {
		waiter.until(ExpectedConditions.invisibilityOfElementLocated(commonCom.getLoading().getBy()));
		WebElement RoleTable = addRole.GetTableRow("Name", roleName).getEl();
		WebElement deleteBtn = RoleTable.findElement(By.cssSelector("[at-key=\"delete\"]"));
		deleteBtn.click();
		waiter.until(ExpectedConditions.presenceOfElementLocated(addRole.getPromp().getBy()));
		waiter.until(ExpectedConditions.visibilityOfElementLocated(addRole.getPromp().getBy()));
		WebElement PrompOkBtn = addRole.getPrompOkBtn().getEl();
		System.out.println("find PrompOkBtn successfully!");
		PrompOkBtn.click();
		waiter.until(ExpectedConditions.invisibilityOfElementLocated(commonCom.getLoading().getBy()));
		WebElement QueryInput = addRole.getQueryInput().getEl();
		System.out.println("find QueryInput successfully!");
		QueryInput.sendKeys(roleName);
		WebElement QueryBtn = addRole.getQueryBtn().getEl();
		System.out.println("find QueryBtn successfully!");
		QueryBtn.click();
		waiter.until(ExpectedConditions.invisibilityOfElementLocated(commonCom.getLoading().getBy()));
		WebElement NoDataSection = addRole.getNoDataSection().getEl();
		Assert.assertTrue(NoDataSection.isDisplayed());
	}
}
