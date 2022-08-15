package com.validate.main;

import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

/**
 * @author guofei
 * @date 2022/8/4 5:37 PM
 */
@Slf4j
@Service
public class TestService {


  public void getCar(@Validated Car car){
    log.info("car=[{}]",car);
  }

}
