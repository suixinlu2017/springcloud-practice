package com.suixinlu.eurakarabbitmq.controller;

import com.suixinlu.eurakarabbitmq.service.RabbitSender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: LHL
 * @ProjectName: rabbitmq
 * @Package: com.amor.controller
 * @ClassName: RabbitmqController
 * @Date: 2018/11/18 21:33
 * @Description:
 * @Version: 1.0
 */
@RestController
public class RabbitmqController {
    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitmqController.class);

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private RabbitSender rabbitSender;

    /**
     * @Description: 发送消息
     * 1.交换机
     * 2.key
     * 3.消息
     * 4消息ID
     * rabbitTemplate.send(message);   发消息;参数对象为org.springframework.amqp.core.Message
     * rabbitTemplate.convertAndSend(message); 转换并发送消息;将参数对象转换为org.springframework.amqp.core.Message后发送,消费者不能有返回值
     * rabbitTemplate.convertSendAndReceive(message) //转换并发送消息,且等待消息者返回响应消息.消费者可以有返回值
     * @method: handleMessage
     * @Param: message
     * @return: void
     * @auther: LHL
     * @Date: 2018/11/18 21:40
     */
    @GetMapping("/directSend")
    public void directSend() {
        String message = "direct 发送消息";
        //rabbitTemplate.convertAndSend("DirectExchange", "DirectKey", message, new CorrelationData(UUID.randomUUID().toString()));
        rabbitSender.sendMessage("DirectExchange", "DirectKey", message);

    }

    @GetMapping("/topicSend")
    public void topicSend() {
        String message = "topic 发送消息";
        //rabbitTemplate.convertAndSend("TopicExchange", "Topic.Key", message, new CorrelationData(UUID.randomUUID().toString()));
        rabbitSender.sendMessage("TopicExchange", "Topic.Key", message);
    }

    @GetMapping("/fanoutSend")
    public void fanoutSend() {
        String message = "fanout 发送消息";
        //rabbitTemplate.convertAndSend("FanoutExchange", "", message, new CorrelationData(UUID.randomUUID().toString()));
        rabbitSender.sendMessage("FanoutExchange", "", message);
    }

    @GetMapping("/headersSend")
    public void headersSend() {
        String msg = "headers 发送消息";
        MessageProperties properties = new MessageProperties();
        properties.setHeader("headers1", "value1");
        properties.setHeader("headers2", "value2");
        Message message = new Message(msg.getBytes(), properties);
        //rabbitTemplate.convertAndSend("HeadersExchange","",message, new CorrelationData(UUID.randomUUID().toString()));
        rabbitSender.sendMessage("HeadersExchange", "", message);
    }

    /**
     * @Description: 消费消息
     * @method: handleMessage
     * @Param: message
     * @return: void
     * @auther: LHL
     * @Date: 2018/11/18 21:41
     */
    @RabbitListener(queues = "DirectQueue")
    @RabbitHandler
    public void directMessage(String sendMessage) {
        LOGGER.info("DirectConsumer {} directMessage :" + sendMessage);
    }

    @RabbitListener(queues = "TopicQueue")
    @RabbitHandler
    public void topicMessage(String message) {
        LOGGER.info("TopicConsumer {} topicMessage :" + message);
    }

    @RabbitListener(queues = "FanoutQueue")
    @RabbitHandler
    public void fanoutMessage(String message) {
        LOGGER.info("FanoutConsumer {} fanoutMessage :" + message);
    }

    @RabbitListener(queues = "HeadersQueue")
    @RabbitHandler
    public void headersMessage(Message message) {
        LOGGER.info("HeadersConsumer {} headersMessage :" + message);
    }
}