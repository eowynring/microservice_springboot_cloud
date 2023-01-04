package com.boot;

import com.boot.thread.SimpleThreadFactory;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.scheduling.concurrent.CustomizableThreadFactory;

import java.util.concurrent.*;

@Slf4j
public class ThreadPoolTest {

    @Test
    public void testThreadFactory(){
        ExecutorService executorService = Executors.newFixedThreadPool(2, new SimpleThreadFactory());
        for (int i = 0; i < 5; i++) {
            //executorService.submit()
        }
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException {

        log.info("线程name:{}", Thread.currentThread().getName());

        MyCallable myCallable = new MyCallable();

        FutureTask<String> stringFutureTask = new FutureTask<>(myCallable);
        Thread futureTask = new Thread(stringFutureTask, "FutureTask");
        futureTask.start();
        String s = stringFutureTask.get();
        log.info("返回的结果：{}，线程name:{}", s, Thread.currentThread().getName());
    }


    static class MyCallable implements Callable<String> {
        @Override
        public String call() throws Exception {
            log.info("线程开始运行：{}", Thread.currentThread().getName());
            Thread.sleep(2000);
            return "hello";
        }
    }


    LinkedBlockingQueue linkedBlockingQueue = new LinkedBlockingQueue<myRunnable>(1);


    ThreadFactory springThreadFactory = new CustomizableThreadFactory("springThread-pool-");

    ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(4,
            8,
            1,
            TimeUnit.SECONDS,
            linkedBlockingQueue, springThreadFactory);

    public void Test1(String[] args) throws ExecutionException, InterruptedException {
        Future future = new CompletableFuture();
        for (int i = 1; i <= 10; i++) {
            myRunnable myRunnable = new myRunnable();
            myRunnable.setCount(i);

            future = threadPoolExecutor.submit(myRunnable);

            for (Object o : linkedBlockingQueue) {
                log.info("myRunnable-------:{}", o.toString());
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        log.info("future:{}", future.get());
    }


    @Data
    static class myRunnable implements Runnable {
        Integer count;


        @Override
        public void run() {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            log.info("thread name:{}", Thread.currentThread().getName());
        }
    }


}
