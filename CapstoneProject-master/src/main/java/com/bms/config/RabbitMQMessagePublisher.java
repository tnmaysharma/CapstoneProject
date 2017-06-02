package com.bms.config;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageDeliveryMode;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/** This class is for publishing the messages via RabbitMQ
 * @author 540497
 *
 */
@Component
public class RabbitMQMessagePublisher {

	@Autowired
	private RabbitTemplate rabbitTemplate;

	@Autowired
	private Queue Queue;

	/*
	 * method to publish the json message in to rabbit MQ server and returns the
	 * unique id
	 * */
	
	public void publishMessage(String messageContent, String requestGuid) {
		MessageProperties messageProperties = new MessageProperties();
		messageProperties.setCorrelationIdString(requestGuid);
		messageProperties.setDeliveryMode(MessageDeliveryMode.PERSISTENT);
		Message message = new Message(messageContent.getBytes(), messageProperties);
		rabbitTemplate.send(Queue.getName(), message);
	}

}
