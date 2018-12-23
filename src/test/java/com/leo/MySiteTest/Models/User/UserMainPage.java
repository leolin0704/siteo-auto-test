package com.leo.MySiteTest.Models.User;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.leo.MySiteTest.Models.BaseElement;
import com.leo.MySiteTest.Models.BaseModel;

public class UserMainPage extends BaseModel {

	public BaseElement getAddNav() {
		return byATKey("btnAdd");
	}

	public BaseElement getCancelBtn() {
		return byATKey("btnDialogCancel");
	}

	public BaseElement getSaveBtn() {
		return byATKey("btnDialogSave");
	}

	public UserMainPage(WebDriver driver) {
		super(driver);
	}

	public BaseElement getAccountInput() {
		return byId("txtUserAccount");
	}

	public BaseElement getPasswordInput() {
		return byId("txtUserPassword");
	}

	public BaseElement getConfirmPasswordInput() {
		return byId("txtUserConfirmPassword");
	}

	public BaseElement getStatusBox() {
		return byId("selUserStatusList");
	}

	public BaseElement getStatusActive() {
		return getDropDownItem("optUserStatus", "Active");
	}

	public BaseElement getStatusInactive() {
		return getDropDownItem("optUserStatus", "Inactive");
	}

	public BaseElement getRoleList() {
		return byId("selUserRoles");
	}

	public BaseElement selectRole(String roleName) {
		return getDropDownItem("optUserRole", roleName);
	}

	public BaseElement GetErrorPrompt(String value) {
		return buildElement(By.xpath("//div[@class='el-form-item__error' and contains(.,'" + value + "')]"));
	}

	public BaseElement getQueryInput() {
		return byATKey("txtBaseQuery");
	}

	public BaseElement getQueryBtn() {
		return byATKey("btnBaseQuery");
	}

	public BaseElement getPromp() {
		return byClassName("el-message-box");
	}

	public BaseElement getMulDeleteBtn() {
		return byATKey("btnMultiDelete");
	}

	public BaseElement getPrompOkBtn() {
		return byXpath("//div[@class='el-message-box__btns']/button/span[contains(text(),'OK')]");
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