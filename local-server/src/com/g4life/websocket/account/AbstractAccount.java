package com.g4life.websocket.account;

import com.google.gson.annotations.SerializedName;

public abstract class AbstractAccount {

	@SerializedName("ID")
	public String id;

	@SerializedName("UserName")
	public String userName;

	@SerializedName("PassWord")
	public String passWord;

	@SerializedName("PhoneNumber")
	public String phoneNumber;

	@SerializedName("Mail")
	public String mail;

	public void setAccount(AbstractAccount account) {
		this.id = account.getId();
		this.userName = account.getUserName();
		this.passWord = account.getPassWord();
		this.phoneNumber = account.getPhoneNumber();
		this.mail = account.getMail();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

}
