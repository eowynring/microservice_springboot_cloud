package com.boot;

import com.boot.pojo.User;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * @author guofei
 * @date 2022/5/12 10:37 AM
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ApplicationSessionTest {

  private MockMvc mockMvc;
  private MockHttpSession session;

  @Resource
  private WebApplicationContext wac;

  @Before
  public void setupMockMvc(){
    mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    session = new MockHttpSession();
    User user =new User();
    user.setUsername("Dopa");
    user.setPasswd("ac3af72d9f95161a502fd326865c2f15");
    session.setAttribute("user", user);
  }
}
