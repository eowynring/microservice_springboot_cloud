package com.microservice.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.microservice.pojo.User;

/**
 * @author guofei
 * @date 2022/4/22 5:24 PM
 */
public interface UserServiceNew extends IService<User> {

  boolean saveBatchTest();

}
