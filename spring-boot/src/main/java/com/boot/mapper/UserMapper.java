package com.boot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.boot.pojo.Member;

import org.springframework.stereotype.Repository;

/**
 * @author guofei
 * @date 2022/5/7 1:52 PM
 */
@Repository
public interface UserMapper extends BaseMapper<Member> {


}
