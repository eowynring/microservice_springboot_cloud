package microservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author guofei
 * @date 2022/3/31 6:39 PM
 */
@SpringBootApplication
@EnableDiscoveryClient
public class OpenFeignApplication {

  public static void main(String[] args) {
    SpringApplication.run(OpenFeignApplication.class, args);
  }

}
