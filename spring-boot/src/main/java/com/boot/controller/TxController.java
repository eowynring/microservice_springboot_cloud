package com.boot.controller;

import com.boot.manager.TestManager;
import com.boot.service.TxService;
import com.boot.service.TxUserService;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author guofei
 * @date 2022/5/18 4:56 PM
 * 测试事务控制器
 */
@RestController
@Slf4j
public class TxController {

  @Resource
  private TxService txService;

  @Resource
  private TxUserService txUserService;

  @Resource
  private TestManager testManager;

  @GetMapping("/testTx")
  public void testTx() throws Exception {
    txService.A();
    return;
  }

  @PostMapping("/test")
  public void test(){
    List<Boolean> list = new ArrayList<>();
      testManager.test(list);
      log.info("list->{}",list);
  }
}
