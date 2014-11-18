package org.tobby.jms.spring.client;

import java.util.Date;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.tobby.jms.spring.client.util.Util;

public class SendByJmsTemplate {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = 
				new AnnotationConfigApplicationContext("org.tobby.jms.spring.client.config");
		MessageCreator messageCreator = new MessageCreator() {
			public Message createMessage(Session session) throws JMSException {
				return session.createTextMessage(Util.formatDate(new Date()) + "--Ping Ping Ping!");
			}
		};
		//ConfigurableApplicationContext context = 
		//		new ClassPathXmlApplicationContext("applicationContext.xml");
		JmsTemplate jmsTemplate = (JmsTemplate)context.getBean("jmsTemplate");
		jmsTemplate.send("mailbox-destination", messageCreator);
		context.close();
	}
}
