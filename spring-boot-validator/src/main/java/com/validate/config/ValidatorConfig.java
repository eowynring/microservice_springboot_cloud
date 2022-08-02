package com.validate.config;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import org.hibernate.validator.HibernateValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * @author guofei
 * @date 2022/8/2 2:01 PM
 */
@Configuration
public class ValidatorConfig {

  @Bean
  public Validator validator() {
    ValidatorFactory validatorFactory = Validation.byProvider(HibernateValidator.class)
        .configure()
        // 快速失败模式
        .failFast(true)
        .buildValidatorFactory();
    return validatorFactory.getValidator();
  }

}
