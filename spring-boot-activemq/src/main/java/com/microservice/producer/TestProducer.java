package com.microservice.producer;

import com.microservice.constant.QueueNameConstant;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.Session;
import lombok.extern.slf4j.Slf4j;
import org.apache.activemq.ScheduledMessage;
import org.springframework.boot.autoconfigure.jms.JmsProperties;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Component;

/**
 * @author guofei
 * @date 2022/3/30 6:04 PM
 */
@Component
@Slf4j
public class TestProducer {

  @Resource
  private JmsMessagingTemplate jmsMessagingTemplate;
  @Resource
  private List<Queue> queues;

  /**
   * 发送消息（更新设备状态）
   * @param message    发送的消息内容
   * @param delayTime  延迟队列时间（单位：min）
   */
  public void sendTestMessage(String message, int delayTime){
    log.info("压入mqMessage：[{}], time:[{}]", message, delayTime);
    long time = delayTime * 60 * 1000L;
    matchingQueue(QueueNameConstant.TEST_QUEUE, message, time);
  }

  /**
   * 根据队列名称匹配
   *
   * @param queueNameParam 队列名称
   * @param message        发送消息的实体
   * @param delayTime      延迟时间（单位：毫秒）
   */
  private void matchingQueue(String queueNameParam, String message, long delayTime) {
    for (Queue queue : queues
    ) {
      try {
        String queueName = queue.getQueueName();
        if (queueNameParam.equals(queueName)) {
          if(delayTime!=0){
            delaySend(queue,message,delayTime);
          }else{
            jmsMessagingTemplate.convertAndSend(queue, message);
          }
        }
      } catch (JMSException e) {
        log.error("发送消息队列失败", e);
      }
    }
  }

  /**
   * 延迟发送
   * @param destination 发送的队列
   * @param data 发送的消息
   * @param time 延迟时间
   * @param <T>
   */
  public <T extends Serializable> void delaySend(Destination destination, T data, Long time) {
    Connection connection = null;
    Session session = null;
    MessageProducer producer = null;
    // 获取连接工厂
    ConnectionFactory connectionFactory = jmsMessagingTemplate.getConnectionFactory();
    try {
      // 获取连接
      connection = connectionFactory.createConnection();
      connection.start();
      // 获取session，true开启事务，false关闭事务
      session = connection.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);
      // 创建一个消息队列
      producer = session.createProducer(destination);
      producer.setDeliveryMode(JmsProperties.DeliveryMode.PERSISTENT.getValue());
      ObjectMessage message = session.createObjectMessage(data);
      // 设置延迟时间
      message.setLongProperty(ScheduledMessage.AMQ_SCHEDULED_DELAY, time);
      // 发送消息
      producer.send(message);
      log.info("发送消息：{}", data);
      session.commit();
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      try {
        if (producer != null) {
          producer.close();
        }
        if (session != null) {
          session.close();
        }
        if (connection != null) {
          connection.close();
        }
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }
}
