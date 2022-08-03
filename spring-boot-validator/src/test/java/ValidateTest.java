import com.alibaba.fastjson.JSON;
import com.common.JsonResult;
import com.validate.ValidateApplication;
import org.junit.After;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author guofei
 * @date 2022/8/2 2:05 PM
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ValidateApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ValidateTest {
  protected Object result;

  @After
  public void printResult() {
    System.out.println(JSON.toJSONString(result));

  }

}
