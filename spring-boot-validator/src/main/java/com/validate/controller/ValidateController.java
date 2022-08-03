package com.validate.controller;

import com.alibaba.fastjson.JSONObject;
import com.common.JsonResult;
import com.validate.handle.ValidationList;
import com.validate.pojo.UserDTO;
import com.validate.service.ValidateService;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author guofei
 * @date 2022/8/2 10:35 AM
 */
@RestController
@Slf4j
@Validated
public class ValidateController {

  @Resource
  private ValidateService validateService;

  @PostMapping("/save")
  //public JsonResult saveUser(@RequestBody @Validated(UserDTO.Save.class) UserDTO userDTO){
  public JsonResult saveUser(@RequestBody @Validated UserDTO userDTO){
    return new JsonResult(userDTO);
  }

  @PostMapping("/udpate")
  public JsonResult updateUser(@RequestBody @Validated(UserDTO.Update.class) UserDTO userDTO){
    return new JsonResult(userDTO);
  }

  @PostMapping("/saveList")
  public JsonResult saveList(@RequestBody @Valid ValidationList<UserDTO> userList){
    log.info("userDTO=[{}]", userList.toString());
    return new JsonResult(userList);
  }

  @PostMapping("/saveService")
  public JsonResult saveService(@RequestBody UserDTO userDTO){
    log.info("userDTO=[{}]", userDTO.toString());
    return validateService.saveService(userDTO);
  }


  @PostMapping("/testBaseType")
  public JsonResult testBaseType(@NotNull @Min(10) Integer age){
    return new JsonResult(age);
  }



  @PostMapping("/testList")
  public JsonResult testList(){
    List<UserDTO> userDtos = getUserDtos();
    String s = JSONObject.toJSONString(userDtos);
    ValidationList validationList = JSONObject.parseObject(s, ValidationList.class);
    JsonResult jsonResult = validateService.testList(validationList);
    return jsonResult;
  }

  public List<UserDTO> getUserDtos(){
    ArrayList<UserDTO> userDTOS = new ArrayList<>();
    UserDTO userDTO1 = new UserDTO();
    userDTO1.setUserId(10L);
    userDTO1.setUserName("kobe");
    userDTOS.add(userDTO1);
    UserDTO userDTO2 = new UserDTO();
    userDTO2.setUserId(200L);
    userDTO2.setUserName("jordan");
    userDTOS.add(userDTO2);
    return userDTOS;
  }


}
