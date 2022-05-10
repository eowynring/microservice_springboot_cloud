package com.boot.enums;

/**
 * @author guofei
 * @date 2022/5/10 2:02 PM
 */
public enum BusinessMsgEnum {

  /**
   * 参数异常
   */
  PARAMETER_EXCEPTION("102", "参数异常!"),

  /**
   * 等待超时
   */
  SERVICE_TIME_OUT("103", "服务调用超时!"),

  /**
   * 参数过大
   */
  PARAMETER_BIG_EXCEPTION("102", "输入的图片数量不能超过50张!"),

  /**
   * 500 : 发生异常
   */
  UNEXPECTED_EXCEPTION("500", "系统发生异常，请联系管理员!");

  /**
   * 消息码
   */
  private String code;
  /**
   * 消息内容
   */
  private String msg;

  BusinessMsgEnum(String code, String msg) {
    this.code = code;
    this.msg = msg;
  }

  public String code() {
    return code;
  }

  public String msg() {
    return msg;
  }
}
