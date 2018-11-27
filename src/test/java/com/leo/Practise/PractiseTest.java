package com.leo.Practise;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PractiseTest {

	public WebDriver driver;
	String baseUrl = "http://localhost:8080/practise";

	@Test
	public void checkImages() {
		driver.get(baseUrl + "/");
		WebElement images = driver.findElement(By.id("images"));
		Assert.assertTrue(images.isDisplayed());
	}
}
