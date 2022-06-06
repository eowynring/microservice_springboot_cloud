package com.boot.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.boot.mapper.UserMapper;
import com.boot.pojo.Member;
import com.boot.pojo.User;
import javax.annotation.Resource;
import org.springframework.aop.framework.AopContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

/**
 * @author guofei
 * @date 2022/5/18 4:57 PM
 */
@Service
public class TxService extends ServiceImpl<UserMapper, Member> {

  @Resource
  private TxService txService;

  /**
   * spring的事务只支持未检查异常（unchecked），不支持已检查异常（checked）。
   * @return
   * @throws Exception
   */
  @Transactional(rollbackFor = Exception.class)
  public String testTx() throws Exception {
    Member member = new Member();
    member.setName("gggggg");
    save(member);

    try {
      int a = 1/0;
    } catch (Exception e) {
      //throw new Exception("出错了。。。");
    }
    Member member1 = new Member();
    member1.setId(6);
    member1.setName("gggggg");
    updateById(member1);
    return "ok";
  }


  @Transactional(rollbackFor = Exception.class)
  public void A(){
    //insertTest();
    //txService.insertTest();
   insertTest();

    int o = 1/0;
  }


  public void  insertTest(){
      Member member = new Member();
      member.setName("ceshi");
      save(member);
      //int i = 1/0;
  }



}
