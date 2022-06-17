package com.websocket.event;

import com.websocket.client.event.ClientPingEvent;
import com.websocket.execute.ClientExecute;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * @author guofei
 * @date 2022/6/17 3:53 PM
 * 客户端事件监听
 */
@Slf4j
@Component
public class ClientEventListener {

  @Resource
  private ClientExecute clientExecute;


  @EventListener
  @Async
  public void listenerPingEvent(ClientPingEvent event){
    clientExecute.exePing(event);
  }

}
