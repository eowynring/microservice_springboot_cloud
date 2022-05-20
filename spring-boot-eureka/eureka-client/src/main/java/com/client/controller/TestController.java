package com.client.controller;

import java.util.List;
import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author guofei
 * @date 2022/5/20 5:31 PM
 */
@RestController
public class TestController {

  private Logger log = LoggerFactory.getLogger(this.getClass());

  @Resource
  private DiscoveryClient client;

  @GetMapping("/info")
  public String info() {
    List<ServiceInstance> instances = client.getInstances("Server-Provider");
    @SuppressWarnings("deprecation")
    ServiceInstance instance = (ServiceInstance) instances;
    String info = "host：" + instance.getHost() + "，service_id：" + instance.getServiceId();
    log.info(info);
    return info;
  }
  @GetMapping("/hello")
  public String hello() {
    return "hello world";
  }
}
