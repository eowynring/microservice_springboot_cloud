package com.boot.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author guofei
 * @date 2022/5/12 12:05 AM
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TxUser {

  @TableId(value = "id", type = IdType.AUTO)
  Integer id;

  String username;

  String passwd;

  String status;


}
