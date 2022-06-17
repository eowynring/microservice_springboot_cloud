package com.client.listener;

import cn.hutool.core.date.DateUtil;
import com.client.config.SocketClient;
import com.client.constant.WebSocketConfigConstant;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDateTime;
import java.util.Date;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.java_websocket.client.WebSocketClient;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author guofei
 * @date 2022/6/16 7:13 PM
 */
@Component
@Slf4j
public class WebSocketHeartListener {

  @Resource
  private WebSocketClient socketClient;

  //@Async
  //@Scheduled(cron = "0/5 * * * * ?")
  public void WebSocketHeartScheduled(){
    int isConnect = WebSocketConfigConstant.isConnect;
    log.info("心跳检测 -> isConnect： {}-{}", isConnect, ((isConnect == 1) ? "连接中" : "未连接"));
    if (1!=isConnect){
      String now = DateUtil.format(new Date(), "yyyy-MM-dd HH:mm:ss");
      log.info("心跳检测 -> 连接异常，时间：{}，尝试重新连接---", now);
      socketClient.reconnect();
      //reconnect();
    }
  }

  public static void reconnect()  {
    SocketClient webSocketClient = null;
    try {
      webSocketClient = new SocketClient(
          new URI("ws://127.0.0.1:8888/websocket/client1"));
    } catch (URISyntaxException e) {
      log.error("URISyntaxException,errorMsg->{}", e.getMessage(), e);
    }
    webSocketClient.connect();
  }

}
