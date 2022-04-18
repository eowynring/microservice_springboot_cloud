package com.readwrite.handle;

import com.readwrite.enums.DBTypeEnum;
import java.util.concurrent.atomic.AtomicInteger;
import lombok.extern.slf4j.Slf4j;

/**
 * 通过ThreadLocal将数据源设置到每个线程上下文中
 * @author guofei
 * @date 2022/4/18 11:11 AM
 */
@Slf4j
public class DBContextHolder {

  private static final ThreadLocal<DBTypeEnum> enumThreadLocal = new ThreadLocal<>();
  private static final AtomicInteger atomicInteger = new AtomicInteger(-1);

  public static void set(DBTypeEnum dbTypeEnum){
    enumThreadLocal.set(dbTypeEnum);
  }

  public static DBTypeEnum get(){
    return enumThreadLocal.get();
  }

  public static void master(){
    set(DBTypeEnum.MASTER);
    log.info("--DBContextHolder,switch master(DB)");
  }

  public static void slave(){
    set(DBTypeEnum.SLAVE);
    log.info("--DBContextHolder,switch slave(DB)");
  }
}
