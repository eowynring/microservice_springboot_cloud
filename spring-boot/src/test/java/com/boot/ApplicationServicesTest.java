package com.boot;

import com.alibaba.fastjson.JSONObject;
import com.boot.pojo.User;
import com.boot.service.UserService;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * 测试service
 * @author guofei
 * @date 2022/5/12 10:19 AM
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ApplicationServicesTest {

  @Resource
  @Qualifier("UserServiceImpl")
  private UserService userService;

  @Test
  public void test() {
    JSONObject userList = this.userService.getUserList();
    log.info("userList->{}", userList.toJSONString());
  }

  /**
   * 和在Controller中引用Service相比，在测试单元中对Service测试完毕后，数据能自动回滚，
   * 只需要在测试方法上加上@Transactional注解
   * @Transactional
   */
  @Test
  @Transactional
  public void testSave() {
    User user = new User();
    user.setId(1);
    user.setUsername("JUnit");
    user.setPasswd("123456");
    user.setStatus("1");
    this.userService.save(user);
  }


}
