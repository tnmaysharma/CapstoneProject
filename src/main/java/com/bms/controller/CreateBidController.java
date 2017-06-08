package com.bms.controller;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
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
@RequestMapping(value = "/opennigBid")
public class CreateBidController {

	@Autowired
	CreateBidService createBidService;

	@RequestMapping(value = "/openBid", method = RequestMethod.POST)
	public JSONObject openBid(@RequestBody String input) throws ParseException, InvalidKeyException, NoSuchAlgorithmException, UnsupportedEncodingException, java.text.ParseException {

		TimeZone.setDefault(TimeZone.getTimeZone("IST"));
		Date date = new Date();
		JSONObject inputJSON = (JSONObject) new JSONParser().parse(input);
		JSONObject output =new JSONObject();
		if(inputJSON.containsKey("hashcode")){
			output = createBidService.openBid(input, date);
		}
		else{
			output.put("info", "This API is reserved for admin. Kindly provide valid inputs");
		}
		return output;
	}
	
	@RequestMapping(value = "/bidList", method = RequestMethod.GET)
	public List<CreateBidList> getList() throws ParseException, java.text.ParseException {
		return createBidService.getList();

	}

}
