package com.leo.MySiteTest.Models.MainPage;

import java.util.Calendar;
import org.openqa.selenium.WebDriver;
import com.leo.MySiteTest.Models.BaseElement;
import com.leo.MySiteTest.Models.BaseModel;

public class MainPage extends BaseModel {
	public MainPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	public BaseElement getMessageBoxRole(String roleName) {
		return byXpath("//div[@class='el-row']/div[contains(text(),'" + roleName + "')]");
	}

	public BaseElement getMessageBoxKeywords(String keywords) {
		return byXpath("//div[@class='el-row']/div[contains(text(),'" + keywords + "')]");
	}

	public BaseElement getMessageBoxAccount(String account) {
		return byXpath("//div[@class='avatar-box']/h3[contains(text(),'" + account + "')]");
	}

	public BaseElement getAccountBtn(String account) {
		return byXpath(
				"//button[@class='el-button el-button--text el-button--small el-popover__reference']/span[contains(text(),'"
						+ account + "')]");
	}

	public BaseElement getLogOutBtn() {
		return byXpath(
				"//button[@class='el-button el-button--primary el-button--mini']/span[contains(text(),'Log out')]");
	}

	public BaseElement getNoticeTitle(String title) {
		return byXpath("//div[@class='el-card__body']/ul/li/a[contains(text(),'" + title + "')]");

	}

	public BaseElement getNewSignal() {
		return byXpath("//div[@class='el-card__body']/ul/li/sup[contains(text(),'NEW')]");

	}

	public BaseElement getNextPage() {
		return byClassName("btn-next");

	}

	public BaseElement getCurrentPage(int page) {
		return byXpath("//div[@at-key='basePager']/ul/li[contains(text()," + page + ")]");

	}

	public BaseElement getPrePage() {
		return byClassName("btn-prev");

	}
}
