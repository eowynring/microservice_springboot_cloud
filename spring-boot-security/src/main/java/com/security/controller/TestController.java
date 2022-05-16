package com.security.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author guofei
 * @date 2022/5/16 10:36 PM
 */
@RestController
public class TestController {

  @GetMapping("hello")
  public String hello() {
    return "hello spring security";
  }

}
