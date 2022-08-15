package com.validate.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;

/**
 * @author guofei
 * @date 2022/8/4 2:26 PM
 */
@Configuration
public class MyMvcConfig {

  @Bean
  public LocaleResolver localeResolver(){
    return new MyLocaleResolver();
  }

}
