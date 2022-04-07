package com.microservice.hystrix;

import com.microservice.service.OpenFeignService;
import org.springframework.stereotype.Component;

/**
 * @author guofei
 * @date 2022/4/6 6:11 PM
 */
@Component
public class TestHystrixServiceImpl implements OpenFeignService {

  @Override
  public String testOpenFeign() {
    return "error";
  }
}
