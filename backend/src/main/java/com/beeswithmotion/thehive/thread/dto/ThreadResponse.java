package com.beeswithmotion.thehive.thread.dto;

public record ThreadResponse(
        Long threadId,
        String boardAbbreviation,
        String threadTitle,
        String threadDescription,
        String threadAuthor,
        String threadDate
) {}
