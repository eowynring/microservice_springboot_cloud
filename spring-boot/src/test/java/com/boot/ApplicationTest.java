package com.boot;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 一个标准的Spring Boot测试单元应有如下的代码结构
 * @author guofei
 * @date 2022/5/11 11:47 PM
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTest {

  /**
   * JUnit4中包含了几个比较重要的注解：
   * @BeforeClass、@AfterClass、@Before、@After和@Test
   * @BeforeClass和@AfterClass在每个类加载的开始和结束时运行，必须为静态方法；
   * @Before和@After则在每个测试方法开始之前和结束之后运行
   */

  @BeforeClass
  public static void beforeClassTest() {
    System.out.println("before class test");
  }

  @Before
  public void beforeTest() {
    System.out.println("before test");
  }

  @Test
  public void Test1() {
    System.out.println("test 1+1=2");
    Assert.assertEquals(2, 1 + 1);
  }

  @Test
  public void Test2() {
    System.out.println("test 2+2=4");
    Assert.assertEquals(4, 2 + 2);
  }

  @After
  public void afterTest() {
    System.out.println("after test");
  }

  @AfterClass
  public static void afterClassTest() {
    System.out.println("after class test");
  }
}
