package com.validate.service;

import com.common.JsonResult;
import com.validate.handle.ValidationList;
import com.validate.pojo.UserDTO;
import com.validate.pojo.UserDTOs;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.Min.List;
import javax.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author guofei
 * @date 2022/8/2 2:04 PM
 */
@Service
@Validated
@Slf4j
public class ValidateService {

  public JsonResult testBaseType(@NotNull @Min(10) Integer age){
    return new JsonResult(age);
  }

  public UserDTO saveUser(@Valid UserDTO userDTO){
    return userDTO;
  }

  @Validated(UserDTO.Save.class)
  public JsonResult saveService(@Valid UserDTO userDTO) {
    log.info("userDTO=[{}]", userDTO.toString());
    return  new  JsonResult(userDTO);
  }


  //@Validated(UserDTO.Save.class)
  public JsonResult testList(@Valid ValidationList<UserDTO> userDTO) {
    log.info("userDTO=[{}]", userDTO.toString());
    return  new  JsonResult(userDTO);
  }

  @Validated
  public JsonResult testList1( @Valid UserDTOs userDTOs) {
    log.info("userDTO=[{}]", userDTOs.toString());
    return  new  JsonResult(userDTOs);
  }




}
