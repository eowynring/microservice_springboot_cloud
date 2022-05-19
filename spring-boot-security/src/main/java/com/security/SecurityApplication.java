package com.security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author guofei
 * @date 2022/5/16 10:36 PM
 */
@SpringBootApplication
public class SecurityApplication {

  public static void main(String[] args) {
    SpringApplication.run(SecurityApplication.class,args);
  }

  /**
   * 关于持久化的测试SQL
   * CREATE TABLE persistent_logins (
   *     username VARCHAR (64) NOT NULL,
   *     series VARCHAR (64) PRIMARY KEY,
   *     token VARCHAR (64) NOT NULL,
   *     last_used TIMESTAMP NOT NULL
   * )
   */

}
