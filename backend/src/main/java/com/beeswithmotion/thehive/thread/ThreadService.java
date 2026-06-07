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

    public ResponseEntity<List<Thread>> getThreadsByBoardAbbreviation(String boardAbbreviation) {
        List<Thread> threads = threadRepository.findByBoardAbbreviation(boardAbbreviation);
        return ResponseEntity.ok(threads);
    }

    public ResponseEntity<Thread> getThreadByBoardAbbreviationAndThreadId(String boardAbbreviation, Long threadId) {
        Thread thread = threadRepository.findByBoardAbbreviationAndThreadId(boardAbbreviation, threadId);
        return ResponseEntity.ok(thread);
    }
}
