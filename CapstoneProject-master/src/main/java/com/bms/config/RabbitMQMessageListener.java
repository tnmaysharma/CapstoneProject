package com.bms.config;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.bms.utils.ListenAndTriggerMail;

@Configuration
public class RabbitMQMessageListener {
	
	@Autowired
	private ConnectionFactoryRabbitMQ connectionFactoryRabbitMQ;
	
	@Autowired
	private ListenAndTriggerMail listenAndTriggerMail;
	
	@Autowired
	private Queue Queue;
	
	/**
	 * @return adapter bean to fetch message from the queue
	 */
	@Bean
	public SimpleMessageListenerContainer messageListenerContainer() {
		SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
		container.setConnectionFactory(connectionFactoryRabbitMQ.connectionFactory());
		container.addQueueNames(Queue.getName());
		container.setMessageListener(Listener());
		return container;
	}

	/**
	 * @return listener bean to receive the message
	 */
	@Bean
	public MessageListener Listener() {
		return new MessageListener() {
			public void onMessage(Message message) {
				listenAndTriggerMail.message(message);
			}
		};
	}

}
