
package com.client.config;


import com.client.constant.WebSocketConfigConstant;
import java.net.Socket;
import java.net.URI;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLEngine;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509ExtendedTrustManager;
import lombok.extern.slf4j.Slf4j;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

/**
 * @author guofei
 * @date 2022/6/16 4:03 PM
 */
@Slf4j
public class SocketClient extends WebSocketClient {


  public SocketClient(URI serverUri) {
    super(serverUri);
    if(serverUri.toString().contains("wss://")){
      trustAllHosts(this);
    }
  }

  @Override
  public void onOpen(ServerHandshake serverHandshake) {
    WebSocketConfigConstant.isConnect=1;
    log.info("------ MyWebSocket onOpen ------");
  }

  @Override
  public void onMessage(String s) {
    WebSocketConfigConstant.isConnect=2;
    log.info("-------- 接收到服务端数据：{} ", s);
  }

  @Override
  public void onClose(int i, String s, boolean b) {
    WebSocketConfigConstant.isConnect=0;
    log.info("------ MyWebSocket onClose ------{}", s);
    //断开时尝试重连
    /*int a = 10;
    do {
      a--;
      //this.reconnect();
      this.connect();
      log.info("开始重连，第[{}]次重连",a);
    }
    while(a>0);*/
  }

  @Override
  public void onError(Exception e) {
    WebSocketConfigConstant.isConnect=0;
    log.info("------ MyWebSocket onError ------{}", e);
  }

  void trustAllHosts(WebSocketClient webSocketClient) {
    TrustManager[] trustAllCerts = new TrustManager[]{new X509ExtendedTrustManager() {
      @Override
      public void checkClientTrusted(X509Certificate[] x509Certificates, String s, Socket socket)
          throws CertificateException {

      }

      @Override
      public void checkServerTrusted(X509Certificate[] x509Certificates, String s, Socket socket)
          throws CertificateException {

      }

      @Override
      public void checkClientTrusted(X509Certificate[] x509Certificates, String s,
          SSLEngine sslEngine) throws CertificateException {

      }

      @Override
      public void checkServerTrusted(X509Certificate[] x509Certificates, String s,
          SSLEngine sslEngine) throws CertificateException {

      }

      @Override
      public java.security.cert.X509Certificate[] getAcceptedIssuers() {
//                return new java.security.cert.X509Certificate[]{};
//                System.out.println("getAcceptedIssuers");
        return null;
      }
      @Override
      public void checkClientTrusted(X509Certificate[] arg0, String arg1)
          throws CertificateException {
        System.out.println("checkClientTrusted");
      }
      @Override
      public void checkServerTrusted(X509Certificate[] arg0, String arg1)
          throws CertificateException {
        System.out.println("checkServerTrusted");
      }
    }};

    try {
      SSLContext ssl = SSLContext.getInstance("SSL");
      ssl.init(null, trustAllCerts, new java.security.SecureRandom());
      SSLSocketFactory socketFactory = ssl.getSocketFactory();
      webSocketClient.setSocketFactory(socketFactory);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
