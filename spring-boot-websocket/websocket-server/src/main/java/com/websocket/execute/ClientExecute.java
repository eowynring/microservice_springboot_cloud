package com.websocket.execute;

import com.websocket.client.event.ClientPingEvent;
import com.websocket.entity.ClientMessage;
import com.websocket.factory.WebSocketMessageFactory;
import com.websocket.manager.ClientWebSocketSessionManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author guofei
 * @date 2022/6/17 3:55 PM
 * 客户端处理器(统一业务处理)
 */
@Component
@Slf4j
public class ClientExecute {


  public void exePing(ClientPingEvent event) {
    ClientMessage clientMessage = event.getClientMessage();
    String data = clientMessage.getData();
    log.info("clientMessage-data->{}",data);
    // 发送会话 pong
    ClientMessage pong = WebSocketMessageFactory.pong(clientMessage.getMessageId());
    ClientWebSocketSessionManager.sendSessionMessage(event.getCode(),pong);
  }
}
