package com.suixinlu.eurakarabbitmq.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.UUID;

/**
 * @author: LHL
 * @ProjectName: rabbitmq
 * @Package: com.amor.config
 * @ClassName: RabbitSender
 * @Date: 2018/11/18 23:24
 * @Description:
 * @Version: 1.0
 */
@Service
public class RabbitSender implements RabbitTemplate.ConfirmCallback, RabbitTemplate.ReturnCallback {

    private final Logger logger = LoggerFactory.getLogger(RabbitSender.class);

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @PostConstruct
    public void init() {
        rabbitTemplate.setConfirmCallback(this);
        rabbitTemplate.setReturnCallback(this);
    }

    /**
     * 实现消息发送到RabbitMQ交换器后接收ack回调,如果消息发送确认失败就进行重试.
     */
    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        if (ack) {
            logger.info("消息发送成功,消息ID:{}", correlationData.getId());
        } else {
            logger.info("消息发送失败，消息ID:{}", correlationData.getId());
        }
    }

    /**
     * 实现消息发送到RabbitMQ交换器,但无相应队列与交换器绑定时的回调.
     */
    @Override
    public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
        logger.error("消息发送失败，replyCode:{}, replyText:{}，exchange:{}，routingKey:{}，消息体:{}",replyCode, replyText, exchange, routingKey, new String(message.getBody()));
    }

    /**
     * convertAndSend 异步,消息是否发送成功用ConfirmCallback和ReturnCallback回调函数类确认。
     * 发送MQ消息
     */
    public void sendMessage(String exchangeName, String routingKey, Object message) {
        rabbitTemplate.convertAndSend(exchangeName, routingKey, message, new CorrelationData(UUID.randomUUID().toString()));
    }

    /**
     * sendMessageAndReturn 当发送消息过后,该方法会一直阻塞在哪里等待返回结果,直到请求超时,配置spring.rabbitmq.template.reply-timeout来配置超时时间。
     * 发送MQ消息并返回结果
     */
    public Object sendMessageAndReturn(String exchangeName, String routingKey, Object message) {
        return rabbitTemplate.convertSendAndReceive(exchangeName, routingKey, message, new CorrelationData(UUID.randomUUID().toString()));
    }
}