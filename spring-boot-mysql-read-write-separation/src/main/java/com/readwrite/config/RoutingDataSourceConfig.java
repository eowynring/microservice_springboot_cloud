package com.readwrite.config;

import com.readwrite.handle.DBContextHolder;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * 获取路由key
 * @author guofei
 * @date 2022/4/18 11:11 AM
 */
public class RoutingDataSourceConfig extends AbstractRoutingDataSource {

  @Override
  protected Object determineCurrentLookupKey() {
    return DBContextHolder.get();
  }
}
