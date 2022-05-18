package com.boot.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * @author guofei
 * @date 2022/5/18 5:02 PM
 */
@Data
@TableName("member")
public class Member implements Serializable {

  @TableId(value = "id", type = IdType.AUTO)
  private Integer id;

  private String name;

}
