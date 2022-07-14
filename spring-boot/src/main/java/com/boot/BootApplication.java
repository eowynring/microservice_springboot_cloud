package com.boot;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author guofei
 * @date 2022/5/7 1:48 PM
 */
@SpringBootApplication
@MapperScan("com.boot.mapper")
@Slf4j
public class BootApplication {

  public static void main(String[] args) {
    SpringApplication.run(BootApplication.class,args);
    int i = Runtime.getRuntime().availableProcessors();
    //Runtime.getRuntime()
    log.info("availableProcessors=[{}]",i);
    }

}
