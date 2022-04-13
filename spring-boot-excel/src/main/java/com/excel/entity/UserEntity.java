package com.excel.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import java.util.Date;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * @author guofei
 * @date 2022/4/13 5:10 PM
 */
@Data
public class UserEntity {

  @ExcelProperty(value = "姓名")
  private String name;

  @ExcelProperty(value = "年龄")
  private int age;

  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  @ExcelProperty(value = "操作时间")
  private Date time;
}
