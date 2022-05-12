package com.boot.pojo;

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
public class User {

  Integer id;

  String username;

  String passwd;

  String status;


}
