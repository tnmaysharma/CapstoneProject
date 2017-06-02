package com.bms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import com.bms.domain.Status;

@Service
public class EmailService {

	@Autowired
	private MailSender mailSender;

	public Status send(SimpleMailMessage msg) {
		try {
			mailSender.send(msg);
		} catch (Exception e) {
			return new Status("failure", e.getMessage());
		}
		return new Status("success", "Mail Sent Successfully");
	}
}
