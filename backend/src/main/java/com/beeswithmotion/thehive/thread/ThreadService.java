package com.beeswithmotion.thehive.thread;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ThreadService {
    private final ThreadRepository threadRepository;

    public ThreadService(ThreadRepository threadRepository) {
        this.threadRepository = threadRepository;
    }

    public ResponseEntity<List<Thread>> getThreadsByBoardId(Long boardId) {
        List<Thread> threads = threadRepository.findByBoardId(boardId);
        return ResponseEntity.ok(threads);
    }

    public ResponseEntity<Thread> getThreadByBoardIdAndThreadId(Long boardId, Long threadId) {
        Thread thread = threadRepository.findByBoardIdAndThreadId(boardId, threadId);
        return ResponseEntity.ok(thread);
    }
}
