package com.shardingsphere.service.impl;

import com.shardingsphere.dao.ProductDao;
import com.shardingsphere.pojo.ProductDescript;
import com.shardingsphere.pojo.ProductInfo;
import com.shardingsphere.service.ProductService;
import java.util.List;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author guofei
 * @date 2022/4/29 5:42 PM
 */
@Service
@Slf4j
public class ProductServiceImpl implements ProductService {

  @Resource
  private ProductDao productDao;

  @Override
  public void createProduct(ProductInfo product) {
    ProductDescript productDescript = new ProductDescript();
    productDescript.setDescript(product.getDescript());
    //新增商品基本信息
    productDao.insertProductInfo(product);
    productDescript.setProductInfoId(product.getProductInfoId());
    //冗余店铺信息
    productDescript.setStoreInfoId(product.getStoreInfoId());
    //新增商品描述信息
    productDao.insertProductDescript(productDescript);

  }

  @Override
  public List<ProductInfo> queryProduct(int page, int pageSize) {
    int start = (page-1) * pageSize;
    return productDao.selectProductList(start,pageSize);
  }
}
