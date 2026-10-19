package com.beeswithmotion.thehive.admin;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.JacksonJsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.amqp.core.TopicExchange;

@Configuration
public class TrafficLoggerRabbitConfig {
    public static final String EXCHANGE = "thehive.logs";
    public static final String QUEUE = "thehive.traffic.logs";
    public static final String ROUTING_KEY = "traffic.request";

    @Bean
    public TopicExchange trafficLogExchange() {
        return new TopicExchange(EXCHANGE, true, false);
    }

    @Bean
    public Queue trafficLogQueue() {
        return new Queue(QUEUE, true);
    }

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new JacksonJsonMessageConverter();
    }

    @Bean
    public Binding trafficLogBinding(Queue trafficLogQueue, TopicExchange trafficLogExchange) {
        return BindingBuilder
                .bind(trafficLogQueue)
                .to(trafficLogExchange)
                .with(ROUTING_KEY);
    }
}
