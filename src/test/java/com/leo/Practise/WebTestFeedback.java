package com.leo.Practise;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import junit.framework.Assert;

public class WebTestFeedback {
	public WebDriver driver;
	String baseUrl = "http://localhost:8081/#/site/index";

	@Test
	public void inputValidation() throws InterruptedException {
		synchronized (driver) {
			driver.get(baseUrl + "/");
			WebElement submitBtn = driver.findElement(By.id("btnSubmitFeedback"));
			submitBtn.click();
			WebDriverWait wait = new WebDriverWait(driver, 10);
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("el-loading-mask")));

			WebElement titleWarning = driver.findElement(By.className("el-input"));
			WebElement titleWarningText = titleWarning.findElement(By.xpath("./following-sibling::*"));
			String answer1 = titleWarningText.getText();
			Assert.assertTrue("请输入留言标题".equals(answer1));

			WebElement typeWarning = driver.findElement(By.className("el-select"));
			WebElement typeWarningText = typeWarning.findElement(By.xpath("./following-sibling::*"));
			String answer2 = typeWarningText.getText();
			Assert.assertTrue("请选择留言类型".equals(answer2));

			WebElement contentWarning = driver.findElement(By.className("el-textarea"));
			WebElement contentWarningText = contentWarning.findElement(By.xpath("./following-sibling::*"));
			String answer3 = contentWarningText.getText();
			Assert.assertTrue("请填写留言内容".equals(answer3));

			WebElement titleInput = driver.findElement(By.id("feedbackTitle"));
			titleInput.sendKeys("a");
			driver.findElement(By.cssSelector("body")).click();
			titleWarning = driver.findElement(By.className("el-input"));
			titleWarningText = titleWarning.findElement(By.xpath("./following-sibling::*"));
			answer1 = titleWarningText.getText();
			Assert.assertTrue("长度在 2 到 20 个字符".equals(answer1));

			WebElement contentInput = driver.findElement(By.id("feedbackContent"));
			for (int i = 1; i <= 10; i++) {
				contentInput.sendKeys(
						"ABCDEFGHIJKLMNOPQRSTUVWXYZABCDEFGHIJKLMNOPQRSTUVWXYZABCDEFGHIJKLMNOPQRSTUVWXYZABCDEFGHIJKLMNOPQRSTUVWXYZ");
			}
			driver.wait(2000L);
			driver.findElement(By.cssSelector("body")).click();
			contentWarning = driver.findElement(By.className("el-textarea"));
			contentWarningText = contentWarning.findElement(By.xpath("./following-sibling::*"));
			answer3 = contentWarningText.getText();
			Assert.assertTrue("长度在 1 到 1000 个字符".equals(answer3));

			WebElement phoneInput = driver.findElement(By.id("feedbackSubmitPhoneNumber"));
			phoneInput.sendKeys("185");

			WebElement emailInput = driver.findElement(By.id("feedbackSubmitEmail"));
			emailInput.sendKeys("啥@163.com");

			WebElement typeSelector = driver.findElement(By.id("feedbackType"));
			typeSelector.click();
			WebElement selectList = driver.findElement(By.className("el-scrollbar"));
			WebElement select1 = selectList.findElement(By.xpath("//span[contains(text(), \"业务咨询\")]"));
			select1.click();

			WebElement phoneWarning = driver.findElement(By.xpath("//div[contains(text(), \"请输入正确的手机号码\")]"));
			Assert.assertTrue(phoneWarning.isDisplayed());
			WebElement emailWarning = driver.findElement(By.xpath("//div[contains(text(), \"请输入正确的EMail地址\")]"));
			Assert.assertTrue(emailWarning.isDisplayed());

			WebElement resetBtn = driver.findElement(By.id("btnResetFeedback"));
			resetBtn.click();
			titleInput = driver.findElement(By.id("feedbackTitle"));
			String titleContent = titleInput.getText();
			Assert.assertTrue("".equals(titleContent));

			contentInput = driver.findElement(By.id("feedbackContent"));
			String Content = contentInput.getText();
			Assert.assertTrue("".equals(Content));

			typeSelector = driver.findElement(By.id("feedbackType"));
			String typeContent = typeSelector.getText();
			Assert.assertTrue("".equals(typeContent));

			phoneInput = driver.findElement(By.id("feedbackSubmitPhoneNumber"));
			String phoneContent = phoneInput.getText();
			Assert.assertTrue("".equals(phoneContent));

			emailInput = driver.findElement(By.id("feedbackSubmitEmail"));
			String emailContent = emailInput.getText();
			Assert.assertTrue("".equals(emailContent));
		}

	}

	@Test
	public void submitInformation() throws InterruptedException {
		synchronized (driver) {
			driver.get(baseUrl + "/");
			WebElement titleInput = driver.findElement(By.id("feedbackTitle"));
			titleInput.sendKeys("我想做女主播");
			WebElement typeSelector = driver.findElement(By.id("feedbackType"));
			typeSelector.click();
			WebElement selectList = driver.findElement(By.className("el-scrollbar"));
			WebElement select1 = selectList.findElement(By.xpath("//span[contains(text(), \"业务咨询\")]"));
			select1.click();
			WebElement contentInput = driver.findElement(By.id("feedbackContent"));
			contentInput.sendKeys("如果我有一百万，请问你们能把我包装城女主播吗？月薪20万就可以");
			WebElement phoneInput = driver.findElement(By.id("feedbackSubmitPhoneNumber"));
			phoneInput.sendKeys("18591919120");
			WebElement emailInput = driver.findElement(By.id("feedbackSubmitEmail"));
			emailInput.sendKeys("1259654@163.com");
			WebElement submitBtn = driver.findElement(By.id("btnSubmitFeedback"));
			submitBtn.click();
			WebDriverWait wait = new WebDriverWait(driver, 10);
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("el-loading-mask")));
			WebElement prompt = driver.findElement(By.xpath("//p[contains(text(), \"成功\")]"));
			Assert.assertTrue(prompt.isDisplayed());
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
