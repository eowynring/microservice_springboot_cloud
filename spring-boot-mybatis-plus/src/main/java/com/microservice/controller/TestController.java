package com.microservice.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.microservice.mapper.UserMapper;
import com.microservice.pojo.User;
import com.microservice.service.UserService;
import com.microservice.service.UserServiceNew;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author guofei
 * @date 2022/4/2 3:33 PM
 */
@RestController
public class TestController {

  @Resource
  private UserMapper userMapper;

  @Resource
  private UserService userService;

  @Resource
  private UserServiceNew userServiceNew;

  @GetMapping("/test")
  public String test(){
    System.out.println(("----- selectAll method test ------"));
    /*try {
      Thread.sleep(3000);
    } catch (InterruptedException e) {

    }*/
    int i = 10/0;
    List<User> userList = userMapper.selectList(null);
    userList.forEach(System.out::println);
    return "ok";
  }

  @GetMapping("/testPage")
  public String testPage(){
    System.out.println(("----- selectAll method test ------"));
    Page<User> objectPage = new Page<>(1, 2);
    IPage<User> userIPage = userService.userIPage(objectPage,null);
    System.out.println("总页数： "+userIPage.getPages());
    System.out.println("总记录数： "+userIPage.getTotal());
    userIPage.getRecords().forEach(System.out::println);
    return "ok";
  }

  @PostMapping("/testBatch")
  public String testBatch(){
    boolean b = userServiceNew.saveBatchTest();
    return b ? "success" : "error";
  }
}
