package com.microservice.java.test;


import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;

/**
 * kafkaconsumer 非线程安全
 */
@Slf4j
public class MyKafkaThreadConsumerDemo1 {

    private static final String brokerList = "";
    private static final String topic = "";
    private static final String groupId = "";

    public static Properties initConfig() {
        Properties properties = new Properties();
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, brokerList);
        properties.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
        properties.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, true);
        return properties;
    }


    /**
     * 一个线程对应一个 KafkaConsumer 实例
     * 优点：每个线程可以按照顺序消费各个分区中的消息
     * 缺点：每个消费线程都要维护一个独立的 TCP 链接，如果分区数和 consumerThreadNum 数都很大，会造成不小的系统开销
     *
     * @param args
     */
    public static void main(String[] args) {
        Properties properties = initConfig();
        // 一般将这个设置为不大于分区数的值：避免大于之后造成线程浪费
        int consumerThreadNum = 4;
        for (int i = 0; i < consumerThreadNum; i++) {
            new KafkaConsumerThread(properties, topic).run();
        }
    }

    public static class KafkaConsumerThread extends Thread {
        private KafkaConsumer kafkaConsumer;


        public KafkaConsumerThread(Properties properties, String topic) {
            this.kafkaConsumer = new KafkaConsumer<>(properties);
            this.kafkaConsumer.subscribe(Arrays.asList(topic));
        }

        @Override
        public void run() {
            try {
                while (true) {
                    ConsumerRecords<String, String> poll = kafkaConsumer.poll(Duration.ofMillis(100));
                    for (ConsumerRecord<String, String> stringStringConsumerRecord : poll) {
                        // 处理消息
                        log.info("stringStringConsumerRecord {}", stringStringConsumerRecord);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                kafkaConsumer.close();
            }
        }
    }


}
