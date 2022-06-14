package com.microservice.controller;

import com.microservice.producer.TestProducer;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author guofei
 * @date 2022/3/30 6:23 PM
 */
@RestController
public class TestQueueController {

  @Resource
  private TestProducer testProducer;

  @PostMapping("/test")
  public String test(){
    testProducer.sendTestMessage("test1",0);
    return "ok";
  }

}
