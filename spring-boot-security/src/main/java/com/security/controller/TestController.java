package com.security.controller;

import org.omg.CORBA.PUBLIC_MEMBER;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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

  @GetMapping("index")
  public Object index(){
    return SecurityContextHolder.getContext().getAuthentication();
  }

  @GetMapping("index")
  public Object index(Authentication authentication) {
    return authentication;
  }

  @GetMapping("/signout/success")
  public String signout(){
    return "退出成功，请重新登录";
  }

}
