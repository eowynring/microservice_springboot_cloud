package com.wss;

import java.net.URI;
import java.net.URISyntaxException;
import org.java_websocket.handshake.ServerHandshake;

/**
 * @author guofei
 * @date 2022/6/22 6:34 PM
 */
public class Test {

  public static void main(String[] args) throws URISyntaxException {
    SSLWebSocketClient sslWebSocketClient = new SSLWebSocketClient(
        new URI("wss://172.24.5.114:8888/websocket/123")) {
      @Override
      public void onClose(int arg0, String arg1, boolean arg2) {
        System.out.println("onClose");
      }

      @Override
      public void onError(Exception arg0) {
        System.out.println("onError");
        arg0.printStackTrace();
      }

      @Override
      public void onMessage(String arg0) {
        System.out.println("onMessage");
        System.out.println(arg0);
        this.send(arg0);
      }

      @Override
      public void onOpen(ServerHandshake arg0) {
        System.out.println("onOpen");
      }
    };
    sslWebSocketClient.connect();
  }

}
