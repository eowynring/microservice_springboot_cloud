package com.microservice.java.test;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.time.Duration;
import java.util.Arrays;
import java.util.Map;
import java.util.Properties;

@Slf4j
public class MyKafkaConsumer {

    private final static String TOPIC_NAME = "mykafka-topic";
    private final static String CONSUMER_GROUP_NAME = "testGroup";

    public static void main(String[] args) {
        Properties props = new Properties();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
        // 消费分组名
        props.put(ConsumerConfig.GROUP_ID_CONFIG, CONSUMER_GROUP_NAME);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        // 手动提交
        // 1、同步手动提交
        // 2、异步手动提交
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, false);
        //创建一个消费者的客户端
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);
        // 消费者订阅主题列表
        try {
            consumer.subscribe(Arrays.asList(TOPIC_NAME));

            while (true) {
                /*
                 * poll() API 是拉取消息的⻓轮询
                 */
                ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(1000));
                for (ConsumerRecord<String, String> record : records) {
                    System.out.printf("收到消息：partition = %d,offset = %d, key =%s, value = %s%n", record.partition(), record.offset(), record.key(), record.value());
                }
            }
        } catch (Exception e) {
            log.error("Error: ", e);
        } finally {
            consumer.close();
            log.info("The consumer is now closed");
        }
        // 1、同步手动提交
        //consumer.commitSync();
        // 2、异步手动提交
        consumer.commitAsync((offsets, exception) -> {
            if (exception == null) {
                System.out.println(offsets);
            } else {
                log.error("fail to commit offsets {}", offsets, exception);
            }
        });
    }
}
