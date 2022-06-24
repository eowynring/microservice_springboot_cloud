package com.websocket.manager;

import cn.hutool.core.collection.ListUtil;
import com.alibaba.fastjson.JSON;
import com.websocket.handler.ClientWebsocketHandler;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Predicate;
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

  private static final Map<String, List<String>> ipInstance= new ConcurrentHashMap<>(100);

  private final ConcurrentWebSocketSessionDecorator delegateSession;

  /**
   *
   * 静态变量，用来记录当前在线连接数
   */
  private static volatile int onlineCount = 0;


  public ClientWebSocketSessionManager(
      ConcurrentWebSocketSessionDecorator delegateSession) {
    this.delegateSession = delegateSession;
  }

  /**
   * 保存会话
   *
   * @param clientId
   * @param session
   */
  public static void save(String hostAddress,String clientId, WebSocketSession session) {
    ConcurrentWebSocketSessionDecorator sessionDecorator = new ConcurrentWebSocketSessionDecorator(
        session, 3000, 10000000);
    synchronized (hostAddress.intern()) {
      clientSessionMap.computeIfAbsent(hostAddress,
          k -> new CopyOnWriteArrayList<>()).add(new ClientWebSocketSessionManager(sessionDecorator));
    }
    addOnlineCount();
    log.info("有新连接加入！当前在线连接数为" + getOnlineCount());
    ipInstance.computeIfAbsent(clientId,
        k -> new CopyOnWriteArrayList<>()).add(hostAddress);
    log.info("当前code=[{}],当前实例=[{}]",clientId,ipInstance);
  }

  /**
   * 移除会话
   *
   * @param clientId
   * @param session
   */
  public static void remove(String hostAddress, String clientId, WebSocketSession session) {
    listSession(hostAddress).removeIf(s->s.delegateSession.getDelegate().equals(session));
    ipInstance.remove(hostAddress);
    List<String> list = ipInstance.get(clientId);
    Predicate<String> predicate = (s) -> s.equals(hostAddress);
    list.removeIf(predicate);
    log.info("当前code=[{}],当前实例=[{}]",clientId, ipInstance);
    subOnlineCount();
    log.info("有新连接断开！当前在线连接为" + getOnlineCount());
  }

  /**
   * 会话是否存在
   * @param clientId
   * @return
   */
  public static boolean isExists(String clientId){
    return !listSession(clientId).isEmpty();
  }

  /**
   * 获取全部会话
   * @param hostAddress
   * @return
   */
  public static List<ClientWebSocketSessionManager> listSession(String hostAddress) {
    return clientSessionMap.get(hostAddress) == null ? Collections.EMPTY_LIST : clientSessionMap.get(hostAddress);
  }

  public static List<ClientWebSocketSessionManager> getSessionList(String hostAddress){
    return clientSessionMap.get(hostAddress);
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



  public static synchronized int getOnlineCount() {
    return onlineCount;
  }

  public static synchronized void addOnlineCount() {
    ClientWebSocketSessionManager.onlineCount++;
  }

  public static synchronized void subOnlineCount() {
    ClientWebSocketSessionManager.onlineCount--;
  }

  public static void main(String[] args) {
    Map<String, List<String>> map = new HashMap<>();
    map.put("key1", Arrays.asList("1","2"));
    map.put("key2", Arrays.asList("3","4"));
    map.put("key3", Arrays.asList("5","6"));
    map.forEach((k, v) -> System.out.println("key:value = " + k + ":" + v));
  }

}
