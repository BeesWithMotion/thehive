package com.beeswithmotion.thehive.thread;

import com.beeswithmotion.thehive.thread.dto.CreateThreadRequest;
import com.beeswithmotion.thehive.thread.dto.ThreadResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/boards/{boardAbbreviation}/threads")
public class ThreadController {
    private final ThreadService threadService;

    public ThreadController(ThreadService threadService) {
        this.threadService = threadService;
    }

    @GetMapping
    public ResponseEntity<List<Thread>> getThreads(@PathVariable String boardAbbreviation) {
        return threadService.getThreadsByBoardAbbreviation(boardAbbreviation);
    }

    @GetMapping("/{threadId}")
    public ResponseEntity<Thread> getThread(@PathVariable String boardAbbreviation, @PathVariable Long threadId) {
        return threadService.getThreadByBoardAbbreviationAndThreadId(boardAbbreviation, threadId);
    }

    @PostMapping
    public ThreadResponse createThread(@PathVariable String boardAbbreviation, @Valid @RequestBody CreateThreadRequest request, Authentication authentication) {
        String username = authentication.getName();

        return threadService.createThread(boardAbbreviation, request, username);
    }
}
