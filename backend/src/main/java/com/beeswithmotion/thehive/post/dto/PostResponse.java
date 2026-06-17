package com.beeswithmotion.thehive.post.dto;

import com.beeswithmotion.thehive.image.dto.ImageResponse;

import java.time.LocalDateTime;

public record PostResponse(
        Long postId,
        Long threadId,
        String postContent,
        String postAuthor,
        LocalDateTime postDate,
        ImageResponse image
) {}