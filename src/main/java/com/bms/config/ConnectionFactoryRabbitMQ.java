package com.bms.config;

import org.json.simple.parser.ParseException;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.Cloud;
import org.springframework.cloud.CloudFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.bms.utils.Utils;


/** This class is for creating connection factory for cloud to make RabbitMQ connections
 * 
 * @author 540497
 *
 */
@Configuration
public class ConnectionFactoryRabbitMQ {

	@Autowired
	private Utils utils;

	/**
	 * ConnectionFactory class to make rabbitMQ connection
	 * 
	 * @return
	 * @throws ParseException
	 */
	@Bean
	public ConnectionFactory connectionFactory() {
		ConnectionFactory connectionFactory = null;
		CloudFactory cloudFactory = new CloudFactory();
		Cloud cloud = cloudFactory.getCloud();
		String rabbitMqService = utils.getVcapServiceName(System.getenv("VCAP_SERVICES"));
		connectionFactory = cloud.getServiceConnector(rabbitMqService, ConnectionFactory.class, null);
		return connectionFactory;
	}

	/**
	 * RabbitTemplate Instance will be created to send message
	 * 
	 * @return
	 * 
	 */
	@Bean
	public RabbitTemplate rabbitTemplate() {
		RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory());
		rabbitTemplate.setMessageConverter(jsonMessageConverter());
		return rabbitTemplate;
	}

	@Bean
	public MessageConverter jsonMessageConverter() {
		JsonMessageConverter jsonMessageConverter = new JsonMessageConverter();
		return jsonMessageConverter;
	}

	@Bean
	AmqpAdmin amqpAdmin() {
		RabbitAdmin admin = new RabbitAdmin(connectionFactory());
		admin.declareQueue(queue());
		admin.declareExchange(exchange());
		admin.declareBinding(binding(queue(), exchange()));
		return admin;
	}

	/**
	 * to create a queue instance based on the Queue name fetched from property
	 * files
	 * 
	 * @return
	 */
	@Bean
	Queue queue() {
		String queueName = utils.getVcapAppName(System.getenv("VCAP_APPLICATION")).concat(".queue");
		return new Queue(queueName, true);
	}

	/**
	 * to create a Direct Exchange instance which will send the message based on
	 * the router key
	 * 
	 * @return
	 */
	@Bean
	DirectExchange exchange() {
		String exchange = utils.getVcapAppName(System.getenv("VCAP_APPLICATION")).concat(".exchange");
		return new DirectExchange(exchange);
	}

	/**
	 * will create a binding instance for queue and exchange based on the router
	 * key
	 * 
	 * @param queue
	 * @param exchange
	 * @return
	 */
	@Bean
	Binding binding(Queue queue, DirectExchange exchange) {
		String queueName = utils.getVcapAppName(System.getenv("VCAP_APPLICATION")).concat(".queue");
		return BindingBuilder.bind(queue).to(exchange).with(queueName);
	}

}
