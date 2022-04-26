package com.excel.data;

import com.alibaba.excel.util.ListUtils;
import java.util.Date;
import java.util.List;

/**
 * @author guofei
 * @date 2022/4/26 5:35 PM
 */
public class DataDemo {
  public static List<DemoData> data() {
    List<DemoData> list = ListUtils.newArrayList();
    for (int i = 0; i < 10; i++) {
      DemoData data = new DemoData();
      data.setString("字符串" + i);
      data.setDate(new Date());
      data.setDoubleData(0.56);
      list.add(data);
    }
    return list;

  }

}
