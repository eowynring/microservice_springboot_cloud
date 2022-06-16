package com.websocket.manager;

import com.alibaba.fastjson.JSON;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.ConcurrentWebSocketSessionDecorator;

/**
 * @author guofei
 * @date 2022/6/16 4:36 PM
 *
 * 会话管理器
 */

@Slf4j
public class ClientWebSocketSessionManager {

  private static final Map<String, List<ClientWebSocketSessionManager>> clientSessionMap =
      new ConcurrentHashMap<>(100);

  private final ConcurrentWebSocketSessionDecorator delegateSession;


  public ClientWebSocketSessionManager(
      ConcurrentWebSocketSessionDecorator delegateSession) {
    this.delegateSession = delegateSession;
  }

  /**
   * 保存会话
   *
   * @param code
   * @param session
   */
  public static void save(String code, WebSocketSession session) {
    ConcurrentWebSocketSessionDecorator sessionDecorator = new ConcurrentWebSocketSessionDecorator(
        session, 3000, 10000000);
    synchronized (code.intern()) {
      clientSessionMap.computeIfAbsent(code,
          k -> new CopyOnWriteArrayList<>()).add(new ClientWebSocketSessionManager(sessionDecorator));
    }
  }

  /**
   * 移除会话
   *
   * @param code
   * @param session
   */
  public static void remove(String code, WebSocketSession session) {
    listSession(code).removeIf(s->s.delegateSession.getDelegate().equals(session));
  }

  /**
   * 会话是否存在
   * @param code
   * @return
   */
  public static boolean isExists(String code){
    return !listSession(code).isEmpty();
  }

  /**
   * 获取全部会话
   * @param code
   * @return
   */
  public static List<ClientWebSocketSessionManager> listSession(String code) {
    return clientSessionMap.get(code) == null ? Collections.EMPTY_LIST : clientSessionMap.get(code);
  }

  /**
   * 发送消息
   * @param code
   * @param message
   */
  public static void sendSessionMessage(String code, Object message) {
    if (log.isDebugEnabled()) {
      log.debug("code=[{}], 消息内容=[{}]", code, message);
    }
    listSession(code).forEach(e -> {
      ConcurrentWebSocketSessionDecorator delegateSession = e.delegateSession;
      try {
        delegateSession.sendMessage(new TextMessage(JSON.toJSONString(message)));
      } catch (IOException ex) {
        log.warn("发送失败，code=[{}],sessionId=[{}],消息=[{}],原因=[{}]", code, delegateSession.getId(),
            message, ex.getMessage(), e);
      }
    });
  }


}
