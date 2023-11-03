package com.boot.retry;

public class Retry01 {

    /**
     * 重试机制 for 循环实现
     *
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        int retryCount = 3;
        for (int i = 0; i < retryCount; i++) {
            System.out.println("i = " + i);
            try {
                // do something
                System.out.println("fetch data success");
                i = 10 / 0;
                break;
            } catch (Exception e) {
                Thread.sleep(1000);
            }
        }
    }
}
