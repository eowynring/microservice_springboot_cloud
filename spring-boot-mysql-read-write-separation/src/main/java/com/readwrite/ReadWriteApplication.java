package com.readwrite;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author guofei
 * @date 2022/4/18 11:44 AM
 */
@SpringBootApplication
@MapperScan(basePackages = {"com.readwrite.mapper"})
public class ReadWriteApplication {

  public static void main(String[] args) {
    SpringApplication.run(ReadWriteApplication.class,args);
  }
}
