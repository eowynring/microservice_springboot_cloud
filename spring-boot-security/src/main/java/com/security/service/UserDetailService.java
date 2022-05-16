package com.security.service;

import com.security.pojo.MyUser;
import javax.annotation.Resource;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author guofei
 * @date 2022/5/16 10:40 PM
 */
@Configuration
public class UserDetailService implements UserDetailsService {

  @Resource
  private PasswordEncoder passwordEncoder;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    // 模拟一个用户，替代数据库获取逻辑
    MyUser user = new MyUser();
    user.setUserName(username);
    user.setPassword(this.passwordEncoder.encode("123456"));
    // 输出加密后的密码
    System.out.println(user.getPassword());

    return new User(username, user.getPassword(), user.isEnabled(),
        user.isAccountNonExpired(), user.isCredentialsNonExpired(),
        user.isAccountNonLocked(), AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
  }
}
