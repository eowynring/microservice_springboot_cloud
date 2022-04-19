package com.readwrite.config;

import com.readwrite.enums.DBTypeEnum;
import java.util.HashMap;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author guofei
 * @date 2022/4/18 10:59 AM
 */
@Configuration
public class DataSourceConfig {

  @Bean
  @ConfigurationProperties("spring.datasource.master")
  public DataSource masterDataSource(){
    return DataSourceBuilder.create().build();
  }

  @Bean
  @ConfigurationProperties("spring.datasource.slave")
  public DataSource slaveDataSource(){
    return DataSourceBuilder.create().build();
  }

  @Bean
  public DataSource routingDataSource(@Qualifier("masterDataSource") DataSource masterDataSource,
                                      @Qualifier("slaveDataSource") DataSource slaveDataSource){
    HashMap<Object, Object> targetDataSources = new HashMap<>();
    targetDataSources.put(DBTypeEnum.MASTER,masterDataSource);
    targetDataSources.put(DBTypeEnum.SLAVE,slaveDataSource);
    RoutingDataSourceConfig routingDataSourceConfig = new RoutingDataSourceConfig();
    routingDataSourceConfig.setDefaultTargetDataSource(masterDataSource);
    routingDataSourceConfig.setTargetDataSources(targetDataSources);
    return routingDataSourceConfig;
  }

}
