package com.server.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author guofei
 * @date 2022/5/23 5:00 PM
 */
@RestController
public class TestController {

  @Value("${message}")
  private String message;

  @GetMapping("message")
  public String getMessage() {
    return this.message;
  }

}
