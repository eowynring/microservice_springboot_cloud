package com.microservice.service;

import com.microservice.hystrix.TestHystrixServiceImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author guofei
 * @date 2022/4/6 2:41 PM
 */
@FeignClient(value = "spring-boot-mybatis-plus",fallback = TestHystrixServiceImpl.class)
public interface OpenFeignService {

  /**
   * 测试 OpenFeign
   * @return
   */
  @GetMapping("/test")
  String testOpenFeign();
}
