package com.readwrite.aop;

import com.readwrite.handle.DBContextHolder;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * 设置路由key，默认情况下，所有的查询都走从库，插入/修改/删除走主库，通过方法名来区别操作类型（CRUD）
 * @author guofei
 * @date 2022/4/18 11:20 AM
 */
@Aspect
@Component
public class DataSourceAop {

  @Pointcut("!@annotation(com.readwrite.annotation.Master) " +
      "&& (execution(* com.readwrite.service..*.select*(..)) " +
      "|| execution(* com.readwrite.service..*.get*(..)))")
  public void readPointcut() {

  }

  @Pointcut("@annotation(com.readwrite.annotation.Master) " +
      "|| execution(* com.readwrite.service..*.insert*(..)) " +
      "|| execution(* com.readwrite.service..*.add*(..)) " +
      "|| execution(* com.readwrite.service..*.update*(..)) " +
      "|| execution(* com.readwrite.service..*.edit*(..)) " +
      "|| execution(* com.readwrite.service..*.delete*(..)) " +
      "|| execution(* com.readwrite.service..*.remove*(..))")
  public void writePointcut(){

  }

  @Before("readPointcut()")
  public void read(){
    DBContextHolder.slave();
  }

  @Before("writePointcut()")
  public void write(){
    DBContextHolder.master();
  }

  /**
   * 另一种写法：if...else...  判断哪些需要读从数据库，其余的走主数据库
   * @param joinPoint
   */
  /*@Before("execution(* com.readwrite.service.impl.*.*(..))")
  public void before(JoinPoint joinPoint){
    String name = joinPoint.getSignature().getName();
    if (StringUtils.startsWithAny(name,"get","selet","find")){
      DBContextHolder.slave();
    }else {
      DBContextHolder.master();
    }
  }*/




}
