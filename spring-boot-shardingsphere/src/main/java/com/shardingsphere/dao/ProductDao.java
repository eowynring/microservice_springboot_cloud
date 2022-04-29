package com.shardingsphere.dao;

import com.shardingsphere.pojo.ProductDescript;
import com.shardingsphere.pojo.ProductInfo;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

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

  /**
   * 商品查询方法
   * @param start
   * @param pageSize
   * @return
   */
  @Select("select i.*, d.descript, r.region_name placeOfOrigin " +
      "from product_info i join product_descript d on i.id = d.product_info_id " +
      "join region r on r.region_code = i.region_code order by i.id desc limit #{start},# {pageSize}")
  List<ProductInfo> selectProductList(@Param("start")int start,@Param("pageSize") int pageSize);

  /**
   * 总数统计
   * @return
   */
  @Select("select count(1) from product_info")
  int selectCount();

  /**
   * 分组统计
   * @return
   */
  @Select("select count(1) as num from product_info group by region_code having num>1 ORDER BY region_code ASC")
  List<Map> selectProductGroupList();
}
