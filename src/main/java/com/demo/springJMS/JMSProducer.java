package com.demo.springJMS;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.jms.*;

@Component
public class JMSProducer {

  @Autowired
  private ConnectionFactory connectionFactory;
  private JmsTemplate jmsTemplate;

  @PostConstruct
  public void init() {
      this.jmsTemplate = new JmsTemplate(connectionFactory);
  }

  public void sendMessage(String message) {
      jmsTemplate.send(QueueName.PRINT_NAME, new MessageCreator() {
          @Override
          public Message createMessage(Session session) throws JMSException {
              return session.createTextMessage(message);
          }
      });
  }
  
  public void updateStaff(MessageInfo message) {
      jmsTemplate.send(QueueName.HANDLE_BATCH, new MessageCreator() {
          @Override
          public Message createMessage(Session session) throws JMSException {
        	  ObjectMessage objectMessage = session.createObjectMessage(message);
        	  objectMessage.setObject(message);
        	  return objectMessage;
          }
      });
  }
  
}