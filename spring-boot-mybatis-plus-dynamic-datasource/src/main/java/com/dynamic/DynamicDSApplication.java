package com.dynamic;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author guofei
 * @date 2022/4/19 1:59 PM
 */
@SpringBootApplication
@MapperScan("com.dynamic.mapper")
public class DynamicDSApplication {

  public static void main(String[] args) {
    SpringApplication.run(DynamicDSApplication.class,args);
  }
}
