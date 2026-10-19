package com.beeswithmotion.thehive.admin;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(
        name = "traffic_logs",
        indexes = {
                @Index(name = "idx_traffic_logs_timestamp", columnList = "timestamp"),
                @Index(name = "idx_traffic_logs_path", columnList = "path"),
                @Index(name = "idx_traffic_logs_status", columnList = "status")
        }
)
public class TrafficLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String method;

    @Column(length = 2048)
    private String path;

    @Column(length = 2048)
    private String query;

    @Column(length = 1024)
    private String userAgent;

    @Column(length = 2048)
    private String referrer;

    private String ipAddress;

    private int status;

    private LocalDateTime timestamp;

    protected TrafficLog() {}

    public TrafficLog(
            String method,
            String path,
            String query,
            String userAgent,
            String referrer,
            String ipAddress,
            int status,
            LocalDateTime timestamp
    ) {
        this.method = method;
        this.path = path;
        this.query = query;
        this.userAgent = userAgent;
        this.referrer = referrer;
        this.ipAddress = ipAddress;
        this.status = status;
        this.timestamp = timestamp;
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public int getStatus() {
        return status;
    }

    public String getReferrer() {
        return referrer;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public String getQuery() {
        return query;
    }

    public String getPath() {
        return path;
    }

    public String getMethod() {
        return method;
    }
}
