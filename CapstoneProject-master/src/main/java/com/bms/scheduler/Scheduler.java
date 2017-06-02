package com.bms.scheduler;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import java.util.UUID;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.bms.bidPriceComparator.BidPriceComparator;
import com.bms.config.RabbitMQMessageListener;
import com.bms.config.RabbitMQMessagePublisher;
import com.bms.domain.BidList;
import com.bms.domain.CreateBidList;
import com.bms.repostiries.JpaBidList;
import com.bms.repostiries.JpaCreateBidList;

@Component
public class Scheduler {

	@Autowired
	JpaBidList jpaBidList;

	@Autowired
	JpaCreateBidList jpaCreateBidList;

	@Autowired
	RabbitMQMessagePublisher rabbitMQMessagePublisher;

	@Autowired
	RabbitMQMessageListener rabbitMQMessageListener;

	@SuppressWarnings("unchecked")
	@Scheduled(fixedDelayString = "${db_check}")
	public void scheduler() throws ParseException {

		TimeZone.setDefault(TimeZone.getTimeZone("IST"));
		Date date = new Date();
		System.out.println("#####" + date + "#####");

		List<CreateBidList> createdBidList = jpaCreateBidList.findAll();
		List<BidList> bidLists = jpaBidList.findAll();

		for (CreateBidList createBidList : createdBidList) {
			if (date.after(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse(createBidList.getEndDate()))) {
				List<BidList> successBids = new ArrayList<>();
				for (BidList bidList : bidLists) {
					if (bidList.getBidId().equalsIgnoreCase(createBidList.getId())) {
						successBids.add(bidList);
						jpaBidList.delete(bidList.getId());
					}
				}

				BidList maxBidPrice = Collections.max(successBids, new BidPriceComparator());
				for (BidList obj : successBids) {
					if (maxBidPrice.getBiddingPrice().equalsIgnoreCase(obj.getBiddingPrice())) {
						JSONObject message = new JSONObject();
						message.put("emailID", obj.getEmailId());
						message.put("message", "Congratulations. You got the highest bid.");
						UUID requestID = UUID.randomUUID();
						rabbitMQMessagePublisher.publishMessage(message.toJSONString(), requestID.toString());
					}
				}
				for (BidList bl : successBids) {
					System.out.println("*****" + bl + "*****");
				}
				jpaCreateBidList.delete(createBidList.getId());
			}
		}

		createdBidList.clear();
		bidLists.clear();

	}

}
