package com.microservice.config;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.stereotype.Component;

/**
 * @author guofei
 * @date 2022/6/5 1:48 PM
 */
@Component
@EnableJms
public class ConfigBean {

  @Value("${myqueue}")
  private String myQueueName;

  @Bean
  public ActiveMQQueue queue(){
    return new ActiveMQQueue(myQueueName);
  }

}
