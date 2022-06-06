package com.boot.manager;

import com.boot.service.TxService;
import com.boot.service.TxUserService;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

/**
 * @author guofei
 * @date 2022/6/6 12:20 PM
 */
@Service
public class TestManager {

  @Resource
  private TxService txService;

  @Resource
  private TxUserService txUserService;

  @Transactional(rollbackFor = Exception.class)
  public void test(List<Boolean> list){

    try {
      txService.insertTest();
      txUserService.insert();
      list.add(true);
    } catch (Exception e) {
      e.printStackTrace();
      list.add(false);
      TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
    }
  }

}
