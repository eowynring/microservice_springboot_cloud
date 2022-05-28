package com.provider.controlelr;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author guofei
 * @date 2022/5/28 10:43 PM
 */
@RestController
@RequestMapping("provide")
public class HelloController {

  @GetMapping("{message}")
  public String hello(@PathVariable String message) {
    return String.format("hello %s", message);
  }
}
