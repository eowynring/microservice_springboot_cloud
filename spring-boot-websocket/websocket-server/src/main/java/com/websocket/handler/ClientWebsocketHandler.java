package com.websocket.handler;

import com.alibaba.fastjson.JSON;
import com.websocket.constant.ClientMessageActionConstant;
import com.websocket.entity.ClientMessage;
import com.websocket.factory.WebSocketMessageFactory;
import com.websocket.manager.ClientWebSocketSessionManager;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

/**
 * @author guofei
 * @date 2022/6/15 11:02 AM
 * 长连接会话处理器
 */
@Slf4j
public class ClientWebsocketHandler extends TextWebSocketHandler {


  /**
   * 建立会话
   * @param session
   * @throws Exception
   */
  @Override
  public void afterConnectionEstablished(WebSocketSession session) throws Exception {
    String code = getClientUniqueCode(session);
    if (log.isDebugEnabled()){
      log.debug("准备建立会话，code=[{}]",code);
    }
    directSendMessage(session, WebSocketMessageFactory.active());
    ClientWebSocketSessionManager.save(code,session);
    if (log.isDebugEnabled()){
      log.debug("建立会话成功，code=[{}], sessionId=[{}]",code,session.getId());
    }
  }



  /**
   * 处理会话信息
   * @param session
   * @param message
   * @throws Exception
   */
  @Override
  protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
    String clientUniqueCode = getClientUniqueCode(session);
    String id = session.getId();
    log.info("id->{}",id);
    String payload = message.getPayload();
    if (log.isDebugEnabled()){
      log.debug("收到消息，code=[{}],payload={}", clientUniqueCode, payload);
    }
    ClientMessage clientMessage;
    try {
      clientMessage = JSON.parseObject(payload, ClientMessage.class);
    } catch (Exception e) {
      directSendMessage(session, WebSocketMessageFactory.errorMessage(payload));
      log.warn("消息格式不正确，消息内容={}",payload,e);
      return;
    }
    String action = clientMessage.getAction();
    switch (action){
      case ClientMessageActionConstant.PING:
        log.info("pong");
        break;
      case ClientMessageActionConstant.ACK:
        log.info("ack");
    }

  }


  /**
   * 断开会话
   * @param session
   * @param status
   * @throws Exception
   */
  @Override
  public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
    String clientUniqueCode = getClientUniqueCode(session);
    log.warn("断开会话，code=[{}]",clientUniqueCode);
    ClientWebSocketSessionManager.remove(clientUniqueCode,session);
  }


  private String getClientUniqueCode(WebSocketSession session) {
    if (session == null || session.getUri() == null){
      return "";
    }
    String path = session.getUri().getPath();
    String[] split = path.split("/");
    if (split.length > 1){
      return split[split.length-1];
    }
    return "";
  }

  private void directSendMessage(WebSocketSession session, ClientMessage message) {
    try {
      session.sendMessage(new TextMessage(JSON.toJSONString(message)));
    } catch (IOException e) {
      log.error("发送消息失败, 消息={}, 错误={}", message, e.getMessage());
    }
  }
}
