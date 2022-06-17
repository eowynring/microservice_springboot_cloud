package com.client.controller;

import com.alibaba.fastjson.JSON;
import com.client.handler.WsClientOfLocal;
import com.client.handler.entity.ClientMessage;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author guofei
 * @date 2022/6/17 4:31 PM
 */
@RestController
public class TestController {

  @Resource
  private WsClientOfLocal wsClientOfLocal;
  @GetMapping("/send")
  public String testSend(){
    ClientMessage clientMessage =  new ClientMessage();
    clientMessage.setAction("ping");
    clientMessage.setData("123");

    wsClientOfLocal.sendMessage(JSON.toJSONString(clientMessage));
    return "OK";
  }
}
