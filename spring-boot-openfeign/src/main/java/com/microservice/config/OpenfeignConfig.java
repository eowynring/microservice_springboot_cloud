package com.microservice.config;


import feign.Logger;
import feign.Logger.Level;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author guofei
 * @date 2022/4/6 5:38 PM
 */
@Configuration
public class OpenfeignConfig {


  /**
   * feignLogLevel 日志级别定义
   * @return
   */
  @Bean
  Level feignLogLevel(){
    return Level.FULL;
  }

}
