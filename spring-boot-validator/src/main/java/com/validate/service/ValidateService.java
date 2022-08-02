package com.validate.service;

import com.common.JsonResult;
import com.validate.pojo.UserDTO;
import javax.validation.Valid;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

/**
 * @author guofei
 * @date 2022/8/2 2:04 PM
 */
@Service
@Validated
public class ValidateService {

  @Validated
  public UserDTO saveUser(@Valid UserDTO userDTO){
    return userDTO;
  }

}
