package com.websocket.client.event;


import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;

/**
 * @author guofei
 * @date 2022/6/17 3:37 PM
 * 客户端事件
 */
public class ClientEvent extends ApplicationEvent {

  private static final Object NULL = new Object();

  @Getter
  @Setter
  private String code;

  public ClientEvent(Object source) {
    super(source);
  }

  public ClientEvent() {
    this(NULL);
  }

  public static <T extends ClientEvent> T instance(Class<T> eventClazz, String code){
    try {
      T t = eventClazz.newInstance();
      t.setCode(code);
    } catch (InstantiationException e) {
    } catch (IllegalAccessException e) {
    }
    return null;
  }



}
