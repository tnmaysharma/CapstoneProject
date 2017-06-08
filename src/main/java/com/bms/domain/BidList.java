package com.bms.domain;

import org.springframework.data.annotation.Id;

public class BidList {
	
	@Id
	private String id;
	private String biddingPrice;
	private String emailId;
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

	public BidList(String biddingPrice, String emailId, String bidId) {
		super();
		this.biddingPrice = biddingPrice;
		this.emailId = emailId;
		this.bidId = bidId;
	}

	@Override
	public String toString() {
		return "BidList [id=" + id + ", biddingPrice=" + biddingPrice + ", emailId=" + emailId 
				+ ", bidId=" + bidId + "]";
	}
	
	
	
}
