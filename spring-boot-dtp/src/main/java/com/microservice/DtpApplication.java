package com.microservice;

import cn.hippo4j.core.enable.EnableDynamicThreadPool;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableDynamicThreadPool
public class DtpApplication {

    public static void main(String[] args) {
        SpringApplication.run(DtpApplication.class, args);
    }
}
