package com.websocket.client.event;

import com.websocket.entity.ClientMessage;
import lombok.EqualsAndHashCode;
import lombok.Getter;

/**
 * @author guofei
 * @date 2022/6/17 3:41 PM
 * 客户端消息事件
 */
@EqualsAndHashCode(callSuper = true)
public class ClientMessageEvent extends ClientEvent{

  @Getter
  private final ClientMessage clientMessage;

  public ClientMessageEvent(String code,ClientMessage clientMessage) {
    this.clientMessage = clientMessage;
    setCode(code);
  }
}
