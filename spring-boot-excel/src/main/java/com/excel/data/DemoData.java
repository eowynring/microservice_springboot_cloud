package com.excel.data;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import java.util.Date;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * @author guofei
 * @date 2022/4/26 5:35 PM
 */
@Getter
@Setter
@EqualsAndHashCode
public class DemoData {

  @ExcelProperty("字符串标题")
  private String string;
  @ExcelProperty("日期标题")
  private Date date;
  @ExcelProperty("数字标题")
  private Double doubleData;
  /**
   * 忽略这个字段
   */
  @ExcelIgnore
  private String ignore;

}
