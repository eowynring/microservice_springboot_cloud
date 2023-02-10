package com.microservice.java.test;


import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * kafkaconsumer 非线程安全
 */
@Slf4j
public class MyKafkaThreadConsumerDemo2 {

    private static volatile Map<TopicPartition, OffsetAndMetadata> offsets;

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
        KafkaConsumerThread kafkaConsumerThread = new KafkaConsumerThread(
                properties,
                topic,
                Runtime.getRuntime().availableProcessors()
        );
        kafkaConsumerThread.start();
    }

    public static class KafkaConsumerThread extends Thread {
        private KafkaConsumer kafkaConsumer;

        private ExecutorService executorService;

        private int threadNumber;


        public KafkaConsumerThread(Properties properties, String topic, int threadNumber) {
            this.kafkaConsumer = new KafkaConsumer<>(properties);
            this.kafkaConsumer.subscribe(Arrays.asList(topic));
            this.threadNumber = threadNumber;
            executorService = new ThreadPoolExecutor(
                    threadNumber,
                    threadNumber,
                    0L,
                    TimeUnit.MILLISECONDS,
                    new ArrayBlockingQueue<>(1000),
                    new ThreadPoolExecutor.CallerRunsPolicy()
            );
        }

        @Override
        public void run() {
            try {
                while (true) {
                    ConsumerRecords<String, String> records = kafkaConsumer.poll(Duration.ofMillis(100));
                    for (TopicPartition tp : records.partitions()) {
                        List<ConsumerRecord<String, String>> tpRecords = records.records(tp);
                        long lastConsumerOffset = tpRecords.get(tpRecords.size() - 1).offset();
                        synchronized (offsets) {
                            if (!offsets.containsKey(tp)) {
                                offsets.put(tp, new OffsetAndMetadata(lastConsumerOffset + 1));
                            } else {
                                long position = offsets.get(tp).offset();
                                if (position < lastConsumerOffset + 1) {
                                    offsets.put(tp, new OffsetAndMetadata(lastConsumerOffset + 1));
                                }
                            }
                        }

                    }
                    if (!records.isEmpty()) {
                        executorService.submit(new RecordsHandler(records));
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                kafkaConsumer.close();
            }
        }
    }

    /**
     * 处理消息
     */
    public static class RecordsHandler extends Thread {
        private final ConsumerRecords<String, String> records;


        public RecordsHandler(ConsumerRecords records) {
            this.records = records;
        }

        @Override
        public void run() {
            // 处理 records
        }
    }


}
