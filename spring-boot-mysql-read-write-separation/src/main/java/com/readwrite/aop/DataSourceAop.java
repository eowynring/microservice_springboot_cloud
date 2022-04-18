package com.readwrite.aop;

import com.readwrite.handle.DBContextHolder;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * 设置路由key，默认情况下，所有的查询都走从库，插入/修改/删除走主库，通过方法名来区别草足类型（CRUD）
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




}
