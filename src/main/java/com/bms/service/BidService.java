package com.bms.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bms.domain.BidList;
import com.bms.domain.CreateBidList;
import com.bms.repostiries.JpaBidList;
import com.bms.repostiries.JpaCreateBidList;
import com.bms.scheduler.Scheduler;

@Service
public class BidService {

	@Autowired
	JpaBidList jpaBidList;

	@Autowired
	JpaCreateBidList jpaCreateBidList;

	@Autowired
	Scheduler scheduler;

	public List<CreateBidList> getList() {
		return jpaCreateBidList.findAll();
	}

	@SuppressWarnings("unchecked")
	public JSONObject bidItem(String input) throws ParseException, java.text.ParseException {

		Object obj = new JSONParser().parse(input);
		JSONObject json = (JSONObject) obj;

		CreateBidList createBidList = jpaCreateBidList.findOne((String) json.get("bidId"));
		Date startDate = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse(createBidList.getStartDate());
		Date endDate = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse(createBidList.getEndDate());

		Date currentDate = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse((String) json.get("date"));

		JSONObject status = new JSONObject();

		if (currentDate.before(endDate) && currentDate.after(startDate)) {
			if (Integer.valueOf((String) json.get("biddingPrice")) < Integer.valueOf(createBidList.getbidBasePrice())) {
				status.put("status", "Bidding Failed");
				status.put("issue", "Bidding price can not be less than base bid price");
			} else {
				jpaBidList.insert(new BidList((String) json.get("biddingPrice"), (String) json.get("emailId"),
						(String) json.get("date"), (String) json.get("bidId")));
				status.put("status", "success");
			}
		} else {
			status.put("status", "Bidding Failed");
			status.put("issue", "Please check Bidding time");
		}

		return status;
	}

	public List<BidList> allBids() {
		return jpaBidList.findAll();
	}

}
