package com.microservice.redisson.handle;

import cn.hutool.extra.spring.SpringUtil;
import com.microservice.redisson.enums.RedisDelayQueueEnum;
import com.microservice.redisson.util.RedisDelayQueueUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component
public class RedisDelayQueueRunner implements CommandLineRunner {

    @Autowired
    private RedisDelayQueueUtil redisDelayQueueUtil;
    @Autowired
    private ThreadPoolTaskExecutor threadPool;
    ThreadPoolExecutor executorService = new ThreadPoolExecutor(10, 50, 30, TimeUnit.SECONDS,
            new LinkedBlockingQueue<Runnable>(1000), Executors.defaultThreadFactory());

    public RedisDelayQueueRunner(RedisDelayQueueUtil redisDelayQueueUtil, ThreadPoolTaskExecutor threadPool) {
        this.redisDelayQueueUtil = redisDelayQueueUtil;
        this.threadPool = threadPool;
    }

    @Override
    public void run(String... args) {
        threadPool.execute(() -> {
            while (true){
                try {
                    RedisDelayQueueEnum[] queueEnums = RedisDelayQueueEnum.values();
                    for (RedisDelayQueueEnum queueEnum : queueEnums) {
                        Object value = redisDelayQueueUtil.getDelayQueue(queueEnum.getCode());
                        if (value != null) {
                            RedisDelayQueueHandle redisDelayQueueHandle = SpringUtil.getBean(queueEnum.getBeanId());
                            redisDelayQueueHandle.execute(value);
                        }
                    }
                } catch (InterruptedException e) {
                    log.error("(Redis延迟队列异常中断) {}", e.getMessage());
                }
            }
        });
        log.info("(Redis延迟队列启动成功)");
    }
}
