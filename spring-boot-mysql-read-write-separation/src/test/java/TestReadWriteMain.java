import com.apple.eawt.Application;
import com.readwrite.pojo.Member;
import com.readwrite.service.MemberService;
import javax.annotation.Resource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author guofei
 * @date 2022/4/19 10:59 AM
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class TestReadWriteMain {

  @Resource
  MemberService memberService;

  @Test
  public void insert(){
    Member member = new Member();
    member.setName("guofei");
    memberService.insert(member);
  }

}
