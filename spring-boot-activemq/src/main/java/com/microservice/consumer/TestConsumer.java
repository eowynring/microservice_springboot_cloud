package com.microservice.consumer;

import com.microservice.constant.QueueNameConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 * @author guofei
 * @date 2022/3/30 6:26 PM
 */
@Component
@Slf4j
public class TestConsumer {

  @JmsListener(destination = QueueNameConstant.TEST_QUEUE)
  private void testConsumer(String message){
    log.info("消费消息------->{}",message);
  }

}
