package com.boot.controller;

import com.boot.service.UserService;
import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author guofei
 * @date 2022/5/7 2:00 PM
 */
@RestController
public class UserController {

  @Resource
  @Qualifier("UserServiceImpl")
  private UserService userService;

  @GetMapping("/test")
  public String test(){
    return userService.getUserList().toJSONString();
  }

}
