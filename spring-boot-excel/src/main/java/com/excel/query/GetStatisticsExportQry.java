package com.excel.query;

import lombok.Data;

/**
 * @author guofei
 * @date 2022/4/27 5:06 PM
 */
@Data
public class GetStatisticsExportQry {



  Integer stationTrend;

  Integer userTrend;

  Integer addTrend;

  Integer realTimeData;

  String excelName;

  Long beginTime;

  Long endTime;
}
