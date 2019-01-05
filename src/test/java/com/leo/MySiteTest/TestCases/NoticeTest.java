package com.leo.MySiteTest.TestCases;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.leo.MySiteTest.Common.ConfigHelper;
import com.leo.MySiteTest.Common.Utils;
import com.leo.MySiteTest.Models.CommonComponents;
import com.leo.MySiteTest.Models.NavigationComponent;
import com.leo.MySiteTest.Models.Login.LoginPage;
import com.leo.MySiteTest.Models.MainPage.MainPage;
import com.leo.MySiteTest.Models.Notice.NoticeMainPage;
import com.leo.MySiteTest.service.NoticeService;

public class NoticeTest extends BaseChromeTester {
	String baseUr1 = ConfigHelper.getBaseURL("/#/login");
	String baseUr2 = ConfigHelper.getBaseURL("/#/notice");
	String baseUr3 = ConfigHelper.getBaseURL("/#/home");

	NoticeService noticeServce = new NoticeService();

	@Test
	public void navToRolePage() {
		WebDriverWait waiter = new WebDriverWait(driver, 20);
		NavigationComponent navigation = new NavigationComponent(driver);
		CommonComponents commonCom = new CommonComponents(driver);

		adminLogin(navigation, commonCom, waiter);

		navToRolePage(navigation, commonCom, waiter);

//		ChromeDriver driver1 = new ChromeDriver();
//		driver1.get(baseUr1 + "/");
	}

	@Test
	public void cancelAddNotice() throws InterruptedException {

		WebDriverWait waiter = new WebDriverWait(driver, 20);
		NavigationComponent navigation = new NavigationComponent(driver);
		NoticeMainPage addNotice = new NoticeMainPage(driver);
		CommonComponents commonCom = new CommonComponents(driver);

		adminLogin(navigation, commonCom, waiter);

		driver.get(baseUr2 + "/");

		addNotice(addNotice, commonCom, waiter, "界面不需要改进");
		cancelAddNotice(addNotice, commonCom, waiter, "界面不需要改进");
	}

	@Test
	public void addNoticeFailed() {
		WebDriverWait waiter = new WebDriverWait(driver, 20);
		NavigationComponent navigation = new NavigationComponent(driver);
		NoticeMainPage addNotice = new NoticeMainPage(driver);
		CommonComponents commonCom = new CommonComponents(driver);

		adminLogin(navigation, commonCom, waiter);

		driver.get(baseUr2 + "/");

		addNoticeFailed(addNotice, commonCom, waiter);
	}

	@Test
	public void addNoticeSucceed() {

		WebDriverWait waiter = new WebDriverWait(driver, 20);
		NavigationComponent navigation = new NavigationComponent(driver);
		NoticeMainPage addNotice = new NoticeMainPage(driver);
		CommonComponents commonCom = new CommonComponents(driver);

		adminLogin(navigation, commonCom, waiter);

		driver.get(baseUr2 + "/");

		addNotice(addNotice, commonCom, waiter, "界面需要改进");
		addNoticeSucceed(addNotice, commonCom, waiter);
	}

	@Test
	public void queryExistNotice() {

		noticeServce.insertByTitle("需要添加节日特效");
		WebDriverWait waiter = new WebDriverWait(driver, 20);
		NavigationComponent navigation = new NavigationComponent(driver);
		NoticeMainPage addNotice = new NoticeMainPage(driver);
		CommonComponents commonCom = new CommonComponents(driver);

		adminLogin(navigation, commonCom, waiter);
		driver.get(baseUr2 + "/");
		queryExistNotice(addNotice, commonCom, waiter, "需要添加节日特效");
	}

	@Test
	public void viewExistNotice() {
		noticeServce.insertByTitle("添加富文本编辑器");
		WebDriverWait waiter = new WebDriverWait(driver, 20);
		NavigationComponent navigation = new NavigationComponent(driver);
		NoticeMainPage addNotice = new NoticeMainPage(driver);
		CommonComponents commonCom = new CommonComponents(driver);

		adminLogin(navigation, commonCom, waiter);
		driver.get(baseUr2 + "/");

		viewExistNotice(addNotice, commonCom, waiter, "添加富文本编辑器");
	}

	@Test
	public void editNoticeFailed() {
		noticeServce.insertByTitle("删去冗余功能");
		WebDriverWait waiter = new WebDriverWait(driver, 20);
		NavigationComponent navigation = new NavigationComponent(driver);
		NoticeMainPage addNotice = new NoticeMainPage(driver);
		CommonComponents commonCom = new CommonComponents(driver);

		adminLogin(navigation, commonCom, waiter);
		driver.get(baseUr2 + "/");

		editNoticeFailed(addNotice, commonCom, waiter, "删去冗余功能");
	}

	@Test
	public void editNoticeSuccessed() {

		noticeServce.insertByTitle("假期改版");
		WebDriverWait waiter = new WebDriverWait(driver, 20);
		NavigationComponent navigation = new NavigationComponent(driver);
		NoticeMainPage addNotice = new NoticeMainPage(driver);
		CommonComponents commonCom = new CommonComponents(driver);

		adminLogin(navigation, commonCom, waiter);
		driver.get(baseUr2 + "/");

		editNoticeSuccessed(addNotice, commonCom, waiter, navigation, "假期改版", "发布日期调整");
	}

	@Test
	public void deleteNotice() {
		noticeServce.insertByTitle("假日聚餐");
		noticeServce.insertByTitle("茶话会主题");
		noticeServce.insertByTitle("新版块上线");

		WebDriverWait waiter = new WebDriverWait(driver, 20);
		NavigationComponent navigation = new NavigationComponent(driver);
		NoticeMainPage addNotice = new NoticeMainPage(driver);
		CommonComponents commonCom = new CommonComponents(driver);

		adminLogin(navigation, commonCom, waiter);

		driver.get(baseUr2 + "/");

		ArrayList<String> deleteList = new ArrayList<String>();
		deleteList.add("假日聚餐");
		deleteList.add("茶话会主题");
		deleteList.add("新版块上线");

		deleteNotice(addNotice, commonCom, waiter, deleteList);
	}

	@Test
	public void pageCheck() {
		noticeServce.insertManyTitle("板块调整");
		;

		WebDriverWait waiter = new WebDriverWait(driver, 20);
		NavigationComponent navigation = new NavigationComponent(driver);
		NoticeMainPage addNotice = new NoticeMainPage(driver);
		CommonComponents commonCom = new CommonComponents(driver);

		adminLogin(navigation, commonCom, waiter);
		driver.get(baseUr2 + "/");

		pageCheck(addNotice, commonCom, waiter);

		noticeServce.deleteManyTitle("板块调整");

	}

	@Test
	public void newSymbolCheck() {
		noticeServce.deleteAllNotice();
		noticeServce.insertByTitle("系统会在下午6点升级");

		WebDriverWait waiter = new WebDriverWait(driver, 20);
		NavigationComponent navigation = new NavigationComponent(driver);
		MainPage mianPage = new MainPage(driver);
		CommonComponents commonCom = new CommonComponents(driver);

		adminLogin(navigation, commonCom, waiter);

		newSymbolCheck(mianPage, commonCom, waiter);

		try {
			noticeServce.insertByTime("日期调整", Utils.CreateDate(2018, 12, 28));
			System.out.println("添加成功");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("报错");
		}

		driver.navigate().refresh();

		newSymbolDispeared(mianPage, commonCom, waiter);
	}

	private void adminLogin(NavigationComponent navigation, CommonComponents commonCom, WebDriverWait waiter) {
		LoginPage adminUser = new LoginPage(driver);
		driver.get(baseUr1 + "/");
		adminUser.UserLogin("admin", "123123", commonCom, waiter);

		waiter.until(ExpectedConditions.presenceOfElementLocated(navigation.getTitleNav("System Manage").getBy()));
		waiter.until(ExpectedConditions.visibilityOfElementLocated(navigation.getTitleNav("System Manage").getBy()));
		waiter.until(ExpectedConditions.invisibilityOfElementLocated(commonCom.getMessage().getBy()));
	}

	private void navToRolePage(NavigationComponent navigation, CommonComponents commonCom, WebDriverWait waiter) {
		WebElement title = navigation.getTitleNav("System Manage").getEl();
		title.click();
		System.out.println("System Manage click!");

		waiter.until(ExpectedConditions.presenceOfElementLocated(navigation.getDetailNav("Notice").getBy()));
		waiter.until(ExpectedConditions.visibilityOfElementLocated(navigation.getDetailNav("Notice").getBy()));

		WebElement notice = navigation.getDetailNav("Notice").getEl();
		notice.click();
		waiter.until(ExpectedConditions.invisibilityOfElementLocated(commonCom.getLoading().getBy()));
	}

	private void addNotice(NoticeMainPage addNotice, CommonComponents commonCom, WebDriverWait waiter, String title) {
		waiter.until(ExpectedConditions.invisibilityOfElementLocated(commonCom.getLoading().getBy()));
		WebElement addBtn = addNotice.getAddNav().getEl();
		System.out.println("Find AddButton!");
		addBtn.click();

		waiter.until(ExpectedConditions.invisibilityOfElementLocated(commonCom.getLoading().getBy()));

		WebElement titleInputBox = addNotice.getNoticeInput().getEl();
		waiter.until(ExpectedConditions.presenceOfElementLocated(addNotice.getNoticeInput().getBy()));
		System.out.println("Find titleInputBox!");
		titleInputBox.sendKeys(title);

		WebElement contentInputBox = addNotice.getContentInput().getEl();
		waiter.until(ExpectedConditions.presenceOfElementLocated(addNotice.getNoticeInput().getBy()));
		System.out.println("Find contentInputBox!");
		contentInputBox = addNotice.getContentInput().getEl();
		contentInputBox.sendKeys("网站的界面太丑");
	}

	private void cancelAddNotice(NoticeMainPage addNotice, CommonComponents commonCom, WebDriverWait waiter,
			String title) {
		waiter.until(ExpectedConditions.visibilityOfElementLocated(addNotice.getCancelBtn().getBy()));
		WebElement CancelBtn = addNotice.getCancelBtn().getEl();
		CancelBtn.click();
		waiter.until(ExpectedConditions.invisibilityOfElementLocated(commonCom.getLoading().getBy()));
		WebElement titleName = addNotice.byATKey("Title").getEl();
		Assert.assertNotEquals(title, titleName.getText());
	}

	private void addNoticeFailed(NoticeMainPage addNotice, CommonComponents commonCom, WebDriverWait waiter) {
		waiter.until(ExpectedConditions.invisibilityOfElementLocated(commonCom.getLoading().getBy()));
		WebElement addBtn = addNotice.getAddNav().getEl();
		System.out.println("Find AddButton!");
		addBtn.click();

		waiter.until(ExpectedConditions.invisibilityOfElementLocated(commonCom.getLoading().getBy()));

		WebElement saveBtn = addNotice.getSaveBtn().getEl();
		saveBtn.click();
		waiter.until(ExpectedConditions.invisibilityOfElementLocated(commonCom.getLoading().getBy()));
		WebElement titleErrorPrompt = commonCom.GetErrorPrompt("Title is required.").getEl();
		System.out.println("Find titleErrorPrompt!");
		Assert.assertTrue(titleErrorPrompt.isDisplayed());
		WebElement contentErrorPrompt = commonCom.GetErrorPrompt("Content is required.").getEl();
		System.out.println("Find contentErrorPrompt!");
		Assert.assertTrue(contentErrorPrompt.isDisplayed());
		WebElement titleInputBox = addNotice.getNoticeInput().getEl();
		System.out.println("Find titleInputBox!");
		titleInputBox.sendKeys("1");
		WebElement dialogeHead = driver.findElement(By.className("el-dialog__header"));
		dialogeHead.click();
		WebElement StringLimitPrompt = commonCom.GetErrorPrompt("Title length should between 2 to 200.").getEl();
		System.out.println("Find titleErrorPrompt!");
		Assert.assertTrue(StringLimitPrompt.isDisplayed());
		titleInputBox = addNotice.getNoticeInput().getEl();
		for (int i = 1; i <= 200; i++) {
			titleInputBox.sendKeys("1");
		}
		dialogeHead = driver.findElement(By.className("el-dialog__header"));
		dialogeHead.click();
		StringLimitPrompt = commonCom.GetErrorPrompt("Title length should between 2 to 200.").getEl();
		Assert.assertTrue(StringLimitPrompt.isDisplayed());
	}

	private void addNoticeSucceed(NoticeMainPage addNotice, CommonComponents commonCom, WebDriverWait waiter) {
		WebElement SaveBtn = addNotice.getSaveBtn().getEl();
		SaveBtn.click();
		System.out.println("Add Role Successfully!");
		waiter.until(ExpectedConditions.invisibilityOfElementLocated(commonCom.getLoading().getBy()));
		WebElement titleName = addNotice.byATKey("Title").getEl();
		Assert.assertEquals("界面需要改进", titleName.getText());

		driver.get(baseUr3 + "/");
		waiter.until(ExpectedConditions.invisibilityOfElementLocated(commonCom.getLoading().getBy()));
		MainPage mianPage = new MainPage(driver);
		WebElement systemNotice = mianPage.getNoticeContent("界面需要改进").getEl();
		Assert.assertTrue(systemNotice.isDisplayed());
	}

	private void queryExistNotice(NoticeMainPage addNotice, CommonComponents commonCom, WebDriverWait waiter,
			String title) {
		waiter.until(ExpectedConditions.invisibilityOfElementLocated(commonCom.getLoading().getBy()));
		WebElement QueryInput = addNotice.getQueryInput().getEl();
		System.out.println("Find QueryInput!");
		QueryInput.sendKeys(title);
		WebElement QueryBtn = addNotice.getQueryBtn().getEl();
		QueryBtn.click();

		waiter.until(ExpectedConditions.invisibilityOfElementLocated(commonCom.getLoading().getBy()));

		WebElement titleName = addNotice.byATKey("Title").getEl();
		Assert.assertEquals(title, titleName.getText());
	}

	private void viewExistNotice(NoticeMainPage addNotice, CommonComponents commonCom, WebDriverWait waiter,
			String title) {
		waiter.until(ExpectedConditions.invisibilityOfElementLocated(commonCom.getLoading().getBy()));
		WebElement existNotice = addNotice.GetTableRow("Title", title).getEl();
		WebElement viewBtn = existNotice.findElement(By.cssSelector("[at-key=\"view\"]"));
		viewBtn.click();
		waiter.until(ExpectedConditions.presenceOfElementLocated(commonCom.getDialog().getBy()));
		waiter.until(ExpectedConditions.visibilityOfElementLocated(commonCom.getDialog().getBy()));
		waiter.until(ExpectedConditions.invisibilityOfElementLocated(commonCom.getLoading().getBy()));
		WebElement DisabledInput = addNotice.getInputBox().getEl();
		System.out.println("find DisabledInput!");
		Assert.assertFalse(DisabledInput.isEnabled());
		WebElement CancelBtn = commonCom.getDialogCancel().getEl();
		CancelBtn.click();
		System.out.println("click CancelBtn!");
	}

	public void editNoticeFailed(NoticeMainPage addNotice, CommonComponents commonCom, WebDriverWait waiter,
			String title) {
		waiter.until(ExpectedConditions.invisibilityOfElementLocated(commonCom.getLoading().getBy()));

		WebElement existNotice = addNotice.GetTableRow("Title", title).getEl();
		WebElement editBtn = existNotice.findElement(By.cssSelector("[at-key=\"edit\"]"));
		editBtn.click();

		waiter.until(ExpectedConditions.invisibilityOfElementLocated(commonCom.getLoading().getBy()));

		WebElement inputBox = addNotice.getInputBox().getEl();
		System.out.println("Find inputBox!");
		inputBox.sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.DELETE);
		WebElement contentInputBox = addNotice.getContentInput().getEl();
		contentInputBox.sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.DELETE);
		WebElement saveBtn = addNotice.getSaveBtn().getEl();
		saveBtn.click();
		waiter.until(ExpectedConditions.invisibilityOfElementLocated(commonCom.getLoading().getBy()));
		WebElement titleErrorPrompt = commonCom.GetErrorPrompt("Title is required.").getEl();
		System.out.println("Find titleErrorPrompt!");
		Assert.assertTrue(titleErrorPrompt.isDisplayed());
		WebElement contentErrorPrompt = commonCom.GetErrorPrompt("Content is required.").getEl();
		System.out.println("Find contentErrorPrompt!");
		Assert.assertTrue(contentErrorPrompt.isDisplayed());
		inputBox = addNotice.getInputBox().getEl();
		System.out.println("Find inputBox!");
		inputBox.sendKeys("1");
		WebElement windowHead = driver.findElement(By.className("el-dialog__header"));
		windowHead.click();
		WebElement StringLimitPrompt = commonCom.GetErrorPrompt("Title length should between 2 to 200.").getEl();
		System.out.println("Find StringLimitPrompt!");
		Assert.assertTrue(StringLimitPrompt.isDisplayed());
		inputBox = addNotice.getInputBox().getEl();
		System.out.println("Find inputBox!");
		for (int i = 1; i <= 200; i++) {
			inputBox.sendKeys("1");
		}
		windowHead = driver.findElement(By.className("el-dialog__header"));
		windowHead.click();
		StringLimitPrompt = commonCom.GetErrorPrompt("Title length should between 2 to 200.").getEl();
		System.out.println("Find StringLimitPrompt!");
		Assert.assertTrue(StringLimitPrompt.isDisplayed());
		WebElement CancelBtn = commonCom.getDialogCancel().getEl();

		CancelBtn.click();
		waiter.until(ExpectedConditions.invisibilityOfElementLocated(commonCom.getLoading().getBy()));
	}

	public void editNoticeSuccessed(NoticeMainPage addNotice, CommonComponents commonCom, WebDriverWait waiter,
			NavigationComponent navigation, String title, String editTitle) {
		waiter.until(ExpectedConditions.invisibilityOfElementLocated(commonCom.getLoading().getBy()));
		waiter.until(ExpectedConditions.visibilityOfElementLocated(addNotice.GetTableRow("Title", title).getBy()));
		WebElement existNotice = addNotice.GetTableRow("Title", title).getEl();
		WebElement editBtn = existNotice.findElement(
				By.xpath("//button[@class='el-button el-button--primary el-button--mini']/i[@class='el-icon-edit']"));
		editBtn.click();
		waiter.until(ExpectedConditions.invisibilityOfElementLocated(commonCom.getLoading().getBy()));
		WebElement inputBox = addNotice.getInputBox().getEl();
		waiter.until(ExpectedConditions.presenceOfElementLocated(addNotice.getInputBox().getBy()));
		inputBox.sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.DELETE);
		inputBox = addNotice.getInputBox().getEl();
		inputBox.sendKeys(editTitle);
		WebElement contentInputBox = addNotice.getContentInput().getEl();
		contentInputBox.sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.DELETE);
		contentInputBox = addNotice.getContentInput().getEl();
		contentInputBox.sendKeys("由于元旦将近，版面调整日期延后");
		WebElement saveBtn = addNotice.getSaveBtn().getEl();
		saveBtn.click();
		waiter.until(ExpectedConditions.invisibilityOfElementLocated(commonCom.getLoading().getBy()));
		WebElement titleName = addNotice.byATKey("Title").getEl();
		Assert.assertEquals(editTitle, titleName.getText());

		driver.get(baseUr3 + "/");
		waiter.until(ExpectedConditions.invisibilityOfElementLocated(commonCom.getLoading().getBy()));
		MainPage mianPage = new MainPage(driver);
		WebElement systemNotice = mianPage.getNoticeContent(editTitle).getEl();
		Assert.assertTrue(systemNotice.isDisplayed());
	}

	private void deleteNotice(NoticeMainPage addNotice, CommonComponents commonCom, WebDriverWait waiter,
			ArrayList<String> deleteList) {
		waiter.until(ExpectedConditions.invisibilityOfElementLocated(commonCom.getLoading().getBy()));
		for (int i = 0; i <= 2; i++) {
			WebElement noticeTable = addNotice.GetTableRow("Title", deleteList.get(i)).getEl();
			WebElement checkBox = noticeTable.findElement(By.cssSelector("[class=\"el-checkbox__inner\"]"));
			checkBox.click();
		}
		WebElement deletBtn = addNotice.getMultiDelete().getEl();
		deletBtn.click();
		waiter.until(ExpectedConditions.presenceOfElementLocated(addNotice.getPromp().getBy()));
		waiter.until(ExpectedConditions.visibilityOfElementLocated(addNotice.getPromp().getBy()));
		WebElement PrompOkBtn = addNotice.getPrompOkBtn().getEl();
		System.out.println("find PrompOkBtn successfully!");
		PrompOkBtn.click();
		waiter.until(ExpectedConditions.invisibilityOfElementLocated(commonCom.getLoading().getBy()));
		WebElement QueryInput = addNotice.getQueryInput().getEl();
		System.out.println("find QueryInput successfully!");
		for (int i = 0; i <= 2; i++) {
			QueryInput.sendKeys(deleteList.get(i));
			WebElement QueryBtn = addNotice.getQueryBtn().getEl();
			System.out.println("find QueryBtn successfully!");
			QueryBtn.click();
			waiter.until(ExpectedConditions.invisibilityOfElementLocated(commonCom.getLoading().getBy()));
			WebElement NoDataSection = addNotice.getNoDataSection().getEl();
			Assert.assertTrue(NoDataSection.isDisplayed());
			QueryInput = addNotice.getQueryInput().getEl();
			QueryInput.sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.DELETE);
		}

		driver.get(baseUr3 + "/");
		waiter.until(ExpectedConditions.invisibilityOfElementLocated(commonCom.getLoading().getBy()));
		MainPage mianPage = new MainPage(driver);
		for (int i = 0; i <= 2; i++) {
			try {
				WebElement systemNotice = mianPage.getNoticeContent(deleteList.get(i)).getEl();
				Assert.assertTrue(systemNotice.isDisplayed());
			} catch (Exception e) {
				System.out.println(deleteList.get(i) + "doesn't exist!");
			}
		}
	}

	public void pageCheck(NoticeMainPage addNotice, CommonComponents commonCom, WebDriverWait waiter) {
		waiter.until(ExpectedConditions.invisibilityOfElementLocated(commonCom.getLoading().getBy()));
		WebElement nextPage = addNotice.getNextPage().getEl();
		nextPage.click();
		waiter.until(ExpectedConditions.invisibilityOfElementLocated(commonCom.getLoading().getBy()));
		WebElement currentPage = addNotice.getCurrentPage(2).getEl();
		Assert.assertTrue(currentPage.isDisplayed());
		WebElement noticeNameTable = driver
				.findElement(By.xpath("//*[@at-key='Title' and contains(text(),'0')]/ancestor::tr"));
		Assert.assertTrue(noticeNameTable.isDisplayed());
		currentPage = addNotice.getCurrentPage(1).getEl();
		currentPage.click();
		noticeNameTable = driver.findElement(By.xpath("//*[@at-key='Title' and contains(text(),'20')]/ancestor::tr"));
		Assert.assertTrue(noticeNameTable.isDisplayed());
	}

	public void newSymbolCheck(MainPage mianPage, CommonComponents commonCom, WebDriverWait waiter) {
		waiter.until(ExpectedConditions.invisibilityOfElementLocated(commonCom.getLoading().getBy()));
		WebElement addNoticeLine = driver.findElement(
				By.xpath("//div[@class='el-card__body']/ul/li/a[contains(text(),'系统会在下午6点升级')]/parent::li/sup"));
		String newSignal = addNoticeLine.getText();
		System.out.println(newSignal);
		Assert.assertEquals("NEW", newSignal);
	}

	public void newSymbolDispeared(MainPage mianPage, CommonComponents commonCom, WebDriverWait waiter) {
		waiter.until(ExpectedConditions.invisibilityOfElementLocated(commonCom.getLoading().getBy()));

		WebElement addNoticeLine = driver
				.findElement(By.xpath("//div[@class='el-card__body']/ul/li/a[contains(text(),'日期调整')]/parent::li/sup"));
		String newSignal = addNoticeLine.getText();
		System.out.println(newSignal);
		Assert.assertEquals("", newSignal);
	}
}
