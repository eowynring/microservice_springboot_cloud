package com.microservice.producer;


import java.util.UUID;
import javax.annotation.Resource;
import javax.jms.Destination;
import javax.jms.Queue;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Component;

/**
 * @author guofei
 * @date 2022/6/5 1:50 PM
 *
 */
@Component
public class Queue_Produce {

  @Resource
  private JmsMessagingTemplate jmsMessagingTemplate;

  @Resource
  private Queue queue;

  public void produceMessage(){
    // 一参是目的地，二参是消息的内容
    jmsMessagingTemplate.convertAndSend(queue,"****"+ UUID.randomUUID().toString().substring(0,6));
  }
}
