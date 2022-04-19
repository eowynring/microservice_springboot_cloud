package com.dynamic.controller;


import com.alibaba.fastjson.JSON;
import com.dynamic.pojo.Member;
import com.dynamic.service.MemberService;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author guofei
 * @date 2022/4/19 11:17 AM
 */
@RestController
@RequestMapping("/member")
public class MemberController {

  @Resource
  private MemberService memberService;
  @PostMapping("/insert")
  public String insert(){
    Member member = new Member();
    member.setId(2);
    member.setName("jordan");
    int insert = memberService.insert(member);
    return insert+"";
  }

  @PostMapping("/get")
  public String get(){
    Member member = memberService.get();
    return JSON.toJSONString(member);
  }

}
