package com.logback;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author guofei
 * @date 2022/4/20 5:17 PM
 */
@SpringBootApplication
@Slf4j
public class LogbackApplication {

  public static void main(String[] args) {
    ConfigurableApplicationContext context = SpringApplication.run(LogbackApplication.class, args);
    int length = context.getBeanDefinitionNames().length;
    log.trace("Spring boot启动初始化了 {} 个 Bean", length);
    log.debug("Spring boot启动初始化了 {} 个 Bean", length);
    log.info("Spring boot启动初始化了 {} 个 Bean", length);
    log.warn("Spring boot启动初始化了 {} 个 Bean", length);
    log.error("Spring boot启动初始化了 {} 个 Bean", length);
  }

}
