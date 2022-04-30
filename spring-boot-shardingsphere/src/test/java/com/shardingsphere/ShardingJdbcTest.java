package com.shardingsphere;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.shardingsphere.mapper.CourseMapper;
import com.shardingsphere.mapper.DictMapper;
import com.shardingsphere.mapper.UserMapper;
import com.shardingsphere.pojo.Course;
import com.shardingsphere.pojo.Dict;
import com.shardingsphere.pojo.User;
import javax.annotation.Resource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author: GuoFei
 * @date: 2022-04-30 13:47
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ShardingJdbcTest {


  @Resource
  private CourseMapper courseMapper;

  //添加课程
  @Test
  public void addCourse() {
    Course course = new Course();
    //cid由我们设置的策略，雪花算法进行生成
    course.setCname("Java");
    course.setUserId(100L);
    course.setStatus("Normal");
    courseMapper.insert(course);
  }

  @Test
  public void findCourse() {
    QueryWrapper<Course> wrapper = new QueryWrapper<>();
    wrapper.eq("cid", 536248443081850881L);
    courseMapper.selectOne(wrapper);
  }

  @Test
  public void addCourse1() {
    Course course = new Course();
    //cid由我们设置的策略，雪花算法进行生成
    course.setCname("python");
    //分库根据user_id
    course.setUserId(100L);
    course.setStatus("Normal");
    courseMapper.insert(course);

    course.setCname("c++");
    course.setUserId(111L);
    courseMapper.insert(course);
  }

  @Resource
  private UserMapper userMapper;

  @Test
  public void addUser(){
    User user = new User();
    user.setUsername("Jack");
    user.setStatus("Normal");
    userMapper.insert(user);
  }

  @Test
  public void findUser() {
    QueryWrapper<User> wrapper = new QueryWrapper<>();
    wrapper.eq("user_id", 536472243283165185L);
    userMapper.selectOne(wrapper);
  }

  @Resource
  private DictMapper dictMapper;

  @Test
  public void addDict() {
    Dict dict = new Dict();
    dict.setStatus("Normal");
    dict.setValue("启用");
    dictMapper.insert(dict);
  }

  @Test
  public void deleteDict() {
    QueryWrapper<Dict> wrapper = new QueryWrapper<>();
    wrapper.eq("dict_id", 536486065947541505L);
    dictMapper.delete(wrapper);
  }


}
