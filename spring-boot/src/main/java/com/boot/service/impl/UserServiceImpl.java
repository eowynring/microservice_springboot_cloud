package com.boot.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.boot.mapper.UserMapper;
import com.boot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author guofei
 * @date 2022/5/7 1:52 PM
 */
@Service("UserServiceImpl")
public class UserServiceImpl implements UserService {



  @Override
  public JSONObject getUserList() {
    JSONObject obj = new JSONObject();
    obj.put("service","I'm service");
    return obj;
  }
}
