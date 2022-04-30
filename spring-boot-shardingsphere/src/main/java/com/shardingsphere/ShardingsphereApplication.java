package com.shardingsphere;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @author guofei
 * @date 2022/4/29 5:37 PM
 */
@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class})
@MapperScan("{com.shardingsphere.mapper}")
public class ShardingsphereApplication {

  public static void main(String[] args) {
    SpringApplication.run(ShardingsphereApplication.class,args);
  }

}
