package client;


import client.config.SocketClient;
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
public class Client2Application {

  public static void main(String[] args) {
    SpringApplication.run(Client2Application.class, args);
  }

  @Bean
  public WebSocketClient MyWebSocketClient() {
    try {
      SocketClient webSocketClient = new SocketClient(
          new URI("wss://test-runway.mushiapp.com/boss/privateKey/adc0fef1be4a46a7a4efb9a537ca6674"));
      webSocketClient.connect();
      return webSocketClient;
    } catch (URISyntaxException e) {
      log.error("URISyntaxException,errorMsg->{}", e.getMessage(), e);
    }
    return null;
  }
}
