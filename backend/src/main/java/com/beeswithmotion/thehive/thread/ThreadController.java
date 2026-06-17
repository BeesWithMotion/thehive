package com.beeswithmotion.thehive.thread;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/")
public class ThreadController {
    private final ThreadService threadService;

    public ThreadController(ThreadService threadService) {
        this.threadService = threadService;
    }

    @GetMapping("/{boardAbbreviation}/threads")
    public ResponseEntity<List<Thread>> getThreads(@PathVariable String boardAbbreviation) {
        return threadService.getThreadsByBoardAbbreviation(boardAbbreviation);
    }

    @GetMapping("/{boardAbbreviation}/{threadId}")
    public ResponseEntity<Thread> getThread(@PathVariable String boardAbbreviation, @PathVariable Long threadId) {
        return threadService.getThreadByBoardAbbreviationAndThreadId(boardAbbreviation, threadId);
    }
}
