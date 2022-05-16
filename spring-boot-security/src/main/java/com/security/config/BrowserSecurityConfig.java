package com.security.config;

import com.security.handler.MyAuthenticationFailureHandler;
import com.security.handler.MyAuthenticationSuccessHandler;
import com.security.handler.ValidateCodeFilter;
import javax.annotation.Resource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @author guofei
 * @date 2022/5/16 10:38 PM
 */
@Configuration
public class BrowserSecurityConfig extends WebSecurityConfigurerAdapter {

  @Resource
  private MyAuthenticationSuccessHandler authenticationSuccessHandler;

  @Resource
  private MyAuthenticationFailureHandler authenticationFailureHandler;

  @Resource
  private ValidateCodeFilter validateCodeFilter;

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    // 1、http.formLogin() // 表单方式

    /* 2、http.httpBasic() // HTTP Basic方式
        .and()
        .authorizeRequests() // 授权配置
        .anyRequest()  // 所有请求
        .authenticated(); // 都需要认证*/

    /*3、http.formLogin() // 表单登录
        // http.httpBasic() // HTTP Basic
        .loginPage("/authentication/require") // 登录跳转 URL
        .loginProcessingUrl("/login") // 处理表单登录 URL
        .successHandler(authenticationSuccessHandler) // 处理登录成功
        .failureHandler(authenticationFailureHandler) // 处理登录失败
        .and()
        .authorizeRequests() // 授权配置
        .antMatchers("/authentication/require", "/login.html").permitAll() // 登录跳转 URL 无需认证
        .anyRequest()  // 所有请求
        .authenticated() // 都需要认证
        .and().csrf().disable();*/
    /*4、http.formLogin() // 表单登录
        // http.httpBasic() // HTTP Basic
        .loginPage("/authentication/require") // 登录跳转 URL
        .loginProcessingUrl("/login") // 处理表单登录 URL
        .successHandler(authenticationSuccessHandler) // 处理登录成功
        .failureHandler(authenticationFailureHandler) // 处理登录失败
        .and()
        .authorizeRequests() // 授权配置
        .antMatchers("/authentication/require",
            "/login.html",
            "/code/image").permitAll() // 无需认证的请求路径
        .anyRequest()  // 所有请求
        .authenticated() // 都需要认证
        .and().csrf().disable();*/

    http.addFilterBefore(validateCodeFilter, UsernamePasswordAuthenticationFilter.class) // 添加验证码校验过滤器
        .formLogin() // 表单登录
        // http.httpBasic() // HTTP Basic
        .loginPage("/authentication/require") // 登录跳转 URL
        .loginProcessingUrl("/login") // 处理表单登录 URL
        .successHandler(authenticationSuccessHandler) // 处理登录成功
        .failureHandler(authenticationFailureHandler) // 处理登录失败
        .and()
        .authorizeRequests() // 授权配置
        .antMatchers("/authentication/require",
            "/login.html",
            "/code/image").permitAll() // 无需认证的请求路径
        .anyRequest()  // 所有请求
        .authenticated() // 都需要认证
        .and().csrf().disable();
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }
}
