package com.microservice.controller;

import com.microservice.service.OpenFeignService;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author guofei
 * @date 2022/4/6 2:43 PM
 */
@RestController
@RequestMapping("/openfeign")
public class OpenFeignController {

  @Resource
  private OpenFeignService openFeignService;

  @GetMapping("/testOpenFeign")
  public String test(){
    return openFeignService.testOpenFeign();
  }
}
