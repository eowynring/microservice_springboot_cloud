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
 * @date 2022/6/5 1:36 PM
 * 事务演示 Producer
 */
public class Jms_TX_Producer {

  public static final String ACTIVEMQ_URL = "tcp://120.46.149.32:61616";

  public  static  final String QUEUE_NAME = "Queue_TX";

  public static void main(String[] args) throws JMSException {
    ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(ACTIVEMQ_URL);
    Connection connection = activeMQConnectionFactory.createConnection();
    connection.start();
    //1.创建会话session，两个参数transacted=事务,acknowledgeMode=确认模式(签收)
    //设置为开启事务
    Session session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);
    Queue queue = session.createQueue(QUEUE_NAME);
    MessageProducer producer = session.createProducer(queue);
    try {
      for (int i = 0; i < 3; i++) {
        TextMessage textMessage = session.createTextMessage("tx msg--" + i);
        producer.send(textMessage);
        if(i == 2){
          throw new RuntimeException("GG.....");
        }
      }
      // 2. 开启事务后，使用commit提交事务，这样这批消息才能真正的被提交。
      session.commit();
      System.out.println("消息发送完成");
    }catch (Exception e){
      System.out.println("出现异常,消息回滚");
      // 3. 工作中一般，当代码出错，我们在catch代码块中回滚。这样这批发送的消息就能回滚。
      session.rollback();
    }finally {
      //4. 关闭资源
      producer.close();
      session.close();
      connection.close();
    }
  }
}
