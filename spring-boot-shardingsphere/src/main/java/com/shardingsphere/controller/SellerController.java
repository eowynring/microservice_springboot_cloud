package com.shardingsphere.controller;

import com.shardingsphere.pojo.ProductInfo;
import com.shardingsphere.service.ProductService;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author guofei
 * @date 2022/4/29 5:47 PM
 */
@RestController
public class SellerController {

  @Resource
  private ProductService productService;

  @PostMapping("/products")
  public String createProject(@RequestBody ProductInfo productInfo) {
    productService.createProduct(productInfo);
    return "创建成功!";
  }
}
