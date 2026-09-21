package com.beeswithmotion.thehive.admin;

import com.beeswithmotion.thehive.admin.dto.TrafficLoggerMessage;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class TrafficLoggerConsumer {
    @RabbitListener(queues = TrafficLoggerRabbitConfig.QUEUE)
    public void consume(TrafficLoggerMessage message) {
        System.out.println("Logged: " + message);
    }

}
