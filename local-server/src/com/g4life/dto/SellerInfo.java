package com.g4life.dto;

import java.io.Serializable;
import java.util.Date;

import com.google.gson.annotations.Expose;

public class SellerInfo implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Expose
	int accountID;
	@Expose
	int rating;
	@Expose
	String status;
	@Expose
	Date startDate;
	@Expose
	String comment;
	@Expose
	int sellerType;
	
	public SellerInfo(int accountID, int rating, String status, Date startDate,
			String comment, int sellerType) {
		super();
		this.accountID = accountID;
		this.rating = rating;
		this.status = status;
		this.startDate = startDate;
		this.comment = comment;
		this.sellerType = sellerType;
	}
	public int getAccountID() {
		return accountID;
	}
	public void setAccountID(int accountID) {
		this.accountID = accountID;
	}
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public int getSellerType() {
		return sellerType;
	}
	public void setSellerType(int sellerType) {
		this.sellerType = sellerType;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
