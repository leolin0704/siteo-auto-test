package com.leo.MySiteTest.Models.Notice;

import org.openqa.selenium.WebDriver;

import com.leo.MySiteTest.Models.BaseElement;
import com.leo.MySiteTest.Models.BaseModel;

public class NoticeMainPage extends BaseModel {
	public NoticeMainPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	public BaseElement getAddNav() {
		return byATKey("btnAdd");
	}

	public BaseElement getNoticeInput() {
		return byId("txtNoticeTitle");
	}

	public BaseElement getContentInput() {
		return byClassName("ProseMirror");
	}

	public BaseElement getCancelBtn() {
		return byATKey("btnDialogCancel");
	}

	public BaseElement getSaveBtn() {
		return byATKey("btnDialogSave");
	}

	public BaseElement getQueryInput() {
		return byATKey("txtBaseQuery");
	}

	public BaseElement getQueryBtn() {
		return byXpath(
				"//button[@class='el-button el-button--primary el-button--small']/span[contains(text(),'QUERY')]");
	}

	public BaseElement getInputBox() {
		return byId("txtNoticeTitle");
	}

	public BaseElement getMultiDelete() {
		return byATKey("btnMultiDelete");
	}

	public BaseElement getPrompOkBtn() {
		return byXpath("//div[@class='el-message-box__btns']/button/span[contains(text(),'OK')]");
	}

	public BaseElement getPromp() {
		return byClassName("el-message-box");
	}

	public BaseElement getNoDataSection() {
		return byXpath("//div[@class='el-table__empty-block']/span[contains(text(),'No Data')]");
	}

	public BaseElement getNextPage() {
		return byClassName("btn-next");
	}

	public BaseElement getPrePage() {
		return byClassName("btn-prev");
	}

	public BaseElement getCurrentPage(int currentNumber) {
		return byXpath("//ul[@class='el-pager']/li[contains(text(),'" + currentNumber + "')]");
	}
}