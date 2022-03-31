package com.microservice;

import com.microservice.mapper.UserMapper;
import com.microservice.pojo.User;
import java.util.List;
import javax.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author guofei
 * @date 2022/3/31 6:41 PM
 */
@SpringBootTest
public class SampleTest {

  @Resource
  private UserMapper userMapper;

  @Test
  public void testSelect(){
    System.out.println(("----- selectAll method test ------"));
    List<User> userList = userMapper.selectList(null);
    userList.forEach(System.out::println);  }

}
