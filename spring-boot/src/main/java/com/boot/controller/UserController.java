package com.boot.controller;

import com.alibaba.fastjson.JSONObject;
import com.boot.anno.UnInterception;
import com.boot.pojo.User;
import com.boot.service.UserService;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author guofei
 * @date 2022/5/7 2:00 PM
 */
@RestController
@Slf4j
public class UserController {

  @Resource
  @Qualifier("UserServiceImpl")
  private UserService userService;

  @GetMapping("/test")
  public String test(){
    return "hello";
  }

  @GetMapping("/test2")
  @UnInterception
  public String test2(){
    return "test2";
  }

  @GetMapping("/user/{userName}")
  public User getUserByName(@PathVariable(value = "userName") String userName) {
    log.info("-userName->{}", userName);
    return this.userService.getUserListNew();
  }

  @PostMapping("/user/save")
  public void saveUser(@RequestBody User user) {
    log.info("-user->{}", user);
    this.userService.save(user);
  }



}
