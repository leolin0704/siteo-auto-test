package com.leo.MySiteTest.Models.Role;

import org.openqa.selenium.WebDriver;
import com.leo.MySiteTest.Models.BaseElement;
import com.leo.MySiteTest.Models.BaseModel;

public class RoleMainPage extends BaseModel {
	public RoleMainPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	public BaseElement getAddNav() {
		return byATKey("btnAdd");
	}

	public BaseElement getMultiDelete() {
		return byATKey("btnMultiDelete");
	}

	public BaseElement getInputBox() {
		return byId("txtRoleName");
	}

	public BaseElement getPermissionCheckBoxList() {
		return byATKey("chkPermission");
	}

	public BaseElement getCheckBox() {
		return byClassName("el-checkbox__inner");
	}

	public BaseElement getCustomerCheckbox() {
		return byXpath("//span[contains(text(),'CUSTOMER')]");
	}

	public BaseElement getBasic_InfoCheckbox() {
		return byXpath("//span[contains(text(),'BASIC_INFO')]");
	}

	public BaseElement getContentCheckbox() {
		return byXpath("//span[contains(text(),'CONTENT')]");
	}

	public BaseElement getSystemCheckbox() {
		return byXpath("//span[contains(text(),'SYSTEM')]");
	}

	public BaseElement getCancelBtn() {
		return byXpath("//button/span[contains(text(),'CANCEL')]/..");
	}

	public BaseElement getSaveBtn() {
		return byXpath(
				"//button[@class='el-button el-button--primary el-button--small']/span[contains(text(),'SAVE')]");
	}

	public BaseElement getQueryInput() {
		return byXpath("//input[@class='el-input__inner']");
	}

	public BaseElement getQueryBtn() {
		return byXpath(
				"//button[@class='el-button el-button--primary el-button--small']/span[contains(text(),'QUERY')]");
	}

	public BaseElement getResetBtn() {
		return byCssSelector("[at-key=\"btnBaseQueryReset\"]");
	}

	public BaseElement getListBody() {
		return byId("tblRole");
	}

	public BaseElement getDeleteBtn() {
		return byATKey("delete");
	}

	public BaseElement getViewBtn() {
		return byATKey("view");
	}

	public BaseElement getEditBtn() {
		return byATKey("edit");
	}

	public BaseElement getPromp() {
		return byClassName("el-message-box");
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
