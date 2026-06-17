package com.beeswithmotion.thehive.thread;

import com.beeswithmotion.thehive.thread.dto.CreateThreadRequest;
import com.beeswithmotion.thehive.thread.dto.ThreadResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/{boardAbbreviation}/threads")
    public ThreadResponse createThread(@PathVariable String boardAbbreviation, @Valid @RequestBody CreateThreadRequest request) {
        return threadService.createThread(boardAbbreviation, request);
    }
}
