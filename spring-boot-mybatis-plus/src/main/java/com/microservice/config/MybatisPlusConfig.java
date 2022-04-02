package com.microservice.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.OptimisticLockerInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author guofei
 * @date 2022/4/1 10:34 AM
 */
@Configuration
@MapperScan("com.microservice.mapper")
public class MybatisPlusConfig {


  /**
   * 乐观锁配置
   * @return MybatisPlusInterceptor
   */
  @Bean
  public MybatisPlusInterceptor optimisticLockerInnerInterceptor(){
    MybatisPlusInterceptor mybatisPlusInterceptor = new MybatisPlusInterceptor();
    mybatisPlusInterceptor.addInnerInterceptor(new OptimisticLockerInnerInterceptor());
    return mybatisPlusInterceptor;
  }

  @Bean
  public MybatisPlusInterceptor paginationInnerInterceptor(){
    MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
    PaginationInnerInterceptor paginationInnerInterceptor = new PaginationInnerInterceptor();
    paginationInnerInterceptor.setDbType(DbType.MYSQL);
    paginationInnerInterceptor.setOverflow(true);
    interceptor.addInnerInterceptor(paginationInnerInterceptor);
    return interceptor;
  }
}
