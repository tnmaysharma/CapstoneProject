package com.bms.domain;

import org.springframework.data.annotation.Id;

public class BidList {
	
	@Id
	private String id;
	private String biddingPrice;
	private String emailId;
	private String date;
	private String bidId;

	public BidList() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getBiddingPrice() {
		return biddingPrice;
	}

	public void setBiddingPrice(String biddingPrice) {
		this.biddingPrice = biddingPrice;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getBidId() {
		return bidId;
	}

	public void setBidId(String bidId) {
		this.bidId = bidId;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public BidList(String biddingPrice, String emailId, String date, String bidId) {
		super();
		this.biddingPrice = biddingPrice;
		this.emailId = emailId;
		this.date = date;
		this.bidId = bidId;
	}

	@Override
	public String toString() {
		return "BidList [id=" + id + ", biddingPrice=" + biddingPrice + ", emailId=" + emailId + ", date=" + date
				+ ", bidId=" + bidId + "]";
	}
	
	
	
}
