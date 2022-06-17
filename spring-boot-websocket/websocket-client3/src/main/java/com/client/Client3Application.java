package com.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * @author guofei
 * @date 2022/6/17 2:49 PM
 */

@SpringBootApplication
@Async
@EnableScheduling
public class Client3Application {


  public static void main(String[] args) {
    SpringApplication.run(Client3Application.class, args);
  }

}
