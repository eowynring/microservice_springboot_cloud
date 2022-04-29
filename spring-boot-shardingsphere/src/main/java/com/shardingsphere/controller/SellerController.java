package com.shardingsphere.controller;

import com.shardingsphere.pojo.ProductInfo;
import com.shardingsphere.service.ProductService;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

  @GetMapping(value = "/products/{page}/{pageSize}")
  public List<ProductInfo> queryProduct(@PathVariable("page")int page,@PathVariable("pageSize")int pageSize){
    return productService.queryProduct(page,pageSize);
  }
}
