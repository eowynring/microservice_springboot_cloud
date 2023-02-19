import cn.hutool.core.collection.ListUtil;
import cn.hutool.core.lang.Tuple;
import com.redis.config.RedisApplication;
import com.redis.config.util.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import lombok.var;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.connection.RedisZSetCommands;
import org.springframework.data.redis.core.DefaultTypedTuple;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestTemplate;

import java.util.Calendar;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@SpringBootTest(classes = RedisApplication.class)
@RunWith(SpringJUnit4ClassRunner.class)
@Slf4j
public class TestRedis {

    @Autowired
    private RedisUtil redisUtil;

    private static final String ORDER_ID_DELAY = "O:I:D";

    @Test
    public void test() {
        producerOrder();
        consumerDelayMessage();
    }

    public void producerOrder() {
        for (int i = 0; i < 5; i++) {
            val instance = Calendar.getInstance();
            instance.add(Calendar.SECOND, 3);
            val i1 = (int) (instance.getTimeInMillis() / 1000);
            redisUtil.zadd(ORDER_ID_DELAY, i1, "orderid0000" + i);
            System.out.println(System.currentTimeMillis() + "ms:redis生成了一个订单任务：订单ID为" + "orderId0000" + i);
        }
    }

    public void consumerDelayMessage() {
        log.info(String.format("延时队列机器{%s}开始运作", ORDER_ID_DELAY));
        // 发生异常捕获并且继续不能让战斗停下来
        while (true) {
            try {
                // 获取当前时间的时间戳
                long now = System.currentTimeMillis() / 1000;
                // 获取当前时间前的任务列表
                Set<DefaultTypedTuple> tuples = redisUtil.zrangeByScoreWithScores(ORDER_ID_DELAY, 0, now);
                // 如果不为空则遍历判断其是否满足取消要求
                if (!CollectionUtils.isEmpty(tuples)) {
                    for (DefaultTypedTuple tuple : tuples) {

                        String jobId = (String) tuple.getValue();
                        Long num = redisUtil.zremove(ORDER_ID_DELAY, jobId);
                        // 如果移除成功, 则取消订单
                        if (num > 0) {
                            //ThreadPoolUtil.execute(() -> invoke(jobId));
                            System.out.println(System.currentTimeMillis() + "ms:redis消费了一个任务：消费的订单OrderId为" + jobId);
                        }
                    }
                }

            } catch (Exception e) {
                log.warn(String.format("处理延时任务发生异常,异常原因为{%s}", e.getMessage()), e);
            } finally {
                // 间隔一秒钟搞一次
                try {
                    TimeUnit.SECONDS.sleep(1L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }
    }
}
