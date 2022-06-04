package com.microservice.test;

import javax.jms.Connection;
import javax.jms.MapMessage;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;
import org.apache.activemq.ActiveMQConnectionFactory;

/**
 * @author guofei
 * @date 2022/6/4 4:15 PM
 */
public class JmsProduce_topic {
  public static final String ACTIVEMQ_URL = "tcp://192.168.17.3:61616";
  public static final String TOPIC_NAME = "topic01";

  public static void main(String[] args) throws  Exception {
    ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(
        ACTIVEMQ_URL);
    Connection connection = activeMQConnectionFactory.createConnection();
    connection.start();
    Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

    Topic topic = session.createTopic(TOPIC_NAME);

    MessageProducer messageProducer = session.createProducer(topic);
    for (int i = 1; i < 4; i++) {
      TextMessage textMessage = session.createTextMessage("topic_name--" + i);
      messageProducer.send(textMessage);
      MapMessage mapMessage = session.createMapMessage();
    }
    messageProducer.close();
    session.close();
    connection.close();
    System.out.println("  **** TOPIC_NAME消息发送到MQ完成 ****");
  }
}
