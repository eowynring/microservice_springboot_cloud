package com.boot.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.boot.mapper.UserMapper;
import com.boot.pojo.User;
import com.boot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author guofei
 * @date 2022/5/7 1:53 PM
 */
@Service("UserServiceImpl1")
public class UserServiceImpl1 implements UserService {



  @Override
  public JSONObject getUserList() {
    JSONObject obj = new JSONObject();
    obj.put("service","I'm service");
    return obj;
  }

  @Override
  public User getUserListNew() {
    return null;
  }

  @Override
  public void save(User user) {
    return;
  }
}
