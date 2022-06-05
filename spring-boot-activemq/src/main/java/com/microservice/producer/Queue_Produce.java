package com.microservice.producer;


import java.util.UUID;
import javax.annotation.Resource;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;
import org.apache.activemq.ScheduledMessage;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

/**
 * @author guofei
 * @date 2022/6/5 1:50 PM
 *
 */
@Component
public class Queue_Produce {

  @Resource
  private JmsTemplate jmsTemplate;

  @Resource
  private Queue queue;

  public void produceMessage(){
    // 一参是目的地，二参是消息的内容
    jmsTemplate.convertAndSend(queue,"****"+ UUID.randomUUID().toString().substring(0,6));
  }

  /**
   * 发送延迟消息
   * @param destination 队列名称
   * @param message 消息内容
   * @param time 延迟时间 单位 毫秒
   */
  public void produceDelayMessage(String destination, String message, long time){
    jmsTemplate.send(destination, session -> {
      TextMessage textMessage = session.createTextMessage();
      textMessage.setText(message);
      textMessage.setLongProperty(ScheduledMessage.AMQ_SCHEDULED_DELAY, time);
      return textMessage;
    });
  }
}
