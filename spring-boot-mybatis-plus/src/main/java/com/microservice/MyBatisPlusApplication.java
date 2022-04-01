package com.microservice;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author guofei
 * @date 2022/3/31 6:39 PM
 */
@SpringBootApplication
@MapperScan("com.microservice.mapper")
public class MyBatisPlusApplication {

  public static void main(String[] args) {
    SpringApplication.run(MyBatisPlusApplication.class, args);
  }

}
