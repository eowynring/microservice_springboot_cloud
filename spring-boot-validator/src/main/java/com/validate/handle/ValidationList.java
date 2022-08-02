package com.validate.handle;

import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import lombok.Data;
import lombok.experimental.Delegate;

/**
 * @author guofei
 * @date 2022/8/2 1:40 PM
 * <p>{@link #{https://stackoverflow.com/questions/28150405/validation-of-a-list-of-objects-in-spring/36313615#36313615}</p>
 */
@Data
public class ValidationList<E> implements List<E> {

  @Delegate // @Delegate是lombok注解
  @Valid // 一定要加@Valid注解
  public List<E> list = new ArrayList<>();


  @Override
  public String toString() {
    return list.toString();
  }
}
