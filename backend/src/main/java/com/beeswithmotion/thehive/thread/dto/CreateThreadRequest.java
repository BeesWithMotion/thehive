package com.beeswithmotion.thehive.thread.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateThreadRequest(
        // Board abbreviation given in HTTP POST request in ThreadController
    //@NotBlank(message = "Board is required")
    //String boardAbbreviation,

    @NotBlank(message = "Thread title is required")
    @Size(max = 255, message = "Thread title must be less than 255 characters")
    String threadTitle,

    String threadDescription,

    @Size(max = 80, message = "Author name must be less than 80 characters")
    String threadAuthor
) {}
