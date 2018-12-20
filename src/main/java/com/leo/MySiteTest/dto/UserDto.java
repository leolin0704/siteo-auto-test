package com.leo.MySiteTest.dto;

import java.util.Date;

public class UserDto extends BaseSiteoDto {

	int ID;
	String Account;
	String Password;
	char Status;
	String Avatar;
	String LastLoginIP;
	Date LastLoginDate;
	String Token;
	Date TokenExpired;

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getAccount() {
		return Account;
	}

	public void setAccount(String account) {
		Account = account;
	}

	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		Password = password;
	}

	public char getStatus() {
		return Status;
	}

	public void setStatus(char status) {
		Status = status;
	}

	public String getAvatar() {
		return Avatar;
	}

	public void setAvatar(String avatar) {
		Avatar = avatar;
	}

	public String getLastLoginIP() {
		return LastLoginIP;
	}

	public void setLastLoginIP(String lastLoginIP) {
		LastLoginIP = lastLoginIP;
	}

	public Date getLastLoginDate() {
		return LastLoginDate;
	}

	public void setLastLoginDate(Date lastLoginDate) {
		LastLoginDate = lastLoginDate;
	}

	public String getToken() {
		return Token;
	}

	public void setToken(String token) {
		Token = token;
	}

	public Date getTokenExpired() {
		return TokenExpired;
	}

	public void setTokenExpired(Date tokenExpired) {
		TokenExpired = tokenExpired;
	}

}
