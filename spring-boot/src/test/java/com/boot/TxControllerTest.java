package com.boot;

import com.boot.service.TxService;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author guofei
 * @date 2022/7/3 5:09 PM
 */

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class TxControllerTest {



  @Resource
  private TxService txService;
  @Test
  public void testInsert(){
    txService.updateBatch();
  }

}
