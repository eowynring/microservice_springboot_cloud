package com.websocket.factory;

import com.websocket.constant.ClientMessageActionConstant;
import com.websocket.entity.ClientMessage;

/**
 * @author guofei¡
 * @date 2022/6/15 11:45 AM
 * websocket 消息工厂类
 */
public class WebSocketMessageFactory {

  /**
   * 无效session
   * @return
   */
  public static ClientMessage invalidSession() {
    return ClientMessage.builder().action(ClientMessageActionConstant.CLOSE_SESSION).build();
  }

  public static ClientMessage updateSetting() {
    return ClientMessage.builder().action(ClientMessageActionConstant.UPDATE_SETTING).build();
  }

  public static ClientMessage active(){
    return ClientMessage.builder().action(ClientMessageActionConstant.ACTIVE).build();
  }

  public static ClientMessage errorMessage(String payload) {
    return ClientMessage.builder()
        .action(ClientMessageActionConstant.ERROR_MESSAGE)
        .data(payload)
        .build();
  }
}
