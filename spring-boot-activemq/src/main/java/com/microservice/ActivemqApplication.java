package com.microservice;

import com.microservice.producer.TestProducer;
import javax.annotation.Resource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author guofei
 * @date 2022/3/30 5:53 PM
 */
@SpringBootApplication
public class ActivemqApplication {


  public static void main(String[] args) {
    SpringApplication.run(ActivemqApplication.class, args);
  }


}
