package com.bms.domain;

import org.springframework.data.annotation.Id;

public class CreateBidList {

	@Id
	private String id;
	private String startDate;
	private String endDate;
	private String productName;
	private String productId;
	private String bidBasePrice;

	public CreateBidList(String startDate, String endDate, String productName, String productId, String bidBasePrice) {
		super();
		this.startDate = startDate;
		this.endDate = endDate;
		this.productName = productName;
		this.productId = productId;
		this.bidBasePrice = bidBasePrice;
	}

	public String getbidBasePrice() {
		return bidBasePrice;
	}

	public void setbidBasePrice(String bidPrice) {
		this.bidBasePrice = bidPrice;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	@Override
	public String toString() {
		return "CreateBidList [id=" + id + ", startDate=" + startDate + ", endDate=" + endDate + ", productName="
				+ productName + ", productId=" + productId + ", bidBasePrice=" + bidBasePrice + "]";
	}

}
