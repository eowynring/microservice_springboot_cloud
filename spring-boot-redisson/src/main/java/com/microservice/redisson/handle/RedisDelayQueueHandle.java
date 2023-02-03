package com.microservice.redisson.handle;

/**
 * 延迟队列执行器
 * @param <T>
 */
public interface RedisDelayQueueHandle<T> {

    void execute(T t);
}
