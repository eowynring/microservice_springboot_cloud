package com.wss;

import java.net.Socket;
import java.net.URI;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLEngine;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509ExtendedTrustManager;
import javax.net.ssl.X509TrustManager;
import lombok.extern.slf4j.Slf4j;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author guofei
 * @date 2022/6/22 4:44 PM
 */
@SpringBootApplication
@Slf4j
public class Client4WssApplication {

  public static void main(String[] args) throws Exception {
    SpringApplication.run(Client4WssApplication.class, args);
    SSLWebSocketClient sslWebSocketClient = new SSLWebSocketClient(
        new URI("wss://test-runway.mushiapp.com/smart/webLiveStates")) {
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
