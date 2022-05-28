package com.consumer.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author guofei
 * @date 2022/5/28 10:46 PM
 */
@Configuration
public class ConsumerConfigure {

  @Bean
  public RestTemplate restTemplate() {
    return new RestTemplate();
  }
}
