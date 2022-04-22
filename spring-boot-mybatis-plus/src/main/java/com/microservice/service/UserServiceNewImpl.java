package com.microservice.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.microservice.mapper.UserMapper;
import com.microservice.pojo.User;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Function;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;

/**
 * @author guofei
 * @date 2022/4/22 5:25 PM
 */
@Service
public class UserServiceNewImpl extends ServiceImpl<UserMapper,User> implements UserServiceNew{

  private static List<User> users = new CopyOnWriteArrayList<>();
  static {
    for (int i = 0; i < 10000; i++) {
      User user = new User(null, "jordan" + i, i, "jordan" + i+"@gmail.com", 0);
      users.add(user);
    }
  }

  @Override
  public boolean saveBatchTest() {
    long startTime = System.currentTimeMillis();
    // run real action
    boolean b = saveBatch(users);
    long endTime = System.currentTimeMillis();
    System.out.println("方法耗时：" + (endTime - startTime)); // 方法耗时
    return b;
  }
}
