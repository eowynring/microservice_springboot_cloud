package com.client.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author guofei
 * @date 2022/6/17 2:28 PM
 * 消息处理接口
 */
@Slf4j
@Component
public class MessageService {

  /**
   * 处理消息
   * @param s
   */
  void handleMessage(String s){
    log.info("handleMessage -> 开始处理消息---");
    System.out.println("handleMessage -> 接收服务端消息: " + s);
    log.info("handleMessage -> 消息处理完成.");
  }
}
