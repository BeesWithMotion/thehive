package com.beeswithmotion.thehive.admin;

import com.beeswithmotion.thehive.admin.dto.TrafficLoggerMessage;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class TrafficLoggerConsumer {
    private final TrafficLogRepository trafficLogRepository;

    public TrafficLoggerConsumer(TrafficLogRepository trafficLogRepository) {
        this.trafficLogRepository = trafficLogRepository;
    }

    @RabbitListener(queues = TrafficLoggerRabbitConfig.QUEUE)
    public void consume(TrafficLoggerMessage message) {
        TrafficLog log = new TrafficLog(
                message.method(),
                message.path(),
                message.query(),
                message.userAgent(),
                message.referrer(),
                message.ipAddress(),
                message.status(),
                message.timestamp()
        );

        trafficLogRepository.save(log);
    }

}
