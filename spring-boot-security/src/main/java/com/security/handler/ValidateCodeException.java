package com.security.handler;

import org.springframework.security.core.AuthenticationException;

/**
 * @author guofei
 * @date 2022/5/16 11:06 PM
 */
public class ValidateCodeException extends AuthenticationException {
  private static final long serialVersionUID = 5022575393500654458L;

  ValidateCodeException(String message) {
    super(message);
  }
}
