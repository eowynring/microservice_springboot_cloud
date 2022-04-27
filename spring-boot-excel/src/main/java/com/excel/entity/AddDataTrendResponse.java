package com.excel.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentRowHeight;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * @author guofei
 * @date 2022/4/27 5:10 PM
 */
@Data
@ContentRowHeight(16)
@HeadRowHeight(20)
@ColumnWidth(25)
@AllArgsConstructor
public class AddDataTrendResponse {
  @ExcelProperty("统计日期")

  private String occurDate;

  @ExcelProperty("工位")
  private Integer numberOfAddStation;



  @ExcelProperty("员工")
  private Integer numberOfAddUser;
}
