package com.microservice.redisson.util;

import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RBlockingDeque;
import org.redisson.api.RBlockingQueue;
import org.redisson.api.RDelayedQueue;
import org.redisson.api.RedissonClient;
import org.springframework.data.redis.core.RedisAccessor;
import org.springframework.stereotype.Component;
import org.springframework.test.annotation.Commit;

import javax.annotation.Resource;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component
public class RedisDelayQueueUtil {

    @Resource
    private RedissonClient redissonClient;

    /**
     * 添加进延迟队列
     * @param value 队列值
     * @param delay 延迟时间
     * @param timeUnit 时间单位
     * @param queueCode 队列键
     * @param <T>
     */
    public <T> void addDelayQueue(T value, long delay, TimeUnit timeUnit, String queueCode) {
        RBlockingQueue<Object> blockingQueue = redissonClient.getBlockingQueue(queueCode);
        RDelayedQueue<Object> delayedQueue = redissonClient.getDelayedQueue(blockingQueue);
        try {
            delayedQueue.offer(value, delay, timeUnit);
            log.info("(添加延时队列成功) 队列键：{}，队列值：{}，延迟时间：{},单位：{}", queueCode, value, delay, timeUnit);
        } catch (Exception e) {
            log.error("(添加延时队列失败) {}", e.getMessage());
            throw new RuntimeException("添加延迟队列失败");
        }
    }

    /**
     * 获取延迟队列
     *
     * @param queueCode
     * @param <T>
     * @return
     * @throws InterruptedException
     */
    public <T> T getDelayQueue(String queueCode) throws InterruptedException {
        RBlockingDeque<Map> blockingDeque = redissonClient.getBlockingDeque(queueCode);
        T value = (T) blockingDeque.take();
        return value;
    }


}
