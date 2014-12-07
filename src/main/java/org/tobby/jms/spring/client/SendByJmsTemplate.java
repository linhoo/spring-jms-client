package org.tobby.jms.spring.client;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Queue;
import javax.jms.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;
import org.tobby.jms.spring.client.config.AppConfig;
import org.tobby.jms.spring.client.util.Util;

@Component
public class SendByJmsTemplate {

	@Autowired
	private JmsTemplate jmsTemplate;

	@Autowired
	private Queue queue;

//	@Autowired
//	private void setConnectionFactory(ConnectionFactory connectionFactory) {
//		jmsTemplate = new JmsTemplate(connectionFactory);
//	}

//	@Autowired
//	private void setQueue(Queue queue) {
//		this.queue = queue;
//	}

	public static void main(String[] args) {
//		AnnotationConfigApplicationContext context = 
//				new AnnotationConfigApplicationContext("org.tobby.jms.spring.client.config");
//		MessageCreator messageCreator = new MessageCreator() {
//			public Message createMessage(Session session) throws JMSException {
//				return session.createTextMessage(Util.formatDate(new Date()) + "--Ping Ping Ping!");
//			}
//		};
		//ConfigurableApplicationContext context = 
		//		new ClassPathXmlApplicationContext("applicationContext.xml");
		SpringApplication application = new SpringApplication(AppConfig.class);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("spring.activemq.broker-url", "tcp://localhost:61616");
		application.setDefaultProperties(map);
		application.setShowBanner(false);
		ConfigurableApplicationContext context = application.run(args);
		SendByJmsTemplate send = (SendByJmsTemplate)context.getBean("sendByJmsTemplate");
		send.simpleSend(Util.formatDate(new Date()) + "--Ping Ping Ping From default broker urls!");
		context.close();
	}

	public void simpleSend(final String text) {
		this.jmsTemplate.send(this.queue, new MessageCreator() {
			public Message createMessage(Session session) throws JMSException {
				return session.createTextMessage(text);
			}
		});
	}
}
