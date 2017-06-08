package com.bms.service;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Hex;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.bms.domain.CreateBidList;
import com.bms.repostiries.JpaCreateBidList;

@Service
public class CreateBidService {

	@Autowired
	private JpaCreateBidList jpaCreateBidList;
	
	@Value("${secret_api_key}")
	private String apiKey;

	@SuppressWarnings("unchecked")
	public JSONObject openBid(String input, Date date) throws ParseException, java.text.ParseException,
			InvalidKeyException, NoSuchAlgorithmException, UnsupportedEncodingException {

		Object obj = new JSONParser().parse(input);
		JSONObject json = (JSONObject) obj;

		Date startDate = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse((String) json.get("startDate"));
		Date endDate = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse((String) json.get("endDate"));

		JSONObject status = new JSONObject();
		String bodyForHMAC = apiKey + json.get("timestamp").toString() + json.get("nonce").toString();
		String hashCode = generateHMAC(bodyForHMAC, apiKey);
		if (json.get("hashcode").toString().equalsIgnoreCase(hashCode)) {
			if (endDate.after(startDate) && date.before(endDate)) {
				jpaCreateBidList.insert(new CreateBidList((String) json.get("startDate"), (String) json.get("endDate"),
						(String) json.get("productName"), (String) json.get("productId"),
						(String) json.get("bidBasePrice")));

				status.put("status", "success");
			} else {
				status.put("status", "Bid creation failed");
				status.put("info", "Please check start and end date");
			}
		} else {
			status.put("status", "Bid creation failed");
			status.put("info", "This API is reserved for admin. Kindly check the input hashcode");

		}

		return status;
	}

	public List<CreateBidList> getList() {
		return jpaCreateBidList.findAll();
	}

	/**
	 * @param body
	 * @param secretAPIKey
	 * @return
	 * @throws InvalidKeyException
	 * @throws NoSuchAlgorithmException
	 * @throws UnsupportedEncodingException
	 */
	private String generateHMAC(String body, String secretAPIKey)
			throws InvalidKeyException, NoSuchAlgorithmException, UnsupportedEncodingException {
		Mac mac;
		byte[] bytesKey = secretAPIKey.getBytes();
		final SecretKeySpec secretKey = new SecretKeySpec(bytesKey, "HmacSHA512");
		mac = Mac.getInstance("HmacSHA512");
		mac.init(secretKey);
		final byte[] macData = mac.doFinal(body.getBytes());
		byte[] hex = new Hex().encode(macData);
		return new String(hex, "ISO-8859-1");
	}

}
