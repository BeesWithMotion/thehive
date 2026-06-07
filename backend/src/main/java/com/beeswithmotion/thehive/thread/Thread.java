package com.beeswithmotion.thehive.thread;

import jakarta.persistence.*;

import java.security.Timestamp;

@Entity
@Table(name = "thread")
public class Thread {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long threadId;

    private Long boardId;
    private String threadTitle;
    private Timestamp threadDate;
}
