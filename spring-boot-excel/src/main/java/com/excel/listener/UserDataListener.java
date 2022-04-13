package com.excel.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.fastjson.JSON;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;

/**
 * 动态监听器读取文件
 *
 * @author guofei
 * @date 2022/4/13 5:50 PM
 */
@Slf4j
public class UserDataListener extends AnalysisEventListener<Map<Integer, String>> {


  /**
   * 表头数据
   */
  private List<Map<Integer, String>> headList = new ArrayList<>();

  /**
   * 数据体
   */
  private List<Map<Integer, String>> dataList = new ArrayList<>();


  /**
   * 这里会一行行的返回头
   * @param headMap
   * @param context
   */
  @Override
  public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
    log.info("解析到一条头数据:{}", JSON.toJSONString(headMap));
    headList.add(headMap);
  }

  /**
   * 这个每一条数据解析都会来调用
   * @param integerStringMap
   * @param analysisContext
   */
  @Override
  public void invoke(Map<Integer, String> integerStringMap, AnalysisContext analysisContext) {
    log.info("解析到一条数据:{}", JSON.toJSONString(integerStringMap));
    dataList.add(integerStringMap);
  }

  /**
   * 所有数据解析完成了 都会来调用
   * @param analysisContext
   */
  @Override
  public void doAfterAllAnalysed(AnalysisContext analysisContext) {
    log.info("所有数据解析完成！");
  }

  public List<Map<Integer, String>> getDataList() {
    return dataList;
  }

  public List<Map<Integer, String>> getHeadList() {
    return headList;
  }
}
