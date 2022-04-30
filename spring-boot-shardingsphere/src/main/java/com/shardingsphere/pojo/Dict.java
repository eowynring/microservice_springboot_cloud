package com.shardingsphere.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author: GuoFei
 * @date: 2022-04-30 14:39
 */
@Data
@TableName("t_dict")
public class Dict {
  private Long dictId;
  private String status;
  private String value;
}
