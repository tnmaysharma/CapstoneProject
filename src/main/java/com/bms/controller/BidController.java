package com.bms.controller;

import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bms.domain.BidList;
import com.bms.service.BidService;

@CrossOrigin
@RestController
@RequestMapping(value = "/biddingService")
public class BidController {

	@Autowired
	BidService bidService;

	@RequestMapping(value = "/bidOnItem", method = RequestMethod.POST)
	public JSONObject getList(@RequestBody String input) throws ParseException, java.text.ParseException {
		TimeZone.setDefault(TimeZone.getTimeZone("IST"));
		Date date = new Date();
		return bidService.bidItem(input, date);
	}

	@RequestMapping(value = "/listAllBids", method = RequestMethod.GET)
	public List<BidList> allBids() {
		return bidService.allBids();
	}
}
