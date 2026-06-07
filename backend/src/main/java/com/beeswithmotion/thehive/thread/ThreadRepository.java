package com.beeswithmotion.thehive.thread;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ThreadRepository extends JpaRepository<Thread, Long> {
    Thread findByThreadId(Long threadId);

    List<Thread> findByBoardId(Long boardId);
}
