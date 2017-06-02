package com.bms.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.MailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import com.bms.utils.Utils;
import com.google.gson.JsonObject;

@Configuration
public class SendGridConfig {
	
	@Autowired
	private Utils utils;
	
	@Bean
	public MailSender mailSender() {
		JsonObject cloudConfig = utils.getCredentials("sendgrid");
		JavaMailSenderImpl mailSenderImpl = new JavaMailSenderImpl();
		mailSenderImpl.setHost(cloudConfig.get("hostname").getAsString());
		mailSenderImpl.setUsername(cloudConfig.get("username").getAsString());
		mailSenderImpl.setPassword(cloudConfig.get("password").getAsString());
		return mailSenderImpl;
	}

}
