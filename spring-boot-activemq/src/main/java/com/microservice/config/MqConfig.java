package com.microservice.config;

import com.microservice.constant.QueueNameConstant;

import javax.jms.Queue;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author guofei
 * @date 2022/3/30 5:57 PM
 */
@Configuration
public class MqConfig {

  @Bean
  public Queue testQueue(){
    return new ActiveMQQueue(QueueNameConstant.TEST_QUEUE);
  }

}
