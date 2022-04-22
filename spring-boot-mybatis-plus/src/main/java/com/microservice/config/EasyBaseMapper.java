package com.microservice.config;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import java.util.Collection;

/**
 * @author guofei
 * @date 2022/4/22 6:19 PM
 */
public interface EasyBaseMapper<T> extends BaseMapper<T> {

  /**
   * 批量插入 仅适用于mysql
   * @param entityList
   * @return
   */
  Integer insertBatchSomeColumn(Collection<T> entityList);
}
