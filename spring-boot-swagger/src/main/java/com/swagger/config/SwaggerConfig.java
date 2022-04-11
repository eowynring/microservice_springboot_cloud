package com.swagger.config;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * @author guofei
 * @date 2022/4/8 5:28 PM
 */
@Configuration
@EnableOpenApi
public class SwaggerConfig {

    @Bean
  public Docket createRestApi() {
      return new Docket(DocumentationType.OAS_30).apiInfo(
          new ApiInfoBuilder()
              .version("1.0")
              .description("spring-boot-swagger")
              .contact(new Contact("Guo", "", "G***@gamil.com"))
              .title("Swagger3测试项目")
              .build()
      ).enable(true);
    }
}
