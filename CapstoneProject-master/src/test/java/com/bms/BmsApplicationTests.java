package com.bms;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.bms.bidPriceComparator.BidPriceComparator;
import com.bms.domain.BidList;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BmsApplicationTests {

	@Test
	public void contextLoads() {
	}

	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws ParseException {
		/*JSONObject json = new JSONObject();
		json.put("date", "28/05/2017 20:00:00");
		CreateBidList createBidList = new CreateBidList("28/05/2017 18:00:00", "28/05/2017 21:00:00", "", "", "50000");

		Date startDate = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse(createBidList.getStartDate());
		Date endDate = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse(createBidList.getEndDate());

		Date date = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse((String) json.get("date"));

		if (date.before(endDate) && date.after(startDate)) {
			System.out.println(date);
			System.out.println(startDate);
			System.out.println(endDate);
		} else {
			// "Bidding closed";
		}*/
		
		/*CreateBidList createBidList = new CreateBidList("27/05/2017 00:00:00", "28/05/2017 00:00:00", "", "", "50000");
		JSONObject json = new JSONObject();
		json.put("biddingPrice", "55000");
		if (Integer.valueOf(createBidList.getbidBasePrice()) < Integer.valueOf((String) json.get("biddingPrice"))) {
			System.out.println("");
		}*/
		
		BidList b1 = new BidList("51000","abd","28/05/2017 21:10:00","592aee88d2218800127b1432");
		BidList b2 = new BidList("78000","abd","28/05/2017 21:10:00","592aee88d2218800127b1432");
		BidList b3 = new BidList("90000","abd","28/05/2017 21:10:00","592aee88d2218800127b1432");
		BidList b4 = new BidList("54000","abd","28/05/2017 21:10:00","592aee88d2218800127b1432");
		BidList b5 = new BidList("90000","abd","28/05/2017 21:10:00","592aee88d2218800127b1432");
		
		List<BidList> bl = new ArrayList<>();
		bl.add(b1);
		bl.add(b2);
		bl.add(b3);
		bl.add(b4);
		bl.add(b5);
		
		//System.out.println(Collections.max(bl, new BidPriceComparator()));
		Collections.sort(bl, new BidPriceComparator());
		
		for(BidList b : bl) {
			System.out.println(b);
		}
	}

}
