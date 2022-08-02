package com.validate.custom;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author guofei
 * @date 2022/8/2 1:53 PM
 */
public class EncryptIdValidator implements ConstraintValidator<EncryptId, String> {

  private static final Pattern PATTERN = Pattern.compile("^[a-f\\d]{32,256}$");


  @Override
  public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
    // 不为null才进行校验
    if (s != null) {
      Matcher matcher = PATTERN.matcher(s);
      return matcher.find();
    }
    return true;
  }
}
