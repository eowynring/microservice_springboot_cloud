package com.microservice.consumer;

import javax.jms.JMSException;
import javax.jms.TextMessage;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 * @author guofei
 * @date 2022/6/5 2:00 PM
 */
@Component
public class Queue_consummer {

  @JmsListener(destination = "${myqueue}")
  public void receive(TextMessage textMessage) throws JMSException {
    System.out.println(" ***  消费者收到消息  ***"+textMessage.getText());
  }
}
