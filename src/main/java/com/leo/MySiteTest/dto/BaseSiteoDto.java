package com.leo.MySiteTest.dto;

import java.util.Date;

public class BaseSiteoDto {
	private Date LastUpdateDate;
	private Date CreateDate;
	private String CreateBy;
	private String LastUpdateBy;
	private int IsDeleted;

	public Date getLastUpdateDate() {
		return LastUpdateDate;
	}

	public void setLastUpdateDate(Date lastUpdateDate) {
		LastUpdateDate = lastUpdateDate;
	}

	public Date getCreateDate() {
		return CreateDate;
	}

	public void setCreateDate(Date createDate) {
		CreateDate = createDate;
	}

	public String getCreateBy() {
		return CreateBy;
	}

	public void setCreateBy(String createBy) {
		CreateBy = createBy;
	}

	public String getLastUpdateBy() {
		return LastUpdateBy;
	}

	public void setLastUpdateBy(String lastUpdateBy) {
		LastUpdateBy = lastUpdateBy;
	}

	public int getIsDeleted() {
		return IsDeleted;
	}

	public void setIsDeleted(int isDeleted) {
		IsDeleted = isDeleted;
	}

}
