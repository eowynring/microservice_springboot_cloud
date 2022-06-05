package com.microservice.test;

import javax.jms.Connection;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;
import org.apache.activemq.ActiveMQConnectionFactory;

/**
 * @author guofei
 * @date 2022/6/4 11:16 AM
 */
public class JmsProduce {

  /**
   * linux 上部署的activemq 的 IP 地址 + activemq 的端口号
   */
  public static final String ACTIVEMQ_URL = "tcp://120.46.149.32:61616";

  /**
   * 目的地的名称
   */
  public  static  final String QUEUE_NAME = "test-queue";

  public static void main(String[] args) throws JMSException {
    ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(
        ACTIVEMQ_URL);
    Connection connection = activeMQConnectionFactory.createConnection();
    connection.start();
    Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
    Queue queue = session.createQueue(QUEUE_NAME);
    MessageProducer producer = session.createProducer(queue);
    for (int i = 0; i < 4; i++) {
      TextMessage textMessage = session.createTextMessage("msg--msg" + i);
      producer.send(textMessage);
    }
    producer.close();
    session.close();
    connection.close();
    System.out.println("发送完成");
  }



}
