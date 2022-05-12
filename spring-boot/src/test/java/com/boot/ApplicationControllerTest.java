package com.boot;

import com.alibaba.fastjson.JSONObject;
import com.boot.pojo.User;
import com.boot.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

/**
 * 测试service
 * @author guofei
 * @date 2022/5/12 10:19 AM
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ApplicationControllerTest {


  private MockMvc mockMvc;

  @Resource
  private WebApplicationContext wac;

  @Resource
  ObjectMapper mapper;

  @Before
  public void setupMockMvc(){
    mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
  }

  @Test
  public void test() throws Exception {
    mockMvc.perform(
            MockMvcRequestBuilders.get("/user/{userName}", "scott")
                .contentType(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.username").value("scott"))
        .andDo(MockMvcResultHandlers.print());
  }

  @Test
  //@Transactional
  public void testSave() throws Exception {
    User user = new User();
    user.setUsername("Dopa");
    user.setPasswd("ac3af72d9f95161a502fd326865c2f15");
    user.setStatus("1");
    String userJson = mapper.writeValueAsString(user);
    mockMvc.perform(
            MockMvcRequestBuilders.post("/user/save")
                .contentType(MediaType.APPLICATION_JSON)
                .content(userJson.getBytes()))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andDo(MockMvcResultHandlers.print());
  }


}
