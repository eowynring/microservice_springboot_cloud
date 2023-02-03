package com.microserver;

import com.microservice.redisson.Main;
import com.microservice.redisson.enums.RedisDelayQueueEnum;
import com.microservice.redisson.util.RedisDelayQueueUtil;
import lombok.AllArgsConstructor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@SpringBootTest(classes = Main.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class DelayQueueTest {

    @Autowired
    private RedisDelayQueueUtil redisDelayQueueUtil;

    public DelayQueueTest(RedisDelayQueueUtil redisDelayQueueUtil) {
        this.redisDelayQueueUtil = redisDelayQueueUtil;
    }

    @Test
    public void test(){
        Map<String, String> map1 = new HashMap<>();
        map1.put("orderId", "10001");
        map1.put("remark", "订单支付超时，自动取消订单");

        // 为了测试效果，延迟10秒钟
        redisDelayQueueUtil.addDelayQueue(map1, 10, TimeUnit.SECONDS, RedisDelayQueueEnum.ORDER_PAYMENT_TIMEOUT.getCode());
    }

}

