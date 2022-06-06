package com.boot.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.boot.mapper.TxUserMapper;
import com.boot.pojo.TxUser;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

/**
 * @author guofei
 * @date 2022/6/6 12:12 PM
 */
@Service
public class TxUserService extends ServiceImpl<TxUserMapper, TxUser> {


  //@Transactional(rollbackFor = Exception.class)
  public void insert() {
    TxUser txUser = new TxUser();
    txUser.setUsername("gggg");
    txUser.setPasswd("123");
    txUser.setStatus("1");
    save(txUser);
    int i = 10 / 0;
    TxUser txUser1 = new TxUser();
    txUser1.setId(1);
    txUser1.setUsername("ceshiceshi");
    updateById(txUser1);
  }

}
