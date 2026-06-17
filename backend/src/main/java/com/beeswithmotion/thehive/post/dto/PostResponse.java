package com.beeswithmotion.thehive.post.dto;

public record PostResponse(
        Long postId,
        Long threadId,
        String postContent,
        String postAuthor,
        String postDate
) {}