package com.leo.Practise;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.ibatis.session.SqlSession;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import com.leo.MySiteTest.dao.NewsDao;
import com.leo.MySiteTest.tool.DBTools;

public class OpenMySite2 {

	public WebDriver driver;
	String baseUrl = "http://localhost:8080/manage";
	String baseUr2 = "http://localhost:8080/news";

	@Test
	public void queryItems() throws InterruptedException {
		synchronized (driver) {
			driver.get(baseUrl + "/");

			SqlSession session = DBTools.getSession();
			NewsDao mapper = session.getMapper(NewsDao.class);
			try {
				mapper.deleteAll();
				session.commit();
			} catch (Exception e) {
				e.printStackTrace();
				session.rollback();
				Assert.fail();
			}

			for (int i = 1; i <= 11; i++) {
				try {
					mapper.InsertOne();
					mapper.InsertTwo();
					session.commit();
				} catch (Exception e) {
					e.printStackTrace();
					session.rollback();
					Assert.fail();
				}

			}

			WebElement inputBox = driver.findElement(By.id("querykeywords"));
			Assert.assertTrue(inputBox.isDisplayed());
			inputBox.sendKeys("动物");
			driver.findElement(By.id("query")).click();
			WebElement tbody = driver.findElement(By.id("newsList"));
			List<WebElement> trList = tbody.findElements(By.tagName("tr"));
			for (int i = 0; i < trList.size(); i++) {
				WebElement one = (trList.get(i));
				Assert.assertTrue((one.findElement(By.className("title")).getText()).indexOf("动物") > -1);
			}

			inputBox = driver.findElement(By.id("querykeywords"));
			inputBox.clear();
			inputBox.sendKeys("周杰伦");
			WebElement nextPage = driver.findElement(By.className("nextPage"));
			nextPage.click();
			driver.wait(2000L);
			tbody = driver.findElement(By.id("newsList"));
			trList = tbody.findElements(By.tagName("tr"));
			WebElement one = (trList.get(0));
			Assert.assertFalse((one.findElement(By.className("title")).getText()).indexOf("周杰伦") > -1);

			driver.findElement(By.id("query")).click();
			driver.wait(2000L);
			nextPage = driver.findElement(By.className("nextPage"));
			nextPage.click();
			driver.wait(2000L);
			tbody = driver.findElement(By.id("newsList"));
			trList = tbody.findElements(By.tagName("tr"));
			for (int i = 0; i < trList.size(); i++) {
				WebElement two = (trList.get(i));
				Assert.assertTrue((two.findElement(By.className("title")).getText()).indexOf("周杰伦") > -1);
			}
		}
	}

	@Test
	public void testPager() throws InterruptedException {
		synchronized (driver) {
			driver.get(baseUrl + "/");
			WebElement prePage = driver.findElement(By.className("prePage"));
			WebElement preLi = prePage.findElement(By.xpath(".."));
			String preAttrs = preLi.getAttribute("class");
			int preDisabled = preAttrs.indexOf("disabled");
			Assert.assertTrue(preDisabled > -1);
			print("prePage disabled");

			WebElement currentPage = driver.findElement(By.className("pager"));
			WebElement currentLi = currentPage.findElement(By.xpath(".."));
			String curAttrs = currentLi.getAttribute("class");
			int currentDisabled = curAttrs.indexOf("disabled");
			Assert.assertTrue(currentDisabled > -1);
			print("currentPage disabled");

			driver.findElement(By.linkText("3")).click();

			driver.wait(2000L);
			WebElement page3 = driver.findElement(By.linkText("3"));
			print("page3 get");
			WebElement page3Li = page3.findElement(By.xpath(".."));
			print("page3 parent get");
			String page3Attrs = page3Li.getAttribute("class");
			print("page3 parent class " + page3Attrs);
			int page3Disabled = page3Attrs.indexOf("disabled");
			print("page3 parent disabled " + page3Disabled);
			Assert.assertTrue(page3Disabled > -1);
			print("page3 disabled");

			driver.wait(2000L);
			WebElement page1 = driver.findElement(By.linkText("1"));
			WebElement page1Li = page1.findElement(By.xpath(".."));
			String page1Attrs = page1Li.getAttribute("class");
			int page1Disabled = page1Attrs.indexOf("disabled");
			Assert.assertFalse(page1Disabled > -1);
			print("page1 abled");

			driver.wait(2000L);
			WebElement nextPage = driver.findElement(By.className("nextPage"));
			WebElement nextLi = nextPage.findElement(By.xpath(".."));
			String nextAttrs = nextLi.getAttribute("class");
			int nextDisabled = nextAttrs.indexOf("disabled");
			Assert.assertTrue(nextDisabled > -1);
			print("nextPage disabled");
		}
	}

	public void addItem(String title, String content, int day, boolean isShow) {
		WebElement titleInput = driver.findElement(By.id("newsTitle"));
		titleInput.sendKeys(title);
		WebElement contentInput = driver.findElement(By.id("newsContent"));
		contentInput.sendKeys(content);
		WebElement newsPublishInput = driver.findElement(By.id("newsPublish"));
		newsPublishInput.click();
		WebElement tableClendar = driver.findElement(By.className("table-condensed"));
		WebElement dateList = (tableClendar.findElements(By.className("day"))).get(day);
		dateList.click();
		driver.findElement(By.cssSelector("body")).click();
		WebElement checkBox = driver.findElement(By.id("check"));
		if (isShow && !checkBox.isSelected()) {
			checkBox.click();
			print("Check box clicked!");
		} else if (!isShow && checkBox.isSelected()) {
			checkBox.click();
			print("Check box clicked!");
		}

		WebElement saveBtn = driver.findElement(By.id("Save"));
		saveBtn.click();

	}

	public void validationAllFail() {
		WebElement saveBtn = driver.findElement(By.id("Save"));
		saveBtn.click();
		WebElement titleWarning = driver.findElement(By.id("newsTitle-error"));
		String titleWarningContent = titleWarning.getText();
		Assert.assertTrue("This field is required.".equals(titleWarningContent));
		WebElement contentWarning = driver.findElement(By.id("newsContent-error"));
		String contentWarningContent = contentWarning.getText();
		Assert.assertTrue("This field is required.".equals(contentWarningContent));
		WebElement publishDateWarning = driver.findElement(By.id("newsPublish-error"));
		String publishWarningContent = publishDateWarning.getText();
		Assert.assertTrue("This field is required.".equals(publishWarningContent));

		WebElement titleInput = driver.findElement(By.id("newsTitle"));
		for (int i = 1; i <= 31; i++) {
			titleInput.sendKeys("L");
		}
		titleWarning = driver.findElement(By.id("newsTitle-error"));
		titleWarningContent = titleWarning.getText();
		Assert.assertTrue("Please enter a value between 1 and 30 characters long.".equals(titleWarningContent));
		print("MaxTitleValidation successfully!");
		titleInput.clear();

		WebElement contentInput = driver.findElement(By.id("newsContent"));
		for (int i = 1; i <= 101; i++) {
			contentInput.sendKeys("e");
		}
		contentWarning = driver.findElement(By.id("newsContent-error"));
		contentWarningContent = contentWarning.getText();
		Assert.assertTrue("Please enter a value between 1 and 100 characters long.".equals(contentWarningContent));
		print("MaxContentValidation successfully!");
		contentInput.clear();
	}

	public void clickAddCancel(String title, String content, int day) {
		WebElement addBtn = driver.findElement(By.id("add"));
		addBtn.click();
		WebElement titleInput = driver.findElement(By.id("newsTitle"));
		titleInput.sendKeys(title);
		WebElement contentInput = driver.findElement(By.id("newsContent"));
		contentInput.sendKeys(content);
		WebElement newsPublishInput = driver.findElement(By.id("newsPublish"));
		newsPublishInput.click();
		WebElement tableClendar = driver.findElement(By.className("table-condensed"));
		WebElement dateList = (tableClendar.findElements(By.className("day"))).get(day);
		dateList.click();
		WebElement cancelBtn = driver.findElement(By.id("Cancel"));
		cancelBtn.click();
		WebElement tbody = driver.findElement(By.id("newsList"));
		List<WebElement> trList = tbody.findElements(By.tagName("tr"));
		WebElement firstOne = trList.get(0);
		WebElement newTitle = firstOne.findElement(By.className("title"));
		Assert.assertTrue("前9个月全国税收收入达127486亿元".equals(newTitle.getText()));
		WebElement newContent = firstOne.findElement(By.className("content"));
		Assert.assertTrue("9月份，全国一般公共预算收入12963亿元，同比增长2%。其中，中央一般公共预算收入5919亿元，同比增长0.9%".equals(newContent.getText()));
		WebElement newPublishDate = firstOne.findElement(By.className("date"));
		Assert.assertTrue("2018/10/13".equals(newPublishDate.getText()));
	}

	@Test

	public void addItems() throws InterruptedException {
		synchronized (driver) {
			driver.get(baseUrl + "/");
			WebElement addBtn = driver.findElement(By.id("add"));
			addBtn.click();

			validationAllFail();
			driver.wait(2000L);

			addItem("前9个月全国税收收入达127486亿元", "9月份，全国一般公共预算收入12963亿元，同比增长2%。其中，中央一般公共预算收入5919亿元，同比增长0.9%", 13, true);
			driver.wait(2000L);

			WebElement tbody = driver.findElement(By.id("newsList"));
			List<WebElement> trList = tbody.findElements(By.tagName("tr"));
			WebElement addOne = trList.get(0);
			WebElement title = addOne.findElement(By.className("title"));
			Assert.assertTrue("前9个月全国税收收入达127486亿元".equals(title.getText()));
			WebElement content = addOne.findElement(By.className("content"));
			Assert.assertTrue("9月份，全国一般公共预算收入12963亿元，同比增长2%。其中，中央一般公共预算收入5919亿元，同比增长0.9%".equals(content.getText()));
			WebElement publishDate = addOne.findElement(By.className("date"));
			Assert.assertTrue("2018/10/13".equals(publishDate.getText()));
			WebElement status = addOne.findElement(By.className("status"));
			Assert.assertTrue("Y".equals(status.getText()));
			driver.wait(2000L);

			clickAddCancel("明星大侦探即将开始", "明星大侦探官宣：由何炅、撒贝宁、王鸥、白敬亭、魏大勋联合主演的真人节目《明星大侦探》即将开播", 25);
			driver.wait(2000L);

			tbody = driver.findElement(By.id("newsList"));
			trList = tbody.findElements(By.tagName("tr"));
			addOne = trList.get(0);
			WebElement delete = addOne.findElement(By.className("delbtn"));
			delete.click();
			Alert alert = driver.switchTo().alert();
			Assert.assertEquals("是否确认删除", alert.getText());
			alert.dismiss();
			WebElement inputBox = driver.findElement(By.id("querykeywords"));
			inputBox.sendKeys("前9个月全国税收收入达127486亿元");
			driver.findElement(By.id("query")).click();
			driver.wait(2000L);
			tbody = driver.findElement(By.id("newsList"));
			trList = tbody.findElements(By.tagName("tr"));
			Assert.assertTrue(trList.size() != 0);
			print("delete failed");

			tbody = driver.findElement(By.id("newsList"));
			trList = tbody.findElements(By.tagName("tr"));
			WebElement deleteOne = trList.get(0);
			delete = deleteOne.findElement(By.className("delbtn"));
			delete.click();
			alert = driver.switchTo().alert();
			Assert.assertEquals("是否确认删除", alert.getText());
			alert.accept();
			inputBox = driver.findElement(By.id("querykeywords"));
			inputBox.sendKeys("前9个月全国税收收入达127486亿元");
			driver.findElement(By.id("query")).click();
			driver.wait(2000L);
			tbody = driver.findElement(By.id("newsList"));
			trList = tbody.findElements(By.tagName("tr"));
			Assert.assertTrue(trList.size() == 0);
			print("delete succeed");
		}
	}

	private void print(String message) {
		System.out.println("[" + new Date().toString() + "] " + message);
	}

	public void validationAllFailed() throws InterruptedException {
		synchronized (driver) {
			WebElement titleInput = driver.findElement(By.id("newsTitle"));
			titleInput.clear();

			WebElement contentInput = driver.findElement(By.id("newsContent"));
			contentInput.clear();

			WebElement newsPublishInput = driver.findElement(By.id("newsPublish"));
			newsPublishInput.click();
			WebElement cleanDate = driver.findElement(By.className("clear"));
			cleanDate.click();
			WebElement saveBtn = driver.findElement(By.id("Save"));
			saveBtn.click();

			print("save clicked!");

			WebElement titleWarning = driver.findElement(By.id("newsTitle-error"));
			String titleWarningContent = titleWarning.getText();
			Assert.assertTrue("This field is required.".equals(titleWarningContent));

			print("title check succeed!");

			WebElement contentWarning = driver.findElement(By.id("newsContent-error"));
			String contentWarningContent = contentWarning.getText();
			Assert.assertTrue("This field is required.".equals(contentWarningContent));

			print("Content check succeed!");

			WebElement publishDateWarning = driver.findElement(By.id("newsPublish-error"));
			String publishWarningContent = publishDateWarning.getText();
			Assert.assertTrue("This field is required.".equals(publishWarningContent));

			print("Publish date check succeed!");

			titleInput = driver.findElement(By.id("newsTitle"));
			for (int i = 1; i <= 31; i++) {
				titleInput.sendKeys("L");
			}
			titleWarning = driver.findElement(By.id("newsTitle-error"));
			titleWarningContent = titleWarning.getText();
			Assert.assertTrue("Please enter a value between 1 and 30 characters long.".equals(titleWarningContent));
			print("MaxTitleValidation successfully!");
			driver.wait(2000L);
			titleInput = driver.findElement(By.id("newsTitle"));
			titleInput.clear();

			contentInput = driver.findElement(By.id("newsContent"));
			for (int i = 1; i <= 101; i++) {
				contentInput.sendKeys("e");
			}
			contentWarning = driver.findElement(By.id("newsContent-error"));
			contentWarningContent = contentWarning.getText();
			Assert.assertTrue("Please enter a value between 1 and 100 characters long.".equals(contentWarningContent));
			print("MaxContentValidation successfully!");
			driver.wait(2000L);
			contentInput = driver.findElement(By.id("newsContent"));
			contentInput.clear();
		}

	}

	public void editItem(String title, String content, int day, boolean isShow) throws InterruptedException {
		synchronized (driver) {
			WebElement titleInput = driver.findElement(By.id("newsTitle"));
			titleInput.sendKeys(title);
			WebElement contentInput = driver.findElement(By.id("newsContent"));
			contentInput.sendKeys(content);
			WebElement newsPublishInput = driver.findElement(By.id("newsPublish"));
			newsPublishInput.click();
			WebElement tableClendar = driver.findElement(By.className("table-condensed"));
			WebElement dateList = (tableClendar.findElements(By.className("day"))).get(day);
			dateList.click();
			WebElement checkBox = driver.findElement(By.id("check"));

			if (isShow && !checkBox.isSelected()) {
				checkBox.click();
				print("Check box clicked!");
			} else if (!isShow && checkBox.isSelected()) {
				checkBox.click();
				print("Check box clicked!");
			}
			driver.wait(2000L);
			driver.findElement(By.cssSelector("body")).click();
			WebElement saveBtn = driver.findElement(By.id("Save"));
			saveBtn.click();
		}
	}

	public void clickeEditCancel(String title, String content, int day) {
		WebElement tbody = driver.findElement(By.id("newsList"));
		List<WebElement> trList = tbody.findElements(By.tagName("tr"));
		WebElement editItem = trList.get(0);
		WebElement editBtn = editItem.findElement(By.className("editbtn"));
		editBtn.click();
		WebElement titleInput = driver.findElement(By.id("newsTitle"));
		titleInput.sendKeys(title);
		WebElement contentInput = driver.findElement(By.id("newsContent"));
		contentInput.sendKeys(content);
		WebElement newsPublishInput = driver.findElement(By.id("newsPublish"));
		newsPublishInput.click();
		WebElement tableClendar = driver.findElement(By.className("table-condensed"));
		WebElement dateList = (tableClendar.findElements(By.className("day"))).get(day);
		dateList.click();
		WebElement cancelBtn = driver.findElement(By.id("Cancel"));
		cancelBtn.click();
		tbody = driver.findElement(By.id("newsList"));
		trList = tbody.findElements(By.tagName("tr"));
		editItem = trList.get(0);
		String changTitle = editItem.findElement(By.className("title")).getText();
		Assert.assertTrue("双十一变美计划".equals(changTitle));
		String changeContent = editItem.findElement(By.className("content")).getText();
		Assert.assertTrue("买个全能王美容仪".equals(changeContent));
		String changeDate = editItem.findElement(By.className("date")).getText();
		Assert.assertTrue("2018/10/11".equals(changeDate));
		String changePublish = editItem.findElement(By.className("status")).getText();
		Assert.assertTrue("N".equals(changePublish));
	}

	@Test
	public void editItems() throws InterruptedException {
		synchronized (driver) {
			driver.get(baseUrl + "/");
			WebElement tbody = driver.findElement(By.id("newsList"));
			List<WebElement> trList = tbody.findElements(By.tagName("tr"));
			WebElement editItem = trList.get(0);
			WebElement editBtn = editItem.findElement(By.className("editbtn"));
			editBtn.click();

			validationAllFailed();
			driver.wait(2000L);

			editItem("双十一变美计划", "买个全能王美容仪", 11, false);
			driver.wait(2000L);

			tbody = driver.findElement(By.id("newsList"));
			trList = tbody.findElements(By.tagName("tr"));
			editItem = trList.get(0);
			String changTitle = editItem.findElement(By.className("title")).getText();
			Assert.assertTrue("双十一变美计划".equals(changTitle));
			String changeContent = editItem.findElement(By.className("content")).getText();
			Assert.assertTrue("买个全能王美容仪".equals(changeContent));
			String changeDate = editItem.findElement(By.className("date")).getText();
			Assert.assertTrue("2018/10/11".equals(changeDate));
			String changePublish = editItem.findElement(By.className("status")).getText();
			Assert.assertTrue("N".equals(changePublish));
			driver.wait(2000L);

			clickeEditCancel("奇葩说", "奇葩说你最支持谁？李诞？高晓松还是蔡康永？", 30);
			print("cancel edit operation!");
		}
	}

	@Test
	public void interAction() throws InterruptedException {
		synchronized (driver) {

			SqlSession session = DBTools.getSession();
			NewsDao mapper = session.getMapper(NewsDao.class);
			try {
				mapper.deleteAll();
				session.commit();
			} catch (Exception e) {
				e.printStackTrace();
				session.rollback();
				Assert.fail();
			}

			for (int i = 1; i <= 5; i++) {
				try {
					mapper.InsertOne();
					session.commit();
				} catch (Exception e) {
					e.printStackTrace();
					session.rollback();
					Assert.fail();
				}

			}
			driver.wait(2000L);
			driver.get(baseUr2 + "/");
			WebElement newsTbody = driver.findElement(By.id("newsList"));
			List<WebElement> newsTrList = newsTbody.findElements(By.tagName("tr"));
			Assert.assertTrue(newsTrList.size() == 0);

			for (int i = 1; i <= 5; i++) {
				try {
					mapper.InsertTwo();
					session.commit();
				} catch (Exception e) {
					e.printStackTrace();
					session.rollback();
					Assert.fail();
				}

			}
			driver.wait(3000L);
			driver.get(baseUr2 + "/");
			newsTbody = driver.findElement(By.id("newsList"));
			newsTrList = newsTbody.findElements(By.tagName("tr"));
			Assert.assertTrue(newsTrList.size() != 0);

		}
	}

	@Test
	public void jumpWeb() throws InterruptedException {
		synchronized (driver) {
			driver.get(baseUr2 + "/");

			WebElement newsTable = driver.findElement(By.linkText("新闻"));
			newsTable.click();
			driver.wait(2000L);
			WebElement inputBox = driver.findElement(By.id("querykeywords"));
			Assert.assertTrue(inputBox.isDisplayed());

			WebElement mainPage = driver.findElement(By.linkText("首页"));
			mainPage.click();
			driver.wait(2000L);
			WebElement moreButton = driver.findElement(By.className("more"));
			Assert.assertTrue(moreButton.isDisplayed());

			WebElement newsTbody = driver.findElement(By.id("newsList"));
			List<WebElement> newsTrList = newsTbody.findElements(By.tagName("tr"));
			WebElement title = newsTrList.get(0).findElement(By.linkText("周杰伦方回应乱扔垃圾:想帮老先生做回收增加收入"));
			title.click();
			driver.wait(2000L);
			title = driver.findElement(By.className("text-center"));
			Assert.assertTrue("周杰伦方回应乱扔垃圾:想帮老先生做回收增加收入".equals(title.getText()));
		}
	}

	@Before
	public void beforeMethod() {
		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\Administrator\\AppData\\Local\\Google\\Chrome\\Application\\chromedriver.exe");
		driver = new ChromeDriver();

		driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
	}

	@After
	public void afterMethod() {
		driver.quit();
	}
}
