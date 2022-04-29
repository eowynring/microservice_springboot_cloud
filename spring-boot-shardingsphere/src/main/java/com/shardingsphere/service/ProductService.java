package com.shardingsphere.service;

import com.shardingsphere.pojo.ProductInfo;
import java.util.List;

/**
 * @author guofei
 * @date 2022/4/29 5:42 PM
 */
public interface ProductService {

  void createProduct(ProductInfo product);

  List<ProductInfo> queryProduct(int page,int pageSize);
}
