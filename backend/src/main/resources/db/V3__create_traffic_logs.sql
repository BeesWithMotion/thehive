CREATE TABLE traffic_logs (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    method VARCHAR(255),
    path VARCHAR(2048),
    query VARCHAR(2048),
    user_agent VARCHAR(1024),
    referrer VARCHAR(2048),
    ip_address VARCHAR(255),
    status INT NOT NULL,
    timestamp DATETIME(6)
);

CREATE INDEX idx_traffic_logs_timestamp ON traffic_logs (timestamp);
CREATE INDEX idx_traffic_logs_path ON traffic_logs (path);
CREATE INDEX idx_traffic_logs_status ON traffic_logs (status);