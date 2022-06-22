package com.websocket;

import com.websocket.client.SocketClient;
import java.net.URI;
import java.net.URISyntaxException;
import lombok.extern.slf4j.Slf4j;
import org.java_websocket.client.WebSocketClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * @author guofei
 * @date 2022/6/15 7:09 PM
 */
@SpringBootApplication
@Slf4j
public class WebSocketServerApplication {

  public static void main(String[] args) {
    SpringApplication.run(WebSocketServerApplication.class,args);
  }

  @Value("${isClient}")
  private Integer isClient;

/*
  @Bean
  public WebSocketClient MyWebSocketClient() {
    try {
      SocketClient webSocketClient = new SocketClient(
          new URI("ws://127.0.0.1:8888/websocket/client1"));
      if (isClient==1){
        webSocketClient.connect();
        log.info("client start up...");
      }else{
        log.info("server start up...");
      }
      return webSocketClient;
    } catch (URISyntaxException e) {
      log.error("URISyntaxException,errorMsg->{}", e.getMessage(), e);
    }
    return null;
  }
*/

}
