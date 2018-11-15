package com.test.demo.core.rabbitmq;

import com.test.demo.util.StringUtil;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.beans.factory.annotation.Autowired;

//@Service
public class MQSender {

    @Autowired
    AmqpTemplate amqpTemplate;

    public void send(Object message){
        String msg = StringUtil.beanToString(message);
        amqpTemplate.convertAndSend(MQConfig.QUEUE,msg);
    }

    public void topicSend(Object message){
        String msg = StringUtil.beanToString(message);
        amqpTemplate.convertAndSend(MQConfig.TOPIC_EXCHANGE,"topic.key1",msg+"1");
        amqpTemplate.convertAndSend(MQConfig.TOPIC_EXCHANGE,"topic.key2",msg+"2");
    }

    public void headersSend(Object message){
        String msg = StringUtil.beanToString(message);
        MessageProperties properties = new MessageProperties();
        properties.setHeader("headers1","value1");
        properties.setHeader("headers2","value2");
        Message obj = new Message(msg.getBytes(),properties);
        amqpTemplate.convertAndSend(MQConfig.FANOUT_EXCHANGE,"",obj);
    }

}
