package com.microservice.test;

import java.io.IOException;
import javax.jms.Connection;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;
import org.apache.activemq.ActiveMQConnectionFactory;

/**
 * @author guofei
 * @date 2022/6/5 1:40 PM
 */
public class Jms_TX_Consumer {

  public static final String ACTIVEMQ_URL = "tcp://120.46.149.32:61616";

  public  static  final String QUEUE_NAME = "Queue_TX";


  public static void main(String[] args) throws JMSException, IOException {
    ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(ACTIVEMQ_URL);
    Connection connection = activeMQConnectionFactory.createConnection();
    connection.start();
    // 创建会话session，两个参数transacted=事务,acknowledgeMode=确认模式(签收)
    // 消费者开启了事务就必须手动提交，不然会重复消费消息
    final Session session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);
    Queue queue = session.createQueue(QUEUE_NAME);
    MessageConsumer messageConsumer = session.createConsumer(queue);
    messageConsumer.setMessageListener(new MessageListener() {
      int a = 0;
      @Override
      public void onMessage(Message message) {
        if (message instanceof TextMessage) {
          try {
            TextMessage textMessage = (TextMessage) message;
            System.out.println("***消费者接收到的消息:   " + textMessage.getText());
            if(a == 0){
              System.out.println("commit");
              session.commit();
            }
            if (a == 2) {
              System.out.println("rollback");
              session.rollback();
            }
            a++;
          } catch (Exception e) {
            System.out.println("出现异常，消费失败，放弃消费");
            try {
              session.rollback();
            } catch (JMSException ex) {
              ex.printStackTrace();
            }
          }
        }
      }
    });
    //关闭资源
    System.in.read();
    messageConsumer.close();
    session.close();
    connection.close();
  }

}
