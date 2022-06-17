package com.client.handler;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author guofei
 * @date 2022/6/17 2:54 PM
 */
@Component
@Slf4j
public class WebSocketHeartbeatTimer {

  @Resource
  private WsClientOfLocal wsClientOfLocal;

  private String localWebSocketUrl = "ws://127.0.0.1:8888/websocket/client3";

  private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

  /**
   * Local WebSocket连接心跳检测，重连机制，每20秒触发一次
   * 注意 @Async 注解，要使用异步线程的方式来执行心跳检测，避免任务线程被其他任务占用
   */
  @Async
  @Scheduled(cron = "0/20 * * * * ?")
  public void wsHeartbeatOfLocal() {
    try {
      int isConnect = WsClientOfLocal.isConnect;
      log.info("心跳检测 -> WsClientOfLocal： {}-{}", isConnect, ((isConnect == 1) ? "连接中" : "未连接"));
      if (1 != WsClientOfLocal.isConnect) {
        String now = DATE_TIME_FORMATTER.format(LocalDateTime.now());
        log.info("心跳检测 -> WsClientOfLocal连接异常，时间：{}，尝试重新连接---", now);
        wsClientOfLocal.connect(localWebSocketUrl, null);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

}
