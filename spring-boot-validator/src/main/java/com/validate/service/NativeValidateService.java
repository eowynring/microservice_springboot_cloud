package com.validate.service;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

/**
 * @author guofei
 * @date 2022/8/4 2:03 PM
 */
@Component
@Validated
public class NativeValidateService {

  public int nativeValidate( @Min(value = 10) Integer age){
    System.out.println("age:"+age);
    return 1;
  }

}
