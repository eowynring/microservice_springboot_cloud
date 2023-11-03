package com.boot.retry;

public class Retry02 {

    /**
     * 重试机制递归实现
     * @param args
     */
    public static void main(String[] args) {
        int retryCount = 3;
        retry(retryCount);
    }

    private static void retry(int retryCount) {
        if (retryCount <= 0) {
            return;
        }
        try {
            // do something
            System.out.println("fetch data");
            //int i = 10 / 0;
        } catch (Exception e) {
            retry(retryCount-1);
        }
    }
}
