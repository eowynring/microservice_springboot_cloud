package com.microservice.pojo;

import lombok.Data;

/**
 * @author guofei
 * @date 2022/3/31 6:40 PM
 */
@Data
public class User {
  private Long id;
  private String name;
  private Integer age;
  private String email;
}
