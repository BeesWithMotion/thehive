package com.beeswithmotion.thehive.image.dto;

import java.time.LocalDateTime;

public record ImageResponse(
        Long imageId,
        Long postId,
        String originalFileName,
        String contentType,
        Long fileSize,
        LocalDateTime uploadDateTime,
        String imageUrl
) {}
