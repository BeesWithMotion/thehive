package com.beeswithmotion.thehive.admin.dto;

import java.time.LocalDateTime;

public record TrafficLoggerMessage(
        String method,
        String path,
        String query,
        String userAgent,
        String referrer,
        String ipAddress,
        int status,
        LocalDateTime timestamp
) {
}
