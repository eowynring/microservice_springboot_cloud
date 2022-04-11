package com.aop.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author guofei
 * @date 2022/4/11 3:30 PM
 */
@RestController
@RequestMapping("/aop")
public class AopTestController {

  @GetMapping("/{name}")
  public String testAop(@PathVariable String name) {
    // int i = 10/0;
    return "Hello " + name;
  }
}
