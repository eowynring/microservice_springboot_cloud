package com.dynamic.service;

import com.dynamic.pojo.Member;

/**
 * @author guofei
 * @date 2022/4/19 2:02 PM
 */
public interface MemberService {

  Member get();

  int insert(Member member);

}
