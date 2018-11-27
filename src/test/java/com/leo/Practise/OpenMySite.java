package com.leo.Practise;

import java.util.List;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class OpenMySite {

	public WebDriver driver;
	String baseUrl = "http://localhost:8080/todo";

	@Test
	public void testBaiduSearch() {

		TodoIndex todoIndex = new TodoIndex(driver);
		todoIndex.loadPage();
		WebElement inputBox = todoIndex.getQueryName();

		/*
		 * SqlSession session = DBTools.getSession(); TodoDao mapper =
		 * session.getMapper(TodoDao.class); try { mapper.deleteAll(); session.commit();
		 * } catch (Exception e) { e.printStackTrace(); session.rollback();
		 * 
		 * Assert.fail(); }
		 */
		Assert.assertTrue(inputBox.isDisplayed());
		inputBox.sendKeys("人士纷纷");
		WebElement queryBtn = todoIndex.getQueryBtn();
		queryBtn.click();

		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".loading")));

		WebElement tbody = todoIndex.getListBody();
		List<WebElement> trList = todoIndex.getTrList(tbody);
		Assert.assertTrue(trList.size() == 1);

		WebElement detail = tbody.findElement(By.xpath("./tr[1]/td[1]/.."));

		Assert.assertEquals("色粉色发数据", detail.getText());

	}

	@Before
	public void beforeMethod() {
		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\Administrator\\AppData\\Local\\Google\\Chrome\\Application\\chromedriver.exe");
		driver = new ChromeDriver();
	}

	@After
	public void afterMethod() {
		driver.quit();
	}
}
