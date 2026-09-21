package com.beeswithmotion.thehive.admin;

import com.beeswithmotion.thehive.admin.dto.TrafficLoggerMessage;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import java.io.IOException;
import java.time.LocalDateTime;

// TODO RabbitMQ??? Good use case!
@Component
public class TrafficLogger extends OncePerRequestFilter {
    private final RabbitTemplate rabbitTemplate;

    public TrafficLogger(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {
        try {
            filterChain.doFilter(request, response);
        } finally {
            String method = request.getMethod();
            String path = request.getRequestURI();
            String query = request.getQueryString();
            String userAgent = request.getHeader("User-Agent");
            String referrer = request.getHeader("Referer");
            String ipAddress = request.getRemoteAddr();
            int status = response.getStatus();

            TrafficLoggerMessage message = new TrafficLoggerMessage(
                    method,
                    path,
                    query,
                    userAgent,
                    referrer,
                    ipAddress,
                    status,
                    LocalDateTime.now()
            );

            rabbitTemplate.convertAndSend(
                    TrafficLoggerRabbitConfig.EXCHANGE,
                    TrafficLoggerRabbitConfig.ROUTING_KEY,
                    message
            );
        }
    }
}
