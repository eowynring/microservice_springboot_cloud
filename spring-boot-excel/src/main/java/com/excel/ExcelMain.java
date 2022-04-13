package com.excel;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.write.metadata.style.WriteCellStyle;
import com.alibaba.excel.write.metadata.style.WriteFont;
import com.alibaba.excel.write.style.HorizontalCellStyleStrategy;
import com.excel.entity.UserEntity;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.assertj.core.util.Lists;

/**
 * @author guofei
 * @date 2022/4/13 5:12 PM
 */
public class ExcelMain {

  public static void main(String[] args) throws FileNotFoundException {
    //ExcelTest1();
    //ExcelTest2();
    //定义多级表头
    List<List<String>> headList = new ArrayList<>();
    headList.add(Lists.newArrayList("班级"));
    headList.add(Lists.newArrayList("学生信息", "姓名"));
    headList.add(Lists.newArrayList("学生信息","年龄"));
    headList.add(Lists.newArrayList("学生信息","入学时间"));

    //定义数据体
    List<List<Object>> dataList = new ArrayList<>();
    for (int i = 0; i < 10; i++) {
      List<Object> data = new ArrayList<>();
      data.add("一年级～1班");
      data.add("张三" + i);
      data.add(20 + i);
      data.add(new Date(System.currentTimeMillis() + i));
      dataList.add(data);
    }
    //定义文件输出位置
    FileOutputStream outputStream = new FileOutputStream(new File("/Users/choicewell/goffy/doc/easyexcel-export-user3.xlsx"));
    EasyExcel.write(outputStream).registerWriteHandler(customerStyle()).head(headList).sheet("用户信息").doWrite(dataList);
  }

  private static void ExcelTest2() throws FileNotFoundException {
    //定义表头
    List<List<String>> headList = new ArrayList<>();
    headList.add(Lists.newArrayList("姓名"));
    headList.add(Lists.newArrayList("年龄"));
    headList.add(Lists.newArrayList("操作时间"));

    //定义数据体
    List<List<Object>> dataList = new ArrayList<>();
    for (int i = 0; i < 10; i++) {
      List<Object> data = new ArrayList<>();
      data.add("张三" + i);
      data.add(20 + i);
      data.add(new Date(System.currentTimeMillis() + i));
      dataList.add(data);
    }
    //定义文件输出位置
    FileOutputStream outputStream = new FileOutputStream(new File("/Users/choicewell/goffy/doc/easyexcel-export-user2.xlsx"));
    EasyExcel.write(outputStream).head(headList).sheet("用户信息").doWrite(dataList);
  }

  private static void ExcelTest1() throws FileNotFoundException {
    ArrayList<UserEntity> dataList = new ArrayList<>();
    for (int i = 0; i < 10; i++) {
      UserEntity userEntity = new UserEntity();
      userEntity.setName("张三" + i);
      userEntity.setAge(20 + i);
      userEntity.setTime(new Date(System.currentTimeMillis() + i));
      dataList.add(userEntity);
    }
    //定义文件输出位置
    FileOutputStream outputStream = new FileOutputStream(new File("/Users/choicewell/goffy/doc/easyexcel-export-user1.xlsx"));
    EasyExcel.write(outputStream, UserEntity.class).sheet("用户信息").doWrite(dataList);
  }

  /**
   * 自定义样式
   * @return
   */
  private static HorizontalCellStyleStrategy customerStyle(){
    // 头的策略
    WriteCellStyle headWriteCellStyle = new WriteCellStyle();
    // 背景设置为红色
    headWriteCellStyle.setFillForegroundColor(IndexedColors.RED.getIndex());
    WriteFont headWriteFont = new WriteFont();
    headWriteFont.setFontHeightInPoints((short)20);
    headWriteCellStyle.setWriteFont(headWriteFont);
    // 内容的策略
    WriteCellStyle contentWriteCellStyle = new WriteCellStyle();
    // 这里需要指定 FillPatternType 为FillPatternType.SOLID_FOREGROUND 不然无法显示背景颜色.头默认了 FillPatternType所以可以不指定
    contentWriteCellStyle.setFillPatternType(FillPatternType.SOLID_FOREGROUND);
    // 背景绿色
    contentWriteCellStyle.setFillForegroundColor(IndexedColors.GREEN.getIndex());
    WriteFont contentWriteFont = new WriteFont();
    // 字体大小
    contentWriteFont.setFontHeightInPoints((short)20);
    contentWriteCellStyle.setWriteFont(contentWriteFont);
    // 这个策略是 头是头的样式 内容是内容的样式 其他的策略可以自己实现
    HorizontalCellStyleStrategy horizontalCellStyleStrategy =
        new HorizontalCellStyleStrategy(headWriteCellStyle, contentWriteCellStyle);
    return horizontalCellStyleStrategy;
  }
}
