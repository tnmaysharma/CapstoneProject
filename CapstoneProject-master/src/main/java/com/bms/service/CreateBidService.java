package com.bms.service;

import java.util.List;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bms.domain.CreateBidList;
import com.bms.repostiries.JpaCreateBidList;

@Service
public class CreateBidService {

	@Autowired
	private JpaCreateBidList jpaCreateBidList;

	public List<CreateBidList> openBid(String input) throws ParseException {

		Object obj = new JSONParser().parse(input);
		JSONObject json = (JSONObject) obj;

		jpaCreateBidList.insert(new CreateBidList((String) json.get("startDate"), (String) json.get("endDate"),
				(String) json.get("productName"), (String) json.get("productId"), (String) json.get("bidBasePrice")));
		return jpaCreateBidList.findAll();
	}

}
