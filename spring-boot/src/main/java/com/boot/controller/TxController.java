package com.boot.controller;

import com.boot.service.TxService;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author guofei
 * @date 2022/5/18 4:56 PM
 * 测试事务控制器
 */
@RestController
public class TxController {

  @Resource
  private TxService txService;

  @GetMapping("/testTx")
  public void testTx() throws Exception {
    txService.A();
    return;
  }

}
