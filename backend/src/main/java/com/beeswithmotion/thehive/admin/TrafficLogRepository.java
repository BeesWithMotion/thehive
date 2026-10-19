package com.beeswithmotion.thehive.admin;

import org.springframework.data.jpa.repository.JpaRepository;

// TODO delete (certain?) logs after certain amount of time
public interface TrafficLogRepository  extends JpaRepository<TrafficLog, Long> {
    // Queries and such
}
