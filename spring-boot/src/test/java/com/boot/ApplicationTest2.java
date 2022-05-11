package com.boot;

import com.boot.pojo.User;
import com.fasterxml.jackson.databind.json.JsonMapper;
import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import lombok.extern.slf4j.Slf4j;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.WebApplicationContext;

/**
 * 一个标准的Spring Boot测试单元应有如下的代码结构
 *
 * @author guofei
 * @date 2022/5/11 11:47 PM
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ApplicationTest2 {


  private MockMvc mockMvc;

  @Resource
  private WebApplicationContext wac;

  @Before
  public void setupMockMvc() {
    mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
  }

  @Test
  public void mockGetRequest() throws Exception {
    ResultActions resultActions = mockMvc.perform(
        MockMvcRequestBuilders.get("/hello?name={name}", "mrbird"));
    log.info("resultActions-->{}", resultActions);
  }

  @Test
  public void mockPostRequest() throws Exception {
    ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.post("/user/{id}", 1));
    log.info("resultActions-->{}", resultActions);
  }


  @Test
  public void mockFileUploadRequest() throws Exception {
    ResultActions resultActions = mockMvc.perform(
        MockMvcRequestBuilders.multipart("/fileupload").file("file", "文件内容".getBytes("utf-8")));
    log.info("resultActions-->{}", resultActions);
  }

  @Test
  public void mockParamRequest() throws Exception {
    // 模拟发送一个message参数，值为hello
    ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get("/hello").param("message", "hello"));
    // 模拟提交一个checkbox值，name为hobby，值为sleep和eat
    ResultActions resultActions1 = mockMvc.perform(MockMvcRequestBuilders.get("/saveHobby").param("hobby", "sleep", "eat"));
    log.info("resultActions-->{}", resultActions);
    log.info("resultActions1-->{}", resultActions1);
  }

  @Test
  public void mockMultiValueMapRequest() throws Exception {
    MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
    params.add("name", "mrbird");
    params.add("hobby", "sleep");
    params.add("hobby", "eat");
    ResultActions perform = mockMvc.perform(
        MockMvcRequestBuilders.get("/hobby/save").params(params));
    log.info("perform-->{}", perform);
  }


  /**
   * 测试JSON
   * @throws Exception
   */
  @Test
  public void mockJsonRequest() throws Exception {
    String jsonStr = "{\"username\":\"Dopa\",\"passwd\":\"ac3af72d9f95161a502fd326865c2f15\",\"status\":\"1\"}";
    ResultActions perform = mockMvc.perform(
        MockMvcRequestBuilders.post("/user/save").content(jsonStr.getBytes()));

    //实际开发中,可以借助Springboot自带的jackson技术来序列化一个Java对象
    User user = new User();
    user.setUsername("Dopa");
    user.setPasswd("ac3af72d9f95161a502fd326865c2f15");
    user.setStatus("1");
    JsonMapper mapper = null;
    String userJson = mapper.writeValueAsString(user);
    mockMvc.perform(MockMvcRequestBuilders.post("/user/save").content(userJson.getBytes()));
    log.info("perform-->{}", perform);
  }


  @Test
  public void mockCookieAndSessionRequest(String name,String value) throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.get("/index").sessionAttr(name, value));
    mockMvc.perform(MockMvcRequestBuilders.get("/index").cookie(new Cookie(name, value)));
    //log.info("perform-->{}", perform);
  }

  /**
   * 设置请求的Content-Type：
   * mockMvc.perform(MockMvcRequestBuilders.get("/index").contentType(MediaType.APPLICATION_JSON_UTF8));
   *
   * 设置返回格式为JSON：
   * mockMvc.perform(MockMvcRequestBuilders.get("/user/{id}", 1).accept(MediaType.APPLICATION_JSON));
   *
   * 模拟HTTP请求头：
   * mockMvc.perform(MockMvcRequestBuilders.get("/user/{id}", 1).header(name, values));
   */





}
