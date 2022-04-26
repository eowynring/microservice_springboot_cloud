package com.excel;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.excel.data.DataDemo;
import com.excel.data.DemoData;
import com.excel.util.TestFileUtil;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author guofei
 * @date 2022/4/26 5:54 PM
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ExcelApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TestExcel {


  @Test
  public void simpleWrite(){
    // 写法1 JDK8+
    // since: 3.0.0-beta1
    String fileName = TestFileUtil.getPath() + "simpleWrite" + System.currentTimeMillis() + ".xlsx";
    // 这里 需要指定写用哪个class去写，然后写到第一个sheet，名字为模板 然后文件流会自动关闭
    // 如果这里想使用03 则 传入excelType参数即可
    EasyExcel.write(fileName, DemoData.class)
        .sheet("模板")
        .doWrite(() -> {
          // 分页查询数据
          return DataDemo.data();
        });

  }

  @Test
  public void manySheet(){
    ExcelWriter excelWriter = null;
    // 方法2 如果写到不同的sheet 同一个对象
    String fileName = TestFileUtil.getPath() + "repeatedWrite" + System.currentTimeMillis() + ".xlsx";
    try {
      // 这里 指定文件
      ExcelWriter build = EasyExcel.write(fileName, DemoData.class).build();
      excelWriter = build;
      // 去调用写入,这里我调用了五次，实际使用时根据数据库分页的总的页数来。这里最终会写到5个sheet里面
      for (int i = 0; i < 5; i++) {
        // 每次都要创建writeSheet 这里注意必须指定sheetNo 而且sheetName必须不一样
        WriteSheet writeSheet = EasyExcel.writerSheet(i, "模板" + i).build();
        // 分页去数据库查询数据 这里可以去数据库查询每一页的数据
        List<DemoData> data = DataDemo.data();
        build.write(data, writeSheet);
      }
    } finally {
      // 千万别忘记finish 会帮忙关闭流
      if (excelWriter != null) {
        excelWriter.finish();
      }
    }
  }
}
