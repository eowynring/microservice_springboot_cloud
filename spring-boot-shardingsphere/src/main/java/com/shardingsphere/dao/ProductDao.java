package com.shardingsphere.dao;

import com.shardingsphere.pojo.ProductDescript;
import com.shardingsphere.pojo.ProductInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;

/**
 * @author guofei
 * @date 2022/4/29 5:38 PM
 */
public interface ProductDao {


  /**
   * 添加商品基本信息
   * @param productInfo
   * @return
   */
  @Insert("insert into product_info(store_info_id,product_name,spec,region_code,price) value(#{storeInfoId},#{productName},#{spec},#{regionCode},#{price})")
  @Options(useGeneratedKeys = true,keyProperty = "productInfoId",keyColumn = "id")
  int insertProductInfo(ProductInfo productInfo);


  /**
   * 添加商品描述信息
   * @param productDescript
   * @return
   */
  @Insert("insert into product_descript(product_info_id,descript,store_info_id) value(# {productInfoId},#{descript},#{storeInfoId})")
  @Options(useGeneratedKeys = true,keyProperty = "id",keyColumn = "id")
  int insertProductDescript(ProductDescript productDescript);
}
