package com.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author guofei
 * @date 2022/5/20 5:31 PM
 */
@EnableDiscoveryClient
@SpringBootApplication
public class EurekaClientApplication {
  public static void main(String[] args) {
    SpringApplication.run(EurekaClientApplication.class, args);
  }
}
