package client.controller;

import javax.annotation.Resource;
import org.java_websocket.client.WebSocketClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author guofei
 * @date 2022/6/16 4:13 PM
 */
@RestController
public class TestController {


  @Resource
  private WebSocketClient webSocketClient;

  @GetMapping("/subscribe")
  public String subscribe(){
    webSocketClient.connect();
    return "连接成功";
  }

  @GetMapping("/send/{data}")
  private String send(@PathVariable("data") String data){
    webSocketClient.send("hello,im client....,this is my data:"+data);
    return "发送成功";
  }

}
