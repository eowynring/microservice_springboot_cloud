package com.shardingsphere.pojo;

import lombok.Data;

/**
 * @author: GuoFei
 * @date: 2022-04-30 14:36
 */
@Data
//@TableName("t_user")

public class User {
  private Long userId;
  private String username;
  private String status;
}
