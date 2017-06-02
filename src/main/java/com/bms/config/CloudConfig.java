package com.bms.config;

import org.springframework.cloud.config.java.AbstractCloudConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;

@Configuration
public class CloudConfig extends AbstractCloudConfig {
	
	@Bean
	public MongoDbFactory documentMongoDbFactory() {
		return connectionFactory().mongoDbFactory();
	}
	
}
