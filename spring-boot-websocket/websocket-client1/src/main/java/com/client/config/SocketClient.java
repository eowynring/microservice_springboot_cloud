package com.client.config;


import com.client.constant.WebSocketConfigConstant;
import java.net.URI;
import lombok.extern.slf4j.Slf4j;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

/**
 * @author guofei
 * @date 2022/6/16 4:03 PM
 */
@Slf4j
public class SocketClient extends WebSocketClient {


  public SocketClient(URI serverUri) {
    super(serverUri);
  }

  @Override
  public void onOpen(ServerHandshake serverHandshake) {
    WebSocketConfigConstant.isConnect=1;
    log.info("------ MyWebSocket onOpen ------");
  }

  @Override
  public void onMessage(String s) {
    WebSocketConfigConstant.isConnect=2;
    log.info("-------- 接收到服务端数据：{} ", s);
  }

  @Override
  public void onClose(int i, String s, boolean b) {
    WebSocketConfigConstant.isConnect=0;
    log.info("------ MyWebSocket onClose ------{}", s);
    //断开时尝试重连
    int a = 10;
    do {
      a--;
      //this.reconnect();
      this.connect();
      log.info("开始重连，第[{}]次重连",a);
    }
    while(a>0);
  }

  @Override
  public void onError(Exception e) {
    WebSocketConfigConstant.isConnect=0;
    log.info("------ MyWebSocket onError ------{}", e);
  }
}
