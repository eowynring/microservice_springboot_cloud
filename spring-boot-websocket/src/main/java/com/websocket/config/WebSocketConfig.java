package com.websocket.config;

import com.websocket.handler.IpadWebsocketHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

/**
 * @author guofei
 * @date 2022/6/15 11:00 AM
 * websocket 配置
 */
@Configuration
@EnableWebSocket
@Slf4j
public class WebSocketConfig implements WebSocketConfigurer {

  @Override
  public void registerWebSocketHandlers(WebSocketHandlerRegistry webSocketHandlerRegistry) {
    webSocketHandlerRegistry.addHandler(ipadWebsocketHandler(),"/ipad/**").setAllowedOrigins("*");
  }

  @Bean
  IpadWebsocketHandler ipadWebsocketHandler(){
    return new IpadWebsocketHandler();
  }

}
