import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.common.JsonResult;
import com.validate.handle.ValidationList;
import com.validate.pojo.UserDTO;
import com.validate.pojo.UserDTOs;
import com.validate.service.ValidateService;
import java.util.ArrayList;
import java.util.List;
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
    UserDTO userDTO1 = validateService.saveUser(userDTO);
  }


  @Test
  public void test1(){
    JsonResult jsonResult = validateService.testBaseType(5);
    System.out.println(jsonResult.toString());
  }

  @Test
  public void test2(){
    List<UserDTO> userDtos = getUserDtos();
    //String s = JSONObject.toJSONString(userDtos);
    //ValidationList validationList = JSONObject.parseObject(s, ValidationList.class);
    UserDTOs userDTOs = new UserDTOs();
    userDTOs.setUserDTOS(userDtos);
    ValidationList validationList = new ValidationList();
    validationList.addAll(userDtos);
    //ValidationList userDTOs1 = (ValidationList) userDTOs;
    validateService.testList(validationList);
  }


  public List<UserDTO> getUserDtos(){
    ArrayList<UserDTO> userDTOS = new ArrayList<>();
    UserDTO userDTO1 = new UserDTO();
    userDTO1.setUserId(1000L);
    userDTO1.setUserName("kobe");
    userDTOS.add(userDTO1);
    UserDTO userDTO2 = new UserDTO();
    userDTO2.setUserId(10L);
    userDTO2.setUserName("jordan");
    userDTOS.add(userDTO2);
    return userDTOS;
  }


}
