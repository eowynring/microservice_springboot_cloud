package com.microservice.config;

import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.OptimisticLockerInnerInterceptor;
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
  public MybatisPlusInterceptor mybatisPlusInterceptor(){
    MybatisPlusInterceptor mybatisPlusInterceptor = new MybatisPlusInterceptor();
    mybatisPlusInterceptor.addInnerInterceptor(new OptimisticLockerInnerInterceptor());
    return mybatisPlusInterceptor;
  }
}
