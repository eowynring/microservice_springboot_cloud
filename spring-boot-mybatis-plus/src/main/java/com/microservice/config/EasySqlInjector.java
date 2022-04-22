package com.microservice.config;

import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.injector.DefaultSqlInjector;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import com.baomidou.mybatisplus.extension.injector.methods.InsertBatchSomeColumn;
import java.util.List;

/**
 * @author guofei
 * @date 2022/4/22 6:21 PM
 */
public class EasySqlInjector extends DefaultSqlInjector {

  /**
   * 支持自定义数据方法注入
   * @param mapperClass
   * @param tableInfo
   * @return
   */
  @Override
  public List<AbstractMethod> getMethodList(Class<?> mapperClass, TableInfo tableInfo) {
    // 防止父类方法无法使用
    List<AbstractMethod> methodList = super.getMethodList(mapperClass, tableInfo);
    methodList.add(new InsertBatchSomeColumn());
    return methodList;
  }
}
