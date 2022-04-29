package com.shardingsphere;

import com.shardingsphere.dao.ProductDao;
import com.shardingsphere.pojo.ProductInfo;
import com.shardingsphere.service.ProductService;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author guofei
 * @date 2022/4/29 5:48 PM
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ShardingsphereApplication.class)
public class ShardingTest {

  @Resource
  ProductService productService;

  @Resource
  ProductDao productDao;

  @Test
  public void testCreateProduct() {
    for (long i = 1; i < 10; i++) {
      //store_info_id,product_name,spec,region_code,price,image_url
      ProductInfo productInfo = new ProductInfo();
      productInfo.setProductName("Java编程思想" + i);
      productInfo.setDescript("Java编程思想是一本非常好的Java教程" + i);
      productInfo.setRegionCode("110000");
      productInfo.setStoreInfoId(1);
      productInfo.setPrice(new BigDecimal(i));
      productService.createProduct(productInfo);
    }
  }
  @Test
  public void testSelectProductList(){
    List<ProductInfo> productInfos = productService.queryProduct(1,10);
    System.out.println(productInfos);
  }

  @Test
  public void testSelectCount(){
    int i = productDao.selectCount();
    System.out.println(i);
  }
  @Test
  public void testSelectGroupList(){
    List<Map> maps = productDao.selectProductGroupList();
    System.out.println(maps);
  }
}
