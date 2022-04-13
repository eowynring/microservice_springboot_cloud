package com.excel.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import java.util.Date;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * @author guofei
 * @date 2022/4/13 5:47 PM
 */
@Data
public class UserReadEntity {


  @ExcelProperty(value = "姓名")
  private String name;

  /**
   * 强制读取第三个 这里不建议 index 和 name 同时用，要么一个对象只用index，要么一个对象只用name去匹配
   */
  @ExcelProperty(index = 1)
  private int age;

  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  @ExcelProperty(value = "操作时间")
  private Date time;
}
