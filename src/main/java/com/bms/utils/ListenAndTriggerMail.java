package com.bms.utils;

import org.springframework.amqp.core.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;

import com.bms.domain.SimpleMailMessageVO;
import com.bms.domain.Status;
import com.bms.service.EmailService;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@Component
public class ListenAndTriggerMail {
	
	@Autowired
	private EmailService emailService;

	public void message(Message arg0) {
		String messageContent = new String(arg0.getBody());
		System.out.println("====================================================messageContent: " + messageContent);

		JsonObject messageContentJson = (JsonObject) new JsonParser().parse(messageContent);
		String emailIDs = messageContentJson.get("emailID").toString().replaceAll("\"", "");
		String to[] = emailIDs.split(";");
		
		String text = messageContentJson.get("message").toString().replaceAll("\"", "");
		SimpleMailMessageVO emailMessageVO = new SimpleMailMessageVO(to, text);
		System.out.println("====================================================emailMessageVO: " + emailMessageVO);

		SimpleMailMessage emailMessage = new SimpleMailMessage();
		emailMessage.setFrom(emailMessageVO.getFrom());
		emailMessage.setTo(emailMessageVO.getTo());
		emailMessage.setSubject(emailMessageVO.getSubject());
		emailMessage.setText(emailMessageVO.getText().concat(emailMessageVO.getfooter()));
		System.out.println("====================================================emailMessage: " + emailMessage);

		Status status = emailService.send(emailMessage);
		System.out.println("====================================================status: " + status);
	}

}
