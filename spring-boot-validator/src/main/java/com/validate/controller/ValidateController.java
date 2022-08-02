package com.validate.controller;

import com.common.JsonResult;
import com.validate.handle.ValidationList;
import com.validate.pojo.UserDTO;
import javax.validation.Valid;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author guofei
 * @date 2022/8/2 10:35 AM
 */
@RestController
public class ValidateController {

  @PostMapping("/save")
  public JsonResult saveUser(@RequestBody @Validated(UserDTO.Save.class) UserDTO userDTO){
    return new JsonResult(userDTO);
  }

  @PostMapping("/udpate")
  public JsonResult updateUser(@RequestBody @Validated(UserDTO.Update.class) UserDTO userDTO){
    return new JsonResult(userDTO);
  }

  @PostMapping("saveList")
  public JsonResult saveList(@RequestBody @Validated(UserDTO.Save.class) ValidationList<UserDTO> userList){
    return new JsonResult(userList);
  }



}
