package com.wss;

import java.net.URI;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import lombok.extern.slf4j.Slf4j;
import org.java_websocket.client.WebSocketClient;
import org.springframework.stereotype.Component;

/**
 * @author guofei
 * @date 2022/6/22 6:27 PM
 */
@Slf4j
public abstract class SSLWebSocketClient extends WebSocketClient {


  public SSLWebSocketClient(URI serverUri) {
    super(serverUri);
    if(serverUri.toString().contains("wss://")){
      trustAllHosts(this);
    }
  }

  private void trustAllHosts(SSLWebSocketClient sslWebSocketClient) {
    TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {
      @Override
      public java.security.cert.X509Certificate[] getAcceptedIssuers() {
        return new java.security.cert.X509Certificate[]{};
      }
      @Override
      public void checkClientTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {
      }
      @Override
      public void checkServerTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {
      }
    }};
    try {
      SSLContext ssl = SSLContext.getInstance("TLS");
      ssl.init(null, trustAllCerts, new java.security.SecureRandom());
    } catch (Exception e) {
      e.printStackTrace();
      log.error("trustAllHosts error",e);
    }
  }
}
