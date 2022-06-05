package com.microservice;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author guofei
 * @date 2022/3/30 5:53 PM
 */
@SpringBootApplication
@EnableScheduling
public class ActivemqApplication {


  public static void main(String[] args) {
    SpringApplication.run(ActivemqApplication.class, args);
  }


}
