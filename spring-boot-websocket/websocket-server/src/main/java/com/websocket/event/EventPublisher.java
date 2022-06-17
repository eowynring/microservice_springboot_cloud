package com.websocket.event;

import java.util.Objects;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Component;

/**
 * @author guofei
 * @date 2022/6/17 3:48 PM
 */
@Slf4j
@Component
public class EventPublisher implements ApplicationEventPublisherAware {

  private static EventPublisher publisher;

  /**
   * 委托spring对象进行事件发布
   */
  private ApplicationEventPublisher delegate;

  @Override
  public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
    this.delegate = applicationEventPublisher;
    // 当设置委托事件发布对象后, 静态属性对象才能正常工作, 所以要在此初始化对象值
    publisher = this;
  }

  /**
   * 发布事件
   *
   * @param event 事件对象
   */
  public void publishEvent(ApplicationEvent event) {
    try {
      delegate.publishEvent(event);
    } catch (Exception e) {
      // 发布事件不能影响调用方, 异常应该在监听器中处理
      log.error("[事件发布]: 发布失败, 事件对象=" + event, e);
    }
  }

  /**
   * 发布事件
   */
  public static void publish(ApplicationEvent event) {
    Objects.requireNonNull(publisher, "事件发布对象未初始化完成, 无法发布事件").publishEvent(event);
  }
}
