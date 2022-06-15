package com.websocket.handler;

import com.websocket.constant.IpadMessageActionConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

/**
 * @author guofei
 * @date 2022/6/15 11:02 AM
 * ipad 长连接会话处理器
 */
@Slf4j
public class IpadWebsocketHandler extends TextWebSocketHandler {


  /**
   * 建立会话
   * @param session
   * @throws Exception
   */
  @Override
  public void afterConnectionEstablished(WebSocketSession session) throws Exception {
    //super.afterConnectionEstablished(session);
    String code = getIpadUniqueCode();
    if (log.isDebugEnabled()){
      log.debug("准备建立会话，code=[{}]",code);
    }
    //directSendMessage(session, IpadMessageActionConstant.CLOSE_SESSION);
  }




  /**
   * 处理会话信息
   * @param session
   * @param message
   * @throws Exception
   */
  @Override
  protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
    super.handleTextMessage(session, message);
  }


  /**
   * 断开会话
   * @param session
   * @param status
   * @throws Exception
   */
  @Override
  public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
    super.afterConnectionClosed(session, status);
  }


  private String getIpadUniqueCode() {
    return null;
  }
}
