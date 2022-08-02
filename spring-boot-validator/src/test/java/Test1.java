import com.validate.pojo.UserDTO;
import com.validate.service.ValidateService;
import javax.annotation.Resource;
import org.junit.Test;

/**
 * @author guofei
 * @date 2022/8/2 2:06 PM
 */
public class Test1 extends ValidateTest{

  @Resource
  private ValidateService validateService;

  @Test
  public void test(){
    UserDTO userDTO = new UserDTO();
    userDTO.setUserId(10L);
    validateService.saveUser(userDTO);
  }


}
