package com.readwrite.service;

import com.readwrite.mapper.MemberMapper;
import com.readwrite.pojo.Member;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author guofei
 * @date 2022/4/18 11:27 AM
 */
@Service
@Slf4j
public class MemberService {

  @Resource
  private MemberMapper memberMapper;
  public void insert(Member member){
    memberMapper.insert(member);
  };

  public void get(int id) {
    Member select = memberMapper.select(id);
    log.info("--member--{}",select);
  }
}
