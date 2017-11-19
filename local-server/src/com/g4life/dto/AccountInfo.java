package com.g4life.dto;

import java.io.Serializable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AccountInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//@SerializedName("ID")
	@Expose
	public int accountID;

	@SerializedName("UserName")
	@Expose
	public String userName;

	@SerializedName("PassWord")
	@Expose
	public String passWord;

	@SerializedName("PhoneNumber")
	@Expose
	public String phoneNumber;

	@SerializedName("Mail")
	@Expose
	public String mail;
	
	@SerializedName ("Address")
	@Expose
	public String address;
	
	@SerializedName ("AccountType")
	@Expose
	public int accountType;
	public void setAccount(AccountInfo account) {
		this.accountID = account.getAccountID();
		this.userName = account.getUserName();
		this.passWord = account.getPassWord();
		this.phoneNumber = account.getPhoneNumber();
		this.mail = account.getMail();
		this.address = account.getAddress();
	}
	
	public AccountInfo(int accountID, String userName, String passWord,
			String phoneNumber, String mail, String address, int accountType) {
		super();
		this.accountID = accountID;
		this.userName = userName;
		this.passWord = passWord;
		this.phoneNumber = phoneNumber;
		this.mail = mail;
		this.address = address;
		this.accountType = accountType;
	}

	public AccountInfo() {
		// TODO Auto-generated constructor stub
	}

	public String getAddress() {
		return address;
	}
	public int getAccountID() {
		return accountID;
	}

	public void setAccountID(int i) {
		this.accountID = i;
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
	public void setAddress(String address) {
		this.address = address;
	}

	public int getAccountType() {
		return accountType;
	}

	public void setAccountType(int accountType) {
		this.accountType = accountType;
	}
	
}
