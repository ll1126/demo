package com.test.demo.core.rabbitmq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

/**
 * 接收
 */
//@Service
public class MQReceiver {

    private static Logger log = LoggerFactory.getLogger(MQReceiver.class);


    @RabbitListener(queues=MQConfig.QUEUE)
    public void receive(String message) {
        System.out.println(message);
    }

    @RabbitListener(queues=MQConfig.TOPIC_QUEUE1)
    public void receiveTopic1(String message) {
        System.out.println(message);
    }

    @RabbitListener(queues=MQConfig.TOPIC_QUEUE2)
    public void receiveTopic2(String message) {
        System.out.println(message);
    }

    @RabbitListener(queues=MQConfig.HEADERS_QUEUE)
    public void receiveHeaders(byte[] message) {
        System.out.println(new String(message));
    }
}
