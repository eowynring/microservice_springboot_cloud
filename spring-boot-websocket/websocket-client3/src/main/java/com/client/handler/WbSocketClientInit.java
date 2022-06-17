package com.client.handler;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author guofei
 * @date 2022/6/17 2:42 PM
 */
@Component
@Slf4j
public class WbSocketClientInit {

  @Resource
  private WsClientOfLocal wsClientOfLocal;

  //@Value("${local-websocket-url}")
  private String localWebSocketUrl = "ws://127.0.0.1:8888/websocket/client3";

  @PostConstruct
  public void initWebSocketClient(){
    ThreadPoolExecutor executor = new ThreadPoolExecutor(
        2,
        5,
        0L,
        TimeUnit.MILLISECONDS,
        new LinkedBlockingQueue<>());
    executor.execute(()->initWebSocketClientOfLocal());
    executor.shutdown();
  }

  private void initWebSocketClientOfLocal() {
    log.info("initWebSocketClientOfLocal -> webSocket首次连接开始");
    wsClientOfLocal.connect(localWebSocketUrl, null);
    log.info("initWebSocketClientOfLocal -> webSocket首次连接完毕");
  }

}
