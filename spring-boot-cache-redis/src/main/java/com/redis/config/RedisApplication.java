package com.redis.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * @author guofei
 * @date 2022/6/9 3:11 PM
 */
@SpringBootApplication
@EnableCaching
public class RedisApplication {

  public static void main(String[] args) {
    SpringApplication.run(RedisApplication.class,args);
  }

}
