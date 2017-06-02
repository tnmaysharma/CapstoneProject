package com.bms.controller;

import java.util.List;

import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bms.domain.CreateBidList;
import com.bms.service.CreateBidService;

@CrossOrigin
@RestController
@RequestMapping(value = "/openBid")
public class CreateBidController {

	@Autowired
	CreateBidService createBidService;

	@RequestMapping(value = "/open", method = RequestMethod.POST)
	public List<CreateBidList> openBid(@RequestBody String input) throws ParseException {
		return createBidService.openBid(input);
	}

}
