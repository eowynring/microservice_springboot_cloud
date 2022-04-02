package com.microservice;

import com.baomidou.mybatisplus.core.toolkit.AES;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author guofei
 * @date 2022/4/2 3:46 PM
 */
@SpringBootTest
public class GitIgnoreTest {
  @Test
  public void testAES(){
    // 生成 16 位随机 AES 密钥
    //String randomKey = AES.generateRandomKey();
    String randomKey = "87a4fdeed41dbf3d";

    System.out.println("randomKey"+randomKey);
    // 随机密钥加密
    String url = "jdbc:mysql://39.105.15.49:3306/test?useUnicode=true&characterEncoding=utf-8&useSSL=true&serverTimezone=Asia/Shanghai&allowMultiQueries=true";
    String resultUrl = AES.encrypt(url, randomKey);
    System.out.println("url"+resultUrl);
    String passwd = "lqhmysql";
    String resultPasswd = AES.encrypt(passwd, randomKey);
    System.out.println("passwd"+resultPasswd);
  }
}
