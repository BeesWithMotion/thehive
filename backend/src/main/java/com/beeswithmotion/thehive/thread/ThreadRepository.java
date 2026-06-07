package com.beeswithmotion.thehive.thread;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

// TODO use Optional
public interface ThreadRepository extends JpaRepository<Thread, Long> {
    Thread findByThreadId(Long threadId);

    List<Thread> findByBoardAbbreviation(String boardAbbreviation);

    Thread findByBoardAbbreviationAndThreadId(String boardAbbreviation, Long threadId);
}
