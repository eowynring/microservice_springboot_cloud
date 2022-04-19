package com.dynamic.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.dynamic.mapper.MemberMapper;
import com.dynamic.pojo.Member;
import com.dynamic.service.MemberService;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * @author guofei
 * @date 2022/4/19 2:02 PM
 */
@Service
public class MemberServiceImpl implements MemberService {

  @Resource
  private MemberMapper memberMapper;

  @DS("slave")
  @Override
  public Member get() {
    return memberMapper.selectById(1);
  }


  @Override
  public int insert(Member member) {
    return memberMapper.insert(member);
  }
}
