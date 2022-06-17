package com.client.handler;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;
import javax.annotation.Resource;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import org.junit.platform.commons.util.StringUtils;
import org.springframework.stereotype.Component;

/**
 * @author guofei
 * @date 2022/6/17 2:26 PM
 * ws实例连接，单例模式
 */
@Slf4j
@Component
@Data
public class WsClientOfLocal extends WebSocketClientAbs{

  @Resource
  private MessageService messageService;

  private WebSocketClient wsClient;

  private String msgType;

  /**
   * 连接状态 0连接断开或异常 1连接中 2正在连接
   */
  public static  int isConnect = 0;


  @Override
  public WebSocketClient createWebSocketClient(String wsUri, Map<String, String> httpHeaders) {
    try {
      //创建客户端连接对象
      WebSocketClient client = new WebSocketClient(new URI(wsUri), httpHeaders) {

        /**
         * 建立连接调用
         * @param serverHandshake
         */
        @Override
        public void onOpen(ServerHandshake serverHandshake) {
          log.info("WsClientOfLocal -> onOpen -> 客户端建立连接");
          isConnect = 1;
        }


        /**
         * 收到服务端消息调用
         * @param s
         */
        @Override
        public void onMessage(String s) {
          log.info("WsClientOfLocal -> onMessage -> 收到服务端消息：{}", s);
          // 如果对接多个WebSocket服务，且服务消息处理方案一样，
          // 则可以在接收到消息的第一时间为该消息添加区分类型，
          // 比如IP地址：200，201，202
          //s = addTypeOfMsg(s, type);
          if (StringUtils.isNotBlank(s)) {
            // 统一处理消息
            messageService.handleMessage(s);
          }
        }

        /**
         * 断开连接调用
         * @param i
         * @param s
         * @param b
         */
        @Override
        public void onClose(int i, String s, boolean b) {
          log.info("WsClientOfLocal -> onClose -> 客户端关闭连接，i：{}，b：{}，s：{}", i, b, s);
          isConnect = 0;
        }

        /**
         * 连接报错调用
         * @param e
         */
        @Override
        public void onError(Exception e) {
          log.error("WsClientOfLocal -> onError -> 客户端连接异常，异常信息：{}", e.getMessage());
          if (null != wsClient) {
            wsClient.close();
          }
          isConnect = 0;
        }
      };
      return client;
    } catch (URISyntaxException e) {
      e.printStackTrace();
    }
    return null;
  }

  @Override
  public WebSocketClient connect(String wsUri, Map<String, String> httpHeaders) {
    WebSocketClient oldWsClient = this.getWsClient();
    if (null != oldWsClient) {
      log.info("WsClientOfLocal -> 已存在连接，oldWsClient：{}-{}",
          oldWsClient.getReadyState(), oldWsClient.getReadyState().ordinal());
      if (1 == oldWsClient.getReadyState().ordinal()) {
        log.info("WsClientOfLocal -> 使用存在且已打开的连接");
        return oldWsClient;
      } else {
        log.info("WsClientOfLocal -> 注销存在且未打开的连接，并重新获取新的连接");
        oldWsClient.close();
      }
    }
    WebSocketClient newWsClient = createWebSocketClient(wsUri, httpHeaders);
    // 如果是 "wss" 协议，则进行证书认证，认证方法在父类中
    if (wsUri.startsWith("wss")) {
      //createWebSocketClient(newWsClient);
    }
    if (null == newWsClient) {
      // 自定义异常
      throw new RuntimeException("WsClientOfLocal -> 创建失败！");
    }
    newWsClient.connect();
    // 设置连接状态为正在连接
    isConnect = 2;
    // 连接状态不再是0请求中，判断建立结果是不是1已建立
    long startTime = System.currentTimeMillis();
    while (1 != newWsClient.getReadyState().ordinal()) {
      // 避免网络波动，设置持续等待连接时间
      long endTime = System.currentTimeMillis();
      long waitTime = (endTime - startTime) / 1000;
      if (5L < waitTime) {
        log.info("WsClientOfLocal -> 建立连接异常，请稍后再试");
        break;
      }
    }
    if (1 == newWsClient.getReadyState().ordinal()) {
      this.setWsClient(newWsClient);
      newWsClient.send("WsClientOfLocal -> 客户端连接成功！");
      log.info("WsClientOfLocal -> 客户端连接成功！");
      return newWsClient;
    }
    return null;
  }

}
