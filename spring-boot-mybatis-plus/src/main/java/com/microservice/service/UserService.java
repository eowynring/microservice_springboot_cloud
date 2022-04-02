package com.microservice.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.microservice.mapper.UserMapper;
import com.microservice.pojo.User;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * @author guofei
 * @date 2022/4/2 3:54 PM
 */
@Service
public class UserService {

  @Resource
  private UserMapper userMapper;

  public IPage<User> userIPage(IPage<User> userIPage,Integer state){
    return userMapper.selectPage(userIPage, null);
  }


}
