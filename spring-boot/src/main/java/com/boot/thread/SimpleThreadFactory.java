package com.boot.thread;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
public class SimpleThreadFactory implements ThreadFactory {

    @Override
    public Thread newThread(Runnable r) {
        String threadName = "simpleThread-"+ Thread.currentThread().getName();
        log.info("创建一个线程，name:{}",threadName);
        Thread thread = new Thread(r, threadName);
        thread.setDaemon(true);
        return thread;
    }
}
