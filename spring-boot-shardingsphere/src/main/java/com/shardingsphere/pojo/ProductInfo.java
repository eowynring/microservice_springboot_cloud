package com.shardingsphere.pojo;

import java.math.BigDecimal;
import lombok.Data;

/**
 * @author guofei
 * @date 2022/4/29 5:40 PM
 */
@Data
public class ProductInfo {

  private Integer storeInfoId;

  private String descript;

  private Integer productInfoId;

  private String productName;

  private String regionCode;

  private BigDecimal price;
}
