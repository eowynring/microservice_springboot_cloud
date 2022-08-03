package com.validate.handle;

import com.common.JsonResult;
import java.util.List;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author guofei
 * @date 2022/8/2 10:56 AM
 */
@ControllerAdvice
@ResponseBody
@Slf4j
public class WebExceptionHandle {


  @ExceptionHandler({MethodArgumentNotValidException.class})
  @ResponseStatus(HttpStatus.OK)
  public JsonResult resolveMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
    List<ObjectError> objectErrors = ex.getBindingResult().getAllErrors();
    //ex.getBindingResult().
    if (!CollectionUtils.isEmpty(objectErrors)) {
      StringBuilder msgBuilder = new StringBuilder();
      for (ObjectError objectError : objectErrors) {
        msgBuilder.append(objectError.getDefaultMessage()).append(",");
      }
      String errorMessage = msgBuilder.toString();
      if (errorMessage.length() > 1) {
        errorMessage = errorMessage.substring(0, errorMessage.length() - 1);
      }
      return buildResponse(errorMessage, HttpStatus.BAD_REQUEST);
    }
    return buildResponse(ex.getMessage(), HttpStatus.BAD_REQUEST);
  }

  private JsonResult buildResponse(String errorMessage, HttpStatus badRequest) {
    return new JsonResult(String.valueOf(badRequest.value()), errorMessage);
  }

  @ExceptionHandler(ConstraintViolationException.class)
  public JsonResult resolveConstraintViolationException(ConstraintViolationException ex) {
    Set<ConstraintViolation<?>> constraintViolations = ex.getConstraintViolations();
    if (!CollectionUtils.isEmpty(constraintViolations)) {
      StringBuilder msgBuilder = new StringBuilder();
      for (ConstraintViolation constraintViolation : constraintViolations) {
        msgBuilder.append(constraintViolation.getMessage()).append(",");
      }
      String errorMessage = msgBuilder.toString();
      if (errorMessage.length() > 1) {
        errorMessage = errorMessage.substring(0, errorMessage.length() - 1);
      }
      return buildResponse(errorMessage, HttpStatus.BAD_REQUEST);
    }
    return buildResponse(ex.getMessage(), HttpStatus.BAD_REQUEST);
  }
}
