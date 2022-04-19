package com.readwrite.mapper;

import com.readwrite.pojo.Member;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author guofei
 * @date 2022/4/18 11:29 AM
 */
public interface MemberMapper {

  //Member select();

  void insert(Member member);

  Member select(int id);
}
