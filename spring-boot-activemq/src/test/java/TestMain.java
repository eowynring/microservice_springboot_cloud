import com.microservice.ActivemqApplication;
import com.microservice.producer.Queue_Produce;
import javax.annotation.Resource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * @author guofei
 * @date 2022/6/5 1:53 PM
 */
// 加载主类
@SpringBootTest(classes = ActivemqApplication.class)
// 加载spring的junit
@RunWith(SpringJUnit4ClassRunner.class)
// 加载web
@WebAppConfiguration
public class TestMain {

  @Resource
  private Queue_Produce  queue_produce;

  @Test
  public void testSend(){
    queue_produce.produceMessage();
  }

  @Test
  public void testDelaySend() throws InterruptedException {
    queue_produce.produceDelayMessage("delay_queue","这是一条延迟消息",5000);
    Thread.sleep(7000);
  }

}
