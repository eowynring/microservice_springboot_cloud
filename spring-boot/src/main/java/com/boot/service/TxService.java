package com.boot.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.boot.mapper.UserMapper;
import com.boot.pojo.Member;
import com.boot.pojo.User;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.framework.AopContext;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

/**
 * @author guofei
 * @date 2022/5/18 4:57 PM
 */
@Service
@Slf4j
public class TxService extends ServiceImpl<UserMapper, Member> {

  @Resource
  private TxService txService;

  @Resource
  private UserMapper userMapper;

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

  public void  insertBatch(){
    for (int i = 0; i < 10000; i++) {
      Member member = new Member();
      member.setName(i+"");
      save(member);
    }
  }

  @Resource
  private DataSourceTransactionManager dataSourceTransactionManager;

  @Resource
  private TransactionDefinition transactionDefinition;

  public void updateTest(List<Member> members, CountDownLatch countDownLatch){
    TransactionStatus transactionStatus = dataSourceTransactionManager.getTransaction(transactionDefinition);
    log.info("子线程：[{}]" , Thread.currentThread().getName());
    try {
      members.forEach(e->{
        String newTeacher = "GF_" + new Random().nextInt(100);
        e.setId(e.getId());
        e.setName(newTeacher);
        userMapper.updateById(e);
      });
      dataSourceTransactionManager.commit(transactionStatus);
    } catch (Exception e) {
      dataSourceTransactionManager.rollback(transactionStatus);
    }finally {
      countDownLatch.countDown();
    }
  }

  public void updateBatch(){
    List<Member> members = userMapper.selectList(null);
    long start = System.currentTimeMillis();

    // 线程数量
    final Integer threadCount = 17;

    //每个线程处理的数据量
    final Integer dataPartionLength = (members.size() + threadCount - 1) / threadCount;
    // 创建多线程处理任务
    ExecutorService studentThreadPool = Executors.newFixedThreadPool(threadCount);
    CountDownLatch threadLatchs = new CountDownLatch(threadCount);


    for (int i = 0; i < threadCount; i++) {
      // 每个线程处理的数据
      List<Member> threadDatas = members.stream()
          .skip(i * dataPartionLength).limit(dataPartionLength).collect(Collectors.toList());
      studentThreadPool.execute(() -> updateTest(threadDatas, threadLatchs));
    }
    try {
      // 倒计时锁设置超时时间 30s
      threadLatchs.await(30, TimeUnit.SECONDS);
    } catch (Throwable e) {
      e.printStackTrace();
    }
    long end = System.currentTimeMillis();
    log.info("主线程：[{}]完成" , Thread.currentThread().getName());
    log.info("耗时：[{}] ms", end-start);

  }



}
