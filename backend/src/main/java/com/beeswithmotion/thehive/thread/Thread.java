package com.beeswithmotion.thehive.thread;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "threads")
public class Thread {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long threadId;

    private String boardAbbreviation;
    private String threadTitle;
    private String threadDescription;
    private String threadAuthor;
    private LocalDateTime threadDate;

    public Thread() {}

    public Long getThreadId() {
        return threadId;
    }

    public String getBoardAbbreviation() {
        return boardAbbreviation;
    }

    public String getThreadTitle() {
        return threadTitle;
    }

    public String getThreadDescription() {
        return threadDescription;
    }

    public String getThreadAuthor() {
        return threadAuthor;
    }

    public LocalDateTime getThreadDate() {
        return threadDate;
    }
}
