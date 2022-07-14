package com.client.handler.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * @author guofei
 * @date 2022/6/17 2:56 PM
 */
@Configuration
@EnableAsync
public class AsyncConfig {

  /**
   * 设置核心线程数
   */
  //@Value("${public-config.schedule.corePoolSize}")
  private int corePoolSize = 25;

  /**
   * 设置最大线程数
   */
  //@Value("${public-config.schedule.maxPoolSize}")
  private int maxPoolSize= 40;

  /**
   * 设置队列容量
   */
  //@Value("${public-config.schedule.queueCapacity}")
  private int queueCapacity = 400;

  /**
   * 设置线程活跃时间（秒）
   */
  //@Value("${public-config.schedule.keepAliveSeconds}")
  private int keepAliveSeconds = 1000;

  @Bean
  public TaskExecutor taskExecutor() {
    ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
    executor.setCorePoolSize(corePoolSize);
    executor.setMaxPoolSize(maxPoolSize);
    executor.setQueueCapacity(queueCapacity);
    executor.setKeepAliveSeconds(keepAliveSeconds);
    // 设置默认线程名称
    executor.setThreadNamePrefix("task-");
    // 等待所有任务结束后再关闭线程池
    executor.setWaitForTasksToCompleteOnShutdown(true);
    executor.initialize();
    return executor;
  }



}
