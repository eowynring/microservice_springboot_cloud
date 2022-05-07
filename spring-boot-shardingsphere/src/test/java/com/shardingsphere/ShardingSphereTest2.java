package com.shardingsphere;

import com.shardingsphere.mapper.Member2Mapper;
import com.shardingsphere.pojo.Member;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @author: GuoFei
 * @date: 2022-05-02 14:21
 */


@RunWith(SpringRunner.class)
@SpringBootTest
public class ShardingSphereTest2 {

    @Resource
    private Member2Mapper member2Mapper;

    @Test
    public void insert(){
        Member member = new Member();
        member.setId(7);
        member.setName("shardingsphere");
        ///member2Mapper.insert(member);
    }
}

