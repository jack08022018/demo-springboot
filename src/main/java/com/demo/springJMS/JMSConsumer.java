package com.demo.springJMS;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.ObjectMessage;

@Component
public class JMSConsumer {

	Logger logger = LoggerFactory.getLogger(JMSConsumer.class);
	
	@JmsListener(destination = QueueName.PRINT_NAME)
	public void handleMessage(String message) {
		logger.info("received: " + message);
		MessageInfo info = new MessageInfo(Integer.parseInt(message));
	}
	
	@JmsListener(destination = QueueName.HANDLE_BATCH)
	public void updateStaff(ObjectMessage objectMessage) {
		try {
			MessageInfo message = (MessageInfo)objectMessage.getObject();
			logger.info("received: " + message);
		} catch (Exception e) {
			logger.error("\nupdateStaff:\n", e);
		}
	}
	
}