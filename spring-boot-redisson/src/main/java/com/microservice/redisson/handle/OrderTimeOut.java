package com.microservice.redisson.handle;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@Slf4j
public class OrderTimeOut implements RedisDelayQueueHandle<Map>{
    @Override
    public void execute(Map o) {
      log.info("收到超时订单延迟消息{}",o);
    }
}
