package com.microservice.redisson.enums;

import lombok.Getter;

@Getter
public enum RedisDelayQueueEnum {

    ORDER_PAYMENT_TIMEOUT("ORDER_PAYMENT_TIMEOUT", "超时订单自动关闭队列", "orderPaymentTimeout");

    RedisDelayQueueEnum(String code, String name, String beanId) {
        this.beanId = beanId;
        this.code = code;
        this.name = name;
    }

    /**
     * 延迟队列 Redis Key
     */
    private String code;

    /**
     * 中文描述
     */
    private String name;

    /**
     * 延迟队列具体业务实现的 Bean
     * 可通过 Spring 的上下文获取
     */
    private String beanId;


}
