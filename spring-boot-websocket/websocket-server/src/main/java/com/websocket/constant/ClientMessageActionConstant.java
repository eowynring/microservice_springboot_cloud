package com.websocket.constant;

/**
 * @author guofei
 * @date 2022/6/15 11:17 AM
 * ipad 消息行为常量
 */
public  interface ClientMessageActionConstant {

  /**
   * 关闭会话
   */
  String CLOSE_SESSION = "closeSession";


  String PING = "ping";


  String PONG = "pong";


  String ACK = "ack";


  String  ERROR_MESSAGE = "errorMessage";


  String UPDATE_SETTING = "needUpdateSetting";

  String ACTIVE = "active...";

}
