package com.leo.MySiteTest.TestCases;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.leo.MySiteTest.Common.ConfigHelper;
import com.leo.MySiteTest.Models.CommonComponents;
import com.leo.MySiteTest.Models.NavigationComponent;
import com.leo.MySiteTest.Models.Login.LoginPage;
import com.leo.MySiteTest.Models.Role.RoleMainPage;
import com.leo.MySiteTest.Models.User.UserMainPage;
import com.leo.MySiteTest.consts.PasswordConsts;
import com.leo.MySiteTest.consts.PermissionConsts;
import com.leo.MySiteTest.service.RoleService;
import com.leo.MySiteTest.service.UserService;

public class UserTest extends BaseChromeTester {

	RoleService roleService = new RoleService();

	UserService userService = new UserService();

	final String finalPassword = "4297F44B13955235245B2497399D7A93";

	String baseUr2 = ConfigHelper.getBaseURL("/#/user");
	String baseUr1 = ConfigHelper.getBaseURL("/#/login");

	@Test
	public void navToRolePage() {
		WebDriverWait waiter = new WebDriverWait(driver, 20);
		NavigationComponent navigation = new NavigationComponent(driver);
		CommonComponents commonCom = new CommonComponents(driver);

		adminLogin(navigation, commonCom, waiter);

		navToRolePage(navigation, commonCom, waiter);
	}

	@Test
	public void cancelAddUser() throws InterruptedException {

		WebDriverWait waiter = new WebDriverWait(driver, 20);
		NavigationComponent navigation = new NavigationComponent(driver);
		UserMainPage addUser = new UserMainPage(driver);
		CommonComponents commonCom = new CommonComponents(driver);

		adminLogin(navigation, commonCom, waiter);

		driver.get(baseUr2 + "/");

		addUser(addUser, commonCom, waiter, "Justin", "1991325");
		cancelAddUser(addUser, commonCom, waiter);
	}

	@Test
	public void addUserFailed() {
		WebDriverWait waiter = new WebDriverWait(driver, 20);
		NavigationComponent navigation = new NavigationComponent(driver);
		UserMainPage addUser = new UserMainPage(driver);
		CommonComponents commonCom = new CommonComponents(driver);

		adminLogin(navigation, commonCom, waiter);

		driver.get(baseUr2 + "/");

		addUserFailed(addUser, commonCom, waiter);

	}

	@Test
	public void addUserSucceed() {

		WebDriverWait waiter = new WebDriverWait(driver, 20);
		NavigationComponent navigation = new NavigationComponent(driver);
		UserMainPage addUser = new UserMainPage(driver);
		CommonComponents commonCom = new CommonComponents(driver);

		adminLogin(navigation, commonCom, waiter);

		driver.get(baseUr2 + "/");

		addUser(addUser, commonCom, waiter, "Justin", "1991325");
		addUserSucceed(addUser, commonCom, waiter);

	}

	@Test
	public void queryExistUser() {

		ArrayList<String> permissionList = new ArrayList<String>();
		permissionList.add(PermissionConsts.SYSTEM);
		permissionList.add(PermissionConsts.CONTENT);

		userService.InitUser("Lili", "Boss", PasswordConsts.PW_123123, permissionList);

		WebDriverWait waiter = new WebDriverWait(driver, 20);
		NavigationComponent navigation = new NavigationComponent(driver);
		UserMainPage addUser = new UserMainPage(driver);
		CommonComponents commonCom = new CommonComponents(driver);

		adminLogin(navigation, commonCom, waiter);

		driver.get(baseUr2 + "/");

		queryExistUser(addUser, commonCom, waiter, "Lili");
	}

	@Test

	public void addDuplicate() {

		ArrayList<String> permissionList = new ArrayList<String>();
		permissionList.add(PermissionConsts.BASIC_INFO);
		permissionList.add(PermissionConsts.CONTENT);

		userService.InitUser("Wanglili", "Market Analysis", PasswordConsts.PW_123123, permissionList);

		WebDriverWait waiter = new WebDriverWait(driver, 20);
		NavigationComponent navigation = new NavigationComponent(driver);
		UserMainPage addUser = new UserMainPage(driver);
		CommonComponents commonCom = new CommonComponents(driver);

		adminLogin(navigation, commonCom, waiter);

		driver.get(baseUr2 + "/");

		addDuplicateUser(addUser, commonCom, waiter);
	}

	@Test
	public void editUserSuccessed() {

		ArrayList<String> permissionList = new ArrayList<String>();
		permissionList.add(PermissionConsts.CUSTOMER);
		permissionList.add(PermissionConsts.BASIC_INFO);

		ArrayList<String> permissionList2 = new ArrayList<String>();
		permissionList2.add(PermissionConsts.SYSTEM);

		userService.InitUser("Wanglili", "Background Maintain", PasswordConsts.PW_123123, permissionList);
		roleService.initRole("Data Master", permissionList2);

		WebDriverWait waiter = new WebDriverWait(driver, 20);
		NavigationComponent navigation = new NavigationComponent(driver);
		UserMainPage addUser = new UserMainPage(driver);
		CommonComponents commonCom = new CommonComponents(driver);

		adminLogin(navigation, commonCom, waiter);

		driver.get(baseUr2 + "/");

		editUserSuccessed(addUser, commonCom, waiter, navigation, "Wanglili", "Data Master");
	}

	@Test
	public void deleteUser() {

		ArrayList<String> bmPermissionList = new ArrayList<String>();
		bmPermissionList.add(PermissionConsts.CUSTOMER);
		bmPermissionList.add(PermissionConsts.BASIC_INFO);
		ArrayList<String> sPermissionList = new ArrayList<String>();
		sPermissionList.add(PermissionConsts.SYSTEM);
		ArrayList<String> dPermissionList = new ArrayList<String>();
		bmPermissionList.add(PermissionConsts.BASIC_INFO);

		userService.InitUser("Wanglili", "Background Maintain", PasswordConsts.PW_123123, bmPermissionList);
		userService.InitUser("Andy", "supervisor", PasswordConsts.PW_123123, sPermissionList);
		userService.InitUser("Sunny", "Data Analysor", PasswordConsts.PW_123123, dPermissionList);

		WebDriverWait waiter = new WebDriverWait(driver, 20);
		NavigationComponent navigation = new NavigationComponent(driver);
		UserMainPage addUser = new UserMainPage(driver);
		CommonComponents commonCom = new CommonComponents(driver);

		adminLogin(navigation, commonCom, waiter);

		driver.get(baseUr2 + "/");

		ArrayList<String> deleteList = new ArrayList<String>();
		deleteList.add("Wanglili");
		deleteList.add("Andy");
		deleteList.add("Sunny");

		deleteUser(addUser, commonCom, waiter, deleteList);
	}

	@Test
	public void pageCheck() {
		ArrayList<String> PermissionList = new ArrayList<String>();
		PermissionList.add(PermissionConsts.CUSTOMER);
		PermissionList.add(PermissionConsts.BASIC_INFO);

		userService.initManyUsers("NASA", "Manager", "14789632", PermissionList);

		WebDriverWait waiter = new WebDriverWait(driver, 20);
		NavigationComponent navigation = new NavigationComponent(driver);
		UserMainPage addUser = new UserMainPage(driver);
		CommonComponents commonCom = new CommonComponents(driver);

		adminLogin(navigation, commonCom, waiter);
		driver.get(baseUr2 + "/");

		pageCheck(addUser, commonCom, waiter);

	}

	private void adminLogin(NavigationComponent navigation, CommonComponents commonCom, WebDriverWait waiter) {
		LoginPage adminUser = new LoginPage(driver);
		adminUser.UserLogin("admin", "123123", commonCom, waiter);

		waiter.until(ExpectedConditions.presenceOfElementLocated(navigation.getSystemManageNav().getBy()));
		waiter.until(ExpectedConditions.visibilityOfElementLocated(navigation.getSystemManageNav().getBy()));
		waiter.until(ExpectedConditions.invisibilityOfElementLocated(commonCom.getMessage().getBy()));
	}

	private void navToRolePage(NavigationComponent navigation, CommonComponents commonCom, WebDriverWait waiter) {
		waiter.until(ExpectedConditions.invisibilityOfElementLocated(commonCom.getLoading().getBy()));
		WebElement title = navigation.getSystemManageNav().getEl();
		title.click();
		System.out.println("System Manage click!");

		waiter.until(ExpectedConditions.presenceOfElementLocated(navigation.getUserNav().getBy()));
		waiter.until(ExpectedConditions.visibilityOfElementLocated(navigation.getUserNav().getBy()));

		WebElement user = navigation.getUserNav().getEl();
		user.click();
	}

	private void addUser(UserMainPage addUser, CommonComponents commonCom, WebDriverWait waiter, String name,
			String password) {

		roleService.deleteRoleWithUser("Justin", "Supervisor");
		ArrayList<String> permissionList = new ArrayList<String>();
		permissionList.add(PermissionConsts.CUSTOMER);
		permissionList.add(PermissionConsts.BASIC_INFO);
		roleService.initRole("Supervisor", permissionList);

		waiter.until(ExpectedConditions.invisibilityOfElementLocated(commonCom.getLoading().getBy()));
		WebElement addBtn = addUser.getAddNav().getEl();
		System.out.println("Find AddButton!");
		addBtn.click();
		waiter.until(ExpectedConditions.invisibilityOfElementLocated(commonCom.getLoading().getBy()));

		WebElement accountInput = addUser.getAccountInput().getEl();
		waiter.until(ExpectedConditions.presenceOfElementLocated(addUser.getAccountInput().getBy()));
		System.out.println("Find getAccountInput!");
		accountInput.sendKeys(name);

		WebElement passwordInput = addUser.getPasswordInput().getEl();
		System.out.println("Find getPasswordInput!");
		passwordInput.sendKeys(password);

		WebElement confirmPasswordInput = addUser.getConfirmPasswordInput().getEl();
		System.out.println("Find getConfirmPasswordInput!");
		confirmPasswordInput.sendKeys(password);

		WebElement statusBox = addUser.getStatusBox().getEl();
		System.out.println("Find StatusBox!");
		statusBox.click();
		waiter.until(ExpectedConditions.invisibilityOfElementLocated(commonCom.getLoading().getBy()));
		WebElement statusActive = addUser.getStatusActive().getEl();
		statusActive.click();

		WebElement roleList = addUser.getRoleList().getEl();
		System.out.println("Find RoleList!");
		roleList.click();
		waiter.until(ExpectedConditions.invisibilityOfElementLocated(commonCom.getLoading().getBy()));
		waiter.until(ExpectedConditions.visibilityOfElementLocated(addUser.selectRole("Supervisor").getBy()));
		WebElement selectRole = addUser.selectRole("Supervisor").getEl();

		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", selectRole);
//		((JavascriptExecutor) driver)
//				.executeScript("document.querySelector(\".el-select-dropdown__wrap\")[1].scrollTo(0,999)");

		selectRole.click();
	}

	private void cancelAddUser(UserMainPage addUser, CommonComponents commonCom, WebDriverWait waiter) {
		waiter.until(ExpectedConditions.visibilityOfElementLocated(addUser.getCancelBtn().getBy()));
		WebElement CancelBtn = addUser.getCancelBtn().getEl();
		CancelBtn.click();
		waiter.until(ExpectedConditions.invisibilityOfElementLocated(commonCom.getLoading().getBy()));
		WebElement userAccount = addUser.byATKey("Account").getEl();
		Assert.assertNotEquals("Justin", userAccount.getText());
	}

	private void addUserFailed(UserMainPage addUser, CommonComponents commonCom, WebDriverWait waiter) {
		waiter.until(ExpectedConditions.invisibilityOfElementLocated(commonCom.getLoading().getBy()));
		WebElement addBtn = addUser.getAddNav().getEl();
		System.out.println("Find AddButton!");
		addBtn.click();

		waiter.until(ExpectedConditions.invisibilityOfElementLocated(commonCom.getLoading().getBy()));

		WebElement saveBtn = addUser.getSaveBtn().getEl();
		saveBtn.click();
		waiter.until(ExpectedConditions.invisibilityOfElementLocated(commonCom.getLoading().getBy()));
		WebElement AccountErrorPrompt = commonCom.GetErrorPrompt(" Account is required.").getEl();
		System.out.println("Find AccountErrorPrompt!");
		Assert.assertTrue(AccountErrorPrompt.isDisplayed());
		WebElement passwordErrorPrompt = commonCom.GetErrorPrompt("Password is required.").getEl();
		System.out.println("Find passwordErrorPrompt!");
		Assert.assertTrue(passwordErrorPrompt.isDisplayed());
		WebElement statusErrorPrompt = commonCom.GetErrorPrompt("Status is required.").getEl();
		System.out.println("Find statusErrorPrompt!");
		Assert.assertTrue(statusErrorPrompt.isDisplayed());
		WebElement roleErrorPrompt = commonCom.GetErrorPrompt("Role is required.").getEl();
		System.out.println("Find roleErrorPrompt!");
		Assert.assertTrue(roleErrorPrompt.isDisplayed());

		WebElement accountInputBox = addUser.getAccountInput().getEl();
		System.out.println("Find inputBox!");
		accountInputBox.sendKeys("1234");
		WebElement dialogeHead = driver.findElement(By.className("el-dialog__header"));
		dialogeHead.click();
		WebElement StringLimitPrompt = commonCom.GetErrorPrompt("Account length should between 5 to 30.").getEl();
		System.out.println("Find AccountErrorPrompt!");
		Assert.assertTrue(StringLimitPrompt.isDisplayed());
		accountInputBox = addUser.getAccountInput().getEl();
		System.out.println("Find inputBox!");
		for (int i = 1; i <= 27; i++) {
			accountInputBox.sendKeys("1");
		}
		dialogeHead = driver.findElement(By.className("el-dialog__header"));
		dialogeHead.click();
		StringLimitPrompt = commonCom.GetErrorPrompt("Account length should between 5 to 30.").getEl();
		System.out.println("Find AccountErrorPrompt!");
		Assert.assertTrue(StringLimitPrompt.isDisplayed());
		accountInputBox = addUser.getAccountInput().getEl();
		System.out.println("Find inputBox!");
		accountInputBox.sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.DELETE);
		accountInputBox.sendKeys("/()*&^%$#@!+<>?|{}");
		dialogeHead = driver.findElement(By.className("el-dialog__header"));
		dialogeHead.click();
		WebElement StringValidationPrompt = commonCom
				.GetErrorPrompt("Account may only contain alphanumeric characters, underline or single hyphens.")
				.getEl();
		System.out.println("Find StringValidationPrompt!");
		Assert.assertTrue(StringValidationPrompt.isDisplayed());
		accountInputBox = addUser.getAccountInput().getEl();
		System.out.println("Find inputBox!");
		accountInputBox.sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.DELETE);
		accountInputBox.sendKeys("我是王lilia");
		dialogeHead = driver.findElement(By.className("el-dialog__header"));
		dialogeHead.click();
		StringValidationPrompt = commonCom
				.GetErrorPrompt("Account may only contain alphanumeric characters, underline or single hyphens.")
				.getEl();
		System.out.println("Find StringValidationPrompt!");
		Assert.assertTrue(StringValidationPrompt.isDisplayed());
		accountInputBox = addUser.getAccountInput().getEl();
		System.out.println("Find inputBox!");
		accountInputBox.sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.DELETE);
		accountInputBox.sendKeys("-_-_-_");
		dialogeHead = driver.findElement(By.className("el-dialog__header"));
		dialogeHead.click();
		try {
			StringValidationPrompt = commonCom
					.GetErrorPrompt("Account may only contain alphanumeric characters, underline or single hyphens.")
					.getEl();
			System.out.println("Find StringValidationPrompt!");
		} catch (Exception e) {
			System.out.println("'" + StringValidationPrompt + "' doesn't exist!");
		}

		WebElement passwordInputBox = addUser.getPasswordInput().getEl();
		System.out.println("Find inputBox!");
		passwordInputBox.sendKeys("1234");
		dialogeHead = driver.findElement(By.className("el-dialog__header"));
		dialogeHead.click();
		WebElement passwordLimitPrompt = commonCom.GetErrorPrompt("Password length should between 5 to 30.").getEl();
		System.out.println("Find passwordLimitPrompt!");
		Assert.assertTrue(passwordLimitPrompt.isDisplayed());

		passwordInputBox = addUser.getPasswordInput().getEl();
		System.out.println("Find inputBox!");
		for (int i = 1; i <= 27; i++) {
			accountInputBox.sendKeys("1");
		}
		dialogeHead = driver.findElement(By.className("el-dialog__header"));
		dialogeHead.click();
		passwordLimitPrompt = commonCom.GetErrorPrompt("Password length should between 5 to 30.").getEl();
		System.out.println("Find passwordLimitPrompt!");
		Assert.assertTrue(passwordLimitPrompt.isDisplayed());

		passwordInputBox = addUser.getPasswordInput().getEl();
		System.out.println("Find inputBox!");
		passwordInputBox.sendKeys("1991325");
		dialogeHead = driver.findElement(By.className("el-dialog__header"));
		dialogeHead.click();
		WebElement passwordConfirmInputBox = addUser.getConfirmPasswordInput().getEl();
		passwordConfirmInputBox.sendKeys("121212");
		dialogeHead = driver.findElement(By.className("el-dialog__header"));
		dialogeHead.click();
		WebElement passwordConfirmPrompt = commonCom.GetErrorPrompt("Confirm password must be same as password.")
				.getEl();
		Assert.assertTrue(passwordConfirmPrompt.isDisplayed());
	}

	private UserMainPage addUserSucceed(UserMainPage addUser, CommonComponents commonCom, WebDriverWait waiter) {
		WebElement SaveBtn = addUser.getSaveBtn().getEl();
		SaveBtn.click();
		System.out.println("Add Role Successfully!");
		return addUser;
	}

	private void queryExistUser(UserMainPage addUser, CommonComponents commonCom, WebDriverWait waiter, String name) {
		waiter.until(ExpectedConditions.invisibilityOfElementLocated(commonCom.getLoading().getBy()));
		WebElement QueryInput = addUser.getQueryInput().getEl();
		System.out.println("Find QueryInput!");
		QueryInput.sendKeys(name);
		WebElement QueryBtn = addUser.getQueryBtn().getEl();
		QueryBtn.click();

		waiter.until(ExpectedConditions.invisibilityOfElementLocated(commonCom.getLoading().getBy()));

		WebElement UserName = addUser.byATKey("Account").getEl();
		Assert.assertEquals(name, UserName.getText());
		System.out.println("query successfully!");

		waiter.until(ExpectedConditions.invisibilityOfElementLocated(commonCom.getLoading().getBy()));
	}

	private void addDuplicateUser(UserMainPage addUser, CommonComponents commonCom, WebDriverWait waiter) {
		addUser(addUser, commonCom, waiter, "Wanglili", "1991325");
		dealWithAlert(addUser, commonCom, waiter);
	}

	private void dealWithAlert(UserMainPage addUser, CommonComponents commonCom, WebDriverWait waiter) {
		waiter.until(ExpectedConditions.presenceOfElementLocated(addUser.getSaveBtn().getBy()));
		waiter.until(ExpectedConditions.visibilityOfElementLocated(addUser.getSaveBtn().getBy()));
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

	public void editUserSuccessed(UserMainPage addUser, CommonComponents commonCom, WebDriverWait waiter,
			NavigationComponent navigation, String account, String editRoleName) {
		waiter.until(ExpectedConditions.invisibilityOfElementLocated(commonCom.getLoading().getBy()));
		WebElement accountTable = addUser.GetTableRow("Account", account).getEl();
		WebElement editBtn = accountTable.findElement(By.cssSelector("[at-key=\"edit\"]"));
		editBtn.click();
		waiter.until(ExpectedConditions.invisibilityOfElementLocated(commonCom.getLoading().getBy()));
//		WebElement accountInput = addUser.getAccountInput().getEl();
//		Assert.assertTrue(accountInput.getAttribute("disabled").contains("disabled"));

		WebElement passwordInputBox = addUser.getPasswordInput().getEl();
		passwordInputBox.sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.DELETE);
		passwordInputBox.sendKeys("123456");
		WebElement passwordConfirmInputBox = addUser.getConfirmPasswordInput().getEl();
		passwordConfirmInputBox.sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.DELETE);
		passwordConfirmInputBox.sendKeys("123456");

		WebElement roleList = addUser.getRoleList().getEl();
		System.out.println("Find RoleList!");
		roleList.click();
		waiter.until(ExpectedConditions.invisibilityOfElementLocated(commonCom.getLoading().getBy()));
		WebElement selectRole = addUser.selectRole(editRoleName).getEl();
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", selectRole);
		selectRole.click();
		WebElement saveBtn = addUser.getSaveBtn().getEl();
		saveBtn.click();

		waiter.until(ExpectedConditions.invisibilityOfElementLocated(commonCom.getLoading().getBy()));
		WebElement WangliliRow = addUser.GetTableRow("Account", "Wanglili").getEl();
		WebElement RoleName = WangliliRow.findElement(By.cssSelector("[at-key=\"RoleName\"]"));
		Assert.assertEquals(editRoleName, RoleName.getText());

		WebElement accountBtn = addUser.getAccountBtn("admin").getEl();
		System.out.println("Find accountBtn!");
		accountBtn.click();
		WebElement logOutBtn = addUser.getLogOutBtn().getEl();
		System.out.println("Find logOutBtn!");
		logOutBtn.click();
		waiter.until(ExpectedConditions.presenceOfElementLocated(addUser.getPromp().getBy()));
		WebElement PrompOkBtn = addUser.getPrompOkBtn().getEl();
		PrompOkBtn.click();
		waiter.until(ExpectedConditions.invisibilityOfElementLocated(commonCom.getLoading().getBy()));
		LoginPage adminUser = new LoginPage(driver);
		WebElement inputAccount = adminUser.getUseNameInput().getEl();
		inputAccount.sendKeys("Wanglili");
		WebElement inputPassword = adminUser.getPasswordInput().getEl();
		inputPassword.sendKeys("123456");
		WebElement login = adminUser.getLoginBtn().getEl();
		login.click();
		waiter.until(ExpectedConditions.invisibilityOfElementLocated(commonCom.getLoading().getBy()));
		WebElement Systemtitle = navigation.getSystemManageNav().getEl();
		Assert.assertTrue(Systemtitle.isDisplayed());

		driver.get(baseUr2 + "/");
		waiter.until(ExpectedConditions.invisibilityOfElementLocated(commonCom.getLoading().getBy()));
		accountTable = addUser.GetTableRow("Account", account).getEl();
		editBtn = accountTable.findElement(By.cssSelector("[at-key=\"edit\"]"));
		editBtn.click();
		WebElement statusBox = addUser.getStatusBox().getEl();
		System.out.println("Find StatusBox!");
		statusBox.click();
		waiter.until(ExpectedConditions.invisibilityOfElementLocated(commonCom.getLoading().getBy()));
		WebElement statusInactive = addUser.getStatusInactive().getEl();
		statusInactive.click();
		saveBtn = addUser.getSaveBtn().getEl();
		saveBtn.click();
		waiter.until(ExpectedConditions.invisibilityOfElementLocated(commonCom.getLoading().getBy()));
		accountBtn = addUser.getAccountBtn("Wanglili").getEl();
		System.out.println("Find accountBtn!");
		accountBtn.click();
		logOutBtn = addUser.getLogOutBtn().getEl();
		System.out.println("Find logOutBtn!");
		logOutBtn.click();
		waiter.until(ExpectedConditions.presenceOfElementLocated(addUser.getPromp().getBy()));
		PrompOkBtn = addUser.getPrompOkBtn().getEl();
		PrompOkBtn.click();
		waiter.until(ExpectedConditions.invisibilityOfElementLocated(commonCom.getLoading().getBy()));
		adminUser = new LoginPage(driver);
		inputAccount = adminUser.getUseNameInput().getEl();
		inputAccount.sendKeys("Wanglili");
		inputPassword = adminUser.getPasswordInput().getEl();
		inputPassword.sendKeys("123456");
		login = adminUser.getLoginBtn().getEl();
		login.click();
		waiter.until(ExpectedConditions.presenceOfElementLocated(commonCom.getAlertWindow().getBy()));
		waiter.until(ExpectedConditions.visibilityOfElementLocated(commonCom.getAlertWindow().getBy()));
		WebElement AlertWindow = commonCom.getAlertWindow().getEl();
		System.out.println("find AlertWindow!");
		Assert.assertTrue(AlertWindow.isDisplayed());
	}

	private void deleteUser(UserMainPage addUser, CommonComponents commonCom, WebDriverWait waiter,
			ArrayList<String> deleteList) {
		waiter.until(ExpectedConditions.invisibilityOfElementLocated(commonCom.getLoading().getBy()));
		for (int i = 0; i <= 2; i++) {
			WebElement userTable = addUser.GetTableRow("Account", deleteList.get(i)).getEl();
			WebElement checkBox = userTable.findElement(By.cssSelector("[class=\"el-checkbox__inner\"]"));
			checkBox.click();
		}
		WebElement deletBtn = addUser.getMulDeleteBtn().getEl();
		deletBtn.click();
		waiter.until(ExpectedConditions.presenceOfElementLocated(addUser.getPromp().getBy()));
		waiter.until(ExpectedConditions.visibilityOfElementLocated(addUser.getPromp().getBy()));
		WebElement PrompOkBtn = addUser.getPrompOkBtn().getEl();
		System.out.println("find PrompOkBtn successfully!");
		PrompOkBtn.click();
		waiter.until(ExpectedConditions.invisibilityOfElementLocated(commonCom.getLoading().getBy()));
		WebElement QueryInput = addUser.getQueryInput().getEl();
		System.out.println("find QueryInput successfully!");
		for (int i = 0; i <= 2; i++) {
			QueryInput.sendKeys(deleteList.get(i));
			WebElement QueryBtn = addUser.getQueryBtn().getEl();
			System.out.println("find QueryBtn successfully!");
			QueryBtn.click();
			waiter.until(ExpectedConditions.invisibilityOfElementLocated(commonCom.getLoading().getBy()));
			WebElement NoDataSection = addUser.getNoDataSection().getEl();
			Assert.assertTrue(NoDataSection.isDisplayed());
			QueryInput = addUser.getQueryInput().getEl();
			QueryInput.sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.DELETE);
		}
	}

	public void pageCheck(UserMainPage addUser, CommonComponents commonCom, WebDriverWait waiter) {
		waiter.until(ExpectedConditions.invisibilityOfElementLocated(commonCom.getLoading().getBy()));
		WebElement nextPage = addUser.getNextPage().getEl();
		nextPage.click();
		waiter.until(ExpectedConditions.invisibilityOfElementLocated(commonCom.getLoading().getBy()));
		WebElement currentPage = addUser.getCurrentPage(2).getEl();
		Assert.assertTrue(currentPage.isDisplayed());
		WebElement roleNameTable = driver
				.findElement(By.xpath("//*[@at-key='Account' and contains(text(),'0')]/ancestor::tr"));
		Assert.assertTrue(roleNameTable.isDisplayed());
		currentPage = addUser.getCurrentPage(1).getEl();
		currentPage.click();
		roleNameTable = driver.findElement(By.xpath("//*[@at-key='Account' and contains(text(),'20')]/ancestor::tr"));
		Assert.assertTrue(roleNameTable.isDisplayed());
		userService.deleteManyUsers("NASA");
	}
}
