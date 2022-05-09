package com.boot.config;

import com.boot.handle.MyInterceptorHandle;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author guofei
 * @date 2022/5/7 2:07 PM
 */
@Configuration
public class MyInterceptorConfig implements WebMvcConfigurer {

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    //WebMvcConfigurer.super.addInterceptors(registry);
    // 实现WebMvcConfigurer不会导致静态资源被拦截
    registry.addInterceptor(new MyInterceptorHandle()).addPathPatterns("/**");

  }
}
