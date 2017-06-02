package com.bms.utils;

import java.util.Date;
import java.util.Map.Entry;
import java.util.Set;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 * Utils Class with methods to get app and service names
 * 
 * @author 540497
 *
 */
@Component
public class Utils {

	/**
	 * Method to get the service name from environment variables
	 * 
	 * @param vcap_service
	 * @return
	 */
	public String getVcapServiceName(String vcap_service) {
		JSONParser jsonParser = new JSONParser();
		JSONObject bluePrintObject;
		String serviceName = null;
		try {
			bluePrintObject = (JSONObject) jsonParser.parse(vcap_service);
			for (Object key : bluePrintObject.keySet()) {
				if (key.toString().toLowerCase().contains("mq")) {
					JSONArray obj = (JSONArray) bluePrintObject.get(key.toString());
					JSONObject object = (JSONObject) obj.get(0);
					serviceName = (String) object.get("name");
				}

			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return serviceName;

	}

	/*
	 * public String getMongoVcapServiceName(String vcap_service) { JSONParser
	 * jsonParser = new JSONParser(); JSONObject bluePrintObject; String
	 * serviceName = null; try { bluePrintObject = (JSONObject)
	 * jsonParser.parse(vcap_service); for (Object key :
	 * bluePrintObject.keySet()) { if
	 * (key.toString().toLowerCase().contains("mlab")) { JSONArray obj =
	 * (JSONArray) bluePrintObject.get(key.toString()); JSONObject object =
	 * (JSONObject) obj.get(0); serviceName = (String) object.get("name"); }
	 * 
	 * } } catch (ParseException e) { // TODO Auto-generated catch block
	 * e.printStackTrace(); } return serviceName;
	 * 
	 * }
	 */ /**
		 * Method to get the app name from environment variables
		 * 
		 * @param vcap_app
		 * @return
		 */
	public String getVcapAppName(String vcap_app) {
		String appName = null;
		JSONParser jsonParser = new JSONParser();
		JSONObject bluePrintObject;
		try {
			bluePrintObject = (JSONObject) jsonParser.parse(vcap_app);
			appName = (String) bluePrintObject.get("application_name");

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return appName;
	}

	public JsonObject getCredentials(String name) {
		try {
			String vcap_string = System.getenv("VCAP_SERVICES");
			JsonObject vcap_services = (JsonObject) new JsonParser().parse(vcap_string);
			Set<Entry<String, JsonElement>> entries = vcap_services.entrySet();
			for (Entry<String, JsonElement> eachEntry : entries) {
				if (eachEntry.getKey().toLowerCase().contains(name)) {
					vcap_services = (JsonObject) ((JsonArray) eachEntry.getValue()).get(0);
					JsonObject credentialsObj = (JsonObject) vcap_services.get("credentials");
					return credentialsObj;
				}
			}
			JsonArray userProvided = (JsonArray) vcap_services.get("user-provided");
			if (userProvided != null) {
				for (JsonElement cupsElement : userProvided) {
					JsonObject cups = cupsElement.getAsJsonObject();
					String cupsName = cups.get("name").getAsString();
					if (cupsName.toLowerCase().contains(name)) {
						return cups.get("credentials").getAsJsonObject();
					}
				}
			}
		} catch (Exception e) {
			return null;
		}
		return null;
	}

}
