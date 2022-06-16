package com.client.config;


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
    log.info("------ MyWebSocket onOpen ------");
  }

  @Override
  public void onMessage(String s) {
    log.info("-------- 接收到服务端数据：{} ", s);
  }

  @Override
  public void onClose(int i, String s, boolean b) {
    log.info("------ MyWebSocket onClose ------{}", s);
  }

  @Override
  public void onError(Exception e) {
    log.info("------ MyWebSocket onError ------{}", e);
  }
}
