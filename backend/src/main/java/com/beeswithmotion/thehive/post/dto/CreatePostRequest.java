package com.beeswithmotion.thehive.post.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreatePostRequest(
        @NotBlank(message = "Post content is required")
        @Size(max = 2047, message = "Post content must be less than 2047 characters")
        String postContent,

        @Size(max = 80, message = "Author name must be less than 80 characters")
        String postAuthor
) {}
