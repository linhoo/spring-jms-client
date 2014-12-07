package org.tobby.jms.spring.client.config;

import javax.jms.Queue;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableAutoConfiguration
@ComponentScan("org.tobby.jms.spring.client")
public class AppConfig {

//	@Bean
//	public ConnectionFactory connectionFactory() {
//		ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
//		connectionFactory.setBrokerURL("tcp://localhost:61616");
//		return connectionFactory;
//	}
	
//	@Bean
//	public JmsTemplate jmsTemplate() {
//		return new JmsTemplate(connectionFactory());
//	}
	
	@Bean
	public Queue queue() {
		return new ActiveMQQueue("mailbox-destination");
	}
	
}
