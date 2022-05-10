package com.boot.handle;

import com.boot.enums.BusinessMsgEnum;
import com.boot.exception.BusinessErrorException;
import com.boot.result.JsonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author guofei
 * @date 2022/5/10 2:08 PM
 */
@ControllerAdvice
@ResponseBody
@Slf4j
public class GlobalExceptionHandler {


  /**
   * 拦截业务异常，返回业务异常信息 * @param ex
   * @return
   */
  @ExceptionHandler(BusinessErrorException.class)
  @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
  public JsonResult handleBusinessError(BusinessErrorException ex) {
    String code = ex.getCode();
    String message = ex.getMessage();
    return new JsonResult(code, message);
  }

  /**
   * 空指针异常
   * @param ex NullPointerException *
   * @return
   */
  @ExceptionHandler(NullPointerException.class)
  @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
  public JsonResult handleTypeMismatchException(NullPointerException ex) {
    log.error("空指针异常，{}", ex.getMessage());
    return new JsonResult("500", "空指针异常了");
  }

  /**
   * 系统异常 预期以外异常 * @param ex
   * @return
   */
  @ExceptionHandler(Exception.class)
  @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
  public JsonResult handleUnexpectedServer(Exception ex) {
    log.error("系统异常:", ex);
    return new JsonResult(BusinessMsgEnum.UNEXPECTED_EXCEPTION);
  }
}
