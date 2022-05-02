package com.shardingsphere;

import lombok.val;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author guofei
 * @date 2022/4/29 5:37 PM
 */
@SpringBootApplication
//@MapperScan("com.shardingsphere.mapper")
public class ShardingsphereApplication {


  public static void main(String[] args) {
    final val run = SpringApplication.run(ShardingsphereApplication.class, args);
    System.out.println("------"+run);
  }

}
