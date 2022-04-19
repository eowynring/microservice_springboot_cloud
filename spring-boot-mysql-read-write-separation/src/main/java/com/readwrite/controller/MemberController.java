package com.readwrite.controller;

import com.readwrite.pojo.Member;
import com.readwrite.service.MemberService;
import javax.annotation.Resource;
import lombok.Getter;
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
    member.setId(1);
    member.setName("guofei");
    memberService.insert(member);
    return "ok";
  }

  @PostMapping("/get")
  public String get(){
    memberService.get(1);
    return "ok";
  }

  @GetMapping("/getMaster")
  public String getMaster(){
    memberService.getMaster();
    return "ok";
  }
}
