package com.g4life.dto;

import java.io.Serializable;
import java.util.Date;

import com.google.gson.annotations.Expose;

public class ProductInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Expose
	int accountID;
	@Expose
	int productNo;
	@Expose
	int itemNo;
	@Expose
	String itemSubject;
	@Expose
	String unitPrice;
	@Expose
	String unit;
	@Expose
	int totalCount;
	@Expose
	Date startDate;
	@Expose
	Date endDate;
	@Expose
	int rating;
	@Expose
	String status;
	@Expose
	int discount;
	@Expose
	String imageName;
	@Expose
	String information;
	@Expose
	String comment;
	
	public ProductInfo(int accountID, int productNo, int itemNo,
			String itemSubject, String unitPrice, String unit,
			int totalCount, Date startDate, Date endDate, int rating,
			String status, int discount, String imageName, String information,
			String comment) {
		super();
		this.accountID = accountID;
		this.productNo = productNo;
		this.itemNo = itemNo;
		this.itemSubject = itemSubject;
		this.unitPrice = unitPrice;
		this.unit = unit;
		this.totalCount = totalCount;
		this.startDate = startDate;
		this.endDate = endDate;
		this.rating = rating;
		this.status = status;
		this.discount = discount;
		this.imageName = imageName;
		this.information = information;
		this.comment = comment;
	}
	public ProductInfo() {
		// TODO Auto-generated constructor stub
	}
	public int getAccountID() {
		return accountID;
	}
	public void setAccountID(int accountID) {
		this.accountID = accountID;
	}
	public int getProductNo() {
		return productNo;
	}
	public void setProductNo(int productNo) {
		this.productNo = productNo;
	}
	public int getItemNo() {
		return itemNo;
	}
	public void setItemNo(int itemNo) {
		this.itemNo = itemNo;
	}
	public String getItemSubject() {
		return itemSubject;
	}
	public void setItemSubject(String itemSubject) {
		this.itemSubject = itemSubject;
	}
	public String getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(String unitPrice) {
		this.unitPrice = unitPrice;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
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
	public int getDiscount() {
		return discount;
	}
	public void setDiscount(int discount) {
		this.discount = discount;
	}
	public String getImageName() {
		return imageName;
	}
	public void setImageName(String imageName) {
		this.imageName = imageName;
	}
	public String getInformation() {
		return information;
	}
	public void setInformation(String information) {
		this.information = information;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
