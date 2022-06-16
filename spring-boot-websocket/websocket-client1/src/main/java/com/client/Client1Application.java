package com.client;


import com.client.config.SocketClient;
import java.net.URI;
import java.net.URISyntaxException;
import lombok.extern.slf4j.Slf4j;
import org.java_websocket.client.WebSocketClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * @author guofei
 * @date 2022/6/16 4:09 PM
 */
@SpringBootApplication
@Slf4j
public class Client1Application {

  public static void main(String[] args) {
    SpringApplication.run(Client1Application.class, args);
  }

  @Bean
  public WebSocketClient MyWebSocketClient() {
    try {
      SocketClient webSocketClient = new SocketClient(
          new URI("ws://127.0.0.1:8888/websocket/client1"));
      webSocketClient.connect();
      return webSocketClient;
    } catch (URISyntaxException e) {
      log.error("URISyntaxException,errorMsg->{}", e.getMessage(), e);
    }
    return null;
  }
}
