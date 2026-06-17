package com.beeswithmotion.thehive.thread;

import com.beeswithmotion.thehive.thread.dto.CreateThreadRequest;
import com.beeswithmotion.thehive.thread.dto.ThreadResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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

    public ThreadResponse createThread(String boardAbbreviation, CreateThreadRequest request) {
        Thread thread = new Thread();

        thread.setBoardAbbreviation(boardAbbreviation);

        String author = request.threadAuthor();
        if(author == null || author.isBlank()) {
            author = "Drone";
        }
        String title = request.threadTitle();
        if(title == null || title.isBlank()) {
            title = "Untitled thread";
        }
        String description = request.threadDescription();
        if(description == null || description.isBlank()) {
            description = "";
        }
        thread.setThreadAuthor(author);
        thread.setThreadTitle(title);
        thread.setThreadDescription(description);

        thread.setThreadDate(LocalDateTime.now());

        Thread createdThread = threadRepository.save(thread);

        return toThreadResponse(createdThread);
    }

    private ThreadResponse toThreadResponse(Thread thread) {
        return new ThreadResponse(thread.getThreadId(), thread.getBoardAbbreviation(), thread.getThreadTitle(), thread.getThreadDescription(), thread.getThreadAuthor(), thread.getThreadDate().toString());
    }
}
