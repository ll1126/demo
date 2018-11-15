package com.test.demo.core.rabbitmq;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;

import java.util.HashMap;
import java.util.Map;

//@Configuration
public class MQConfig {

    public static final String QUEUE = "queueName";
    public static final String TOPIC_QUEUE1 = "topic.queuename1";
    public static final String TOPIC_QUEUE2 = "topic.queuename2";
    public static final String TOPIC_EXCHANGE = "topic.exchange";
    public static final String FANOUT_EXCHANGE = "fanout.exchange";
    public static final String HEADERS_EXCHANGE = "headers.exchange";
    public static final String HEADERS_QUEUE = "headersqueueName";
    public static final String ROUTING_KEY1 = "topic.key1";
    public static final String ROUTING_KEY2 = "topic.#";

    /**
     * Direct模式 交换机Exchange
     */
    @Bean
    public Queue queue(){
        return new Queue(QUEUE,true);
    }

    /**
     * Topic模式 交换机Exchange
     */
    @Bean
    public Queue topicqueue1(){
        return new Queue(TOPIC_QUEUE1,true);
    }
    @Bean
    public Queue topicqueue2(){
        return new Queue(TOPIC_QUEUE2,true);
    }

    /**
     * topic 交换机  匹配queue名字成功后发送
     * @return
     */
    @Bean
    public TopicExchange topicExchange(){
        return new TopicExchange(TOPIC_EXCHANGE);
    }
    /** 进行绑定 */
    @Bean
    public Binding topicBinding1(){
        return BindingBuilder.bind(topicqueue1()).to(topicExchange()).with(ROUTING_KEY1);
    }
    @Bean
    public Binding topicBinding2(){
        return BindingBuilder.bind(topicqueue1()).to(topicExchange()).with(ROUTING_KEY2);
    }
    /**
     * Fanout 交换机  广播模式
     * @return
     */
    @Bean
    public FanoutExchange fanoutExchange(){
        return new FanoutExchange(FANOUT_EXCHANGE);
    }
    @Bean
    public Binding FanoutBinding(){
        return BindingBuilder.bind(topicqueue1()).to(fanoutExchange());
    }
    /**
     * Headers 交换机
     * @return
     */
    @Bean
    public HeadersExchange headersExchange(){
        return new HeadersExchange(HEADERS_EXCHANGE);
    }
    @Bean
    public Queue headerqueue(){
        return new Queue(HEADERS_QUEUE,true);
    }
    @Bean
    public Binding headersBinding(){
        Map<String,Object> map = new HashMap<>();
        map.put("headers1","value1");
        map.put("headers2","value2");
        return BindingBuilder.bind(headerqueue()).to(headersExchange()).whereAll(map).match();
    }
}
