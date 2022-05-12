package com.boot.service;

import com.alibaba.fastjson.JSONObject;
import com.boot.pojo.User;

/**
 * @author guofei
 * @date 2022/5/7 1:51 PM
 */
public interface UserService {

  JSONObject getUserList();

  User getUserListNew();

  void save(User user);
}
