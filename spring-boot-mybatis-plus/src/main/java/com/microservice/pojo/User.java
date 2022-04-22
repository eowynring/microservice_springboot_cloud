package com.microservice.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.Version;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author guofei
 * @date 2022/3/31 6:40 PM
 */
@Data
@AllArgsConstructor
public class User {
  @TableId(value = "id",type = IdType.AUTO)
  private Long id;
  private String name;
  private Integer age;
  private String email;
  @Version
  private Integer version;
}
