package com.motor.insurance.model;

import lombok.Data;

@Data
public class UserModel {

	private int UserId;
	private String userName;
	private String userEmail;
	private String userPassword;
	private String userConfirmPassword;
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	public String getUserConfirmPassword() {
		return userConfirmPassword;
	}
	public void setUserConfirmPassword(String userConfirmPassword) {
		this.userConfirmPassword = userConfirmPassword;
	}
	public int getUserId() {
		return UserId;
	}
	public void setUserId(int userId) {
		UserId = userId;
	}

	

	

}
