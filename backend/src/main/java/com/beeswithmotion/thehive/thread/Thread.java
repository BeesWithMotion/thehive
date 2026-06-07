package com.beeswithmotion.thehive.thread;

import jakarta.persistence.*;

import java.security.Timestamp;

@Entity
@Table(name = "threads")
public class Thread {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long threadId;

    private Long boardId;
    private String threadTitle;
    private Timestamp threadDate;

    public Thread() {}

    public Long getThreadId() {
        return threadId;
    }

    public Long getBoardId() {
        return boardId;
    }

    public String getThreadTitle() {
        return threadTitle;
    }

    public Timestamp getThreadDate() {
        return threadDate;
    }
}
