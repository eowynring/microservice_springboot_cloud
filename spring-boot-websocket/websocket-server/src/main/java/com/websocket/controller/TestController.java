package com.websocket.controller;

import com.websocket.entity.ClientMessage;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.websocket.manager.ClientWebSocketSessionManager.sendSessionMessage;

/**
 * @author: GuoFei
 * @date: 2022-06-16 21:27
 */
@RestController
public class TestController {



    @GetMapping("/test")
    public String test(){
        ClientMessage clientMessage = new ClientMessage();
        clientMessage.setAction("test");
        clientMessage.setData("123");
        //clientWebsocketHandler.directSendMessage(session, clientMessage);
        sendSessionMessage("client1",clientMessage);
        return "OK";
    }
}
