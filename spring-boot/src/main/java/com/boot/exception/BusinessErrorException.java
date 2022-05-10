package com.boot.exception;

import com.boot.enums.BusinessMsgEnum;

/**
 * @author guofei
 * @date 2022/5/10 2:11 PM
 */
public class BusinessErrorException extends RuntimeException{
  /**
   * 异常码
   */
  private String code;

  /**
   * 异常提示信息
   */
  private String message;

  public BusinessErrorException(BusinessMsgEnum businessMsgEnum) {
    this.code = businessMsgEnum.code();
    this.message = businessMsgEnum.msg();
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  @Override
  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }
}
