package com.client.handler;

import java.util.Map;
import org.java_websocket.client.WebSocketClient;

/**
 * @author guofei
 * @date 2022/6/17 2:24 PM
 * websocket 客户端静态类
 */
public abstract class WebSocketClientAbs {



  /**
   * 创建websocket客户端(获取实例)
   * @param wsUri
   * @param httpHeaders
   * @return
   */
  public abstract WebSocketClient createWebSocketClient(String wsUri,
      Map<String, String> httpHeaders);


  /**
   * 客户端连接
   * @param wsUri
   * @param httpHeaders
   * @return
   */
  public abstract WebSocketClient connect(String wsUri,
      Map<String, String> httpHeaders);



}
