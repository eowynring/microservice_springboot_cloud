package com.shardingsphere.controller;

import com.shardingsphere.dao.MemberDao;
import com.shardingsphere.mapper.Member2Mapper;
import com.shardingsphere.pojo.Member;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author: GuoFei
 * @date: 2022-05-02 14:26
 */

@RestController
public class Member2Controller {



    @Resource
    private MemberDao memberDao;

    @GetMapping("/get")
    public void insert(){
        Member member = new Member();
        member.setId(6);
        member.setName("shardingsphere");
        //member2Mapper.insert(member);
        final val list = memberDao.getList();
        System.out.println(list);
    }
}
