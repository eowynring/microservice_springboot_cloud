package com.websocket.client.event;

import com.websocket.entity.ClientMessage;

/**
 * @author guofei
 * @date 2022/6/17 3:51 PM
 * 客户端ping事件
 */
public class ClientPingEvent extends ClientMessageEvent {

  public ClientPingEvent(String code, ClientMessage clientMessage) {
    super(code, clientMessage);
  }
}
